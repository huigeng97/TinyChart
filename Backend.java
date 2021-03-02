// --== CS400 File Header Information ==--
// Name: Edward Zhao
// Email: edward.zhao@wisc.edu
// Team: GE red
// Role: Backend developer
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.Reader;

/**
 * Class to represent nodes in the hash table
 * 
 * @author edward
 */
class LinkedNode {
  String genre; // one genre of the movie
  List<String> genreSet; // a list of genres of movie
  String rating; // the rating of the movie
  MovieInterface movie; // the movie
  LinkedNode next; // the next node

  /**
   * Constructor that initializes the node by giving keys and value
   * this constructor is only used for genre hash table and only saves
   * one genre of a movie in a node
   * 
   * @param genre one of the keys of hash table
   * @param rating one of the keys of hash table
   * @param movie the value of hash table
   */
  public LinkedNode(String genre, String rating, MovieInterface movie) {
    this.genre = genre;
    this.rating = rating;
    this.movie = movie;
  }

  /**
   * Constructor that initializes the node by giving keys and value
   * this constructor is only used for rating hash table and saves
   * all the genres of a movie in a node
   * 
   * @param genre
   * @param rating
   * @param movie
   */
  public LinkedNode(List<String> genre, String rating, MovieInterface movie) {
    this.genreSet = genre;
    this.rating = rating;
    this.movie = movie;
  }
}

/**
 * Stores lists of movies in a hash table. The hash table will map strings
 * that represent genres and ratings (numbers as strings from “0” to “10”)
 * to lists of movie objects of the respective genre or rating
 * 
 * @author edward
 */
public class Backend implements BackendInterface {

  private int capacity; // capacity of both hash tables
  private int size; // the size of genre storage
  private LinkedNode[] genreList; // the genre list that maps to movies
  private LinkedNode[] ratingList; // the rating list that maps to movies
  private List<String> currGenre; // list of genres currently set
  private List<String> currRating; // list of ratings currently set
  private List<MovieInterface> movies; // resulting set of movies

  /**
   * The constructor that takes the command line arguments to the application 
   * as a parameter, parses them, and instantiates the Data Wrangler’s 
   * implementation of the MovieDataReaderInterface interface to read in the 
   * data file passed as a command line argument to the application. It will 
   * then store the list of movies that the MovieDataReaderInterface provides.
   * 
   * @param args Can be the movie data file path or raw movie data. If it's
   * the movie data file path, args should be `new FileReader(filePath)`, 
   * otherwise it should be `new StringReader(rawData)`.
   */
  public Backend(Reader args) {
    this.capacity = 100;
    this.genreList = new LinkedNode[capacity];
    this.ratingList = new LinkedNode[20];
    this.currGenre = new ArrayList<String>();
    this.currRating = new ArrayList<String>();
    this.movies = new ArrayList<MovieInterface>();

    int index;
    List<MovieInterface> data = null; // all the movies

    // instantiates the Data Wrangler’s implementation
    MovieDataReaderInterface mdr = new MovieDataReader();

    try {
      // if the input is a String containing movie info
      if (args.getClass().toString().equals("class java.io.StringReader")) {
        data = mdr.readDataSet(args);
      } else { // if the input isn't a String (but a file)
        data = mdr.readDataSet(args);
      }
    } catch (IOException | DataFormatException e) {
      e.printStackTrace();
    }

    // traverse every movie in the data set
    for (int i = 0; i < data.size(); i++) {
      // map genres to the movie
      for (int j = 0; j < data.get(i).getGenres().size(); j++) {
        // implements hash function to find index of a key
        index = hashIndexHelper(data.get(i).getGenres().get(j), capacity);
        // current node
        LinkedNode currNode = genreList[index];

        // create a new node if empty
        if (currNode == null) {
          // check if rating is 10
          if (data.get(i).getAvgVote() != 10.0) {
            currNode = new LinkedNode(
                data.get(i).getGenres().get(j), 
                data.get(i).getAvgVote().toString().substring(0, 1), 
                data.get(i));
          } else { // if the rating is 10
            currNode = new LinkedNode(
                data.get(i).getGenres().get(j), 
                "10", 
                data.get(i));
          }
          genreList[index] = currNode;
          size += 1;
          if (1.0 * size / capacity > 0.9) {
            resizeHelper();
          }
        } else { // add a new node at the end if not empty
          while(currNode.next != null) { // loop till the end
            currNode = currNode.next;
          }
          if (data.get(i).getAvgVote() != 10.0) {
            currNode.next = new LinkedNode(
                data.get(i).getGenres().get(j), 
                data.get(i).getAvgVote().toString().substring(0, 1), 
                data.get(i)); // add a new node at the end
          } else { // if rating is 10
            currNode.next = new LinkedNode(
                data.get(i).getGenres().get(j), 
                "10", 
                data.get(i));
          }
          size += 1;
          // check if need resize
          if (1.0 * size / capacity > 0.9) {
            resizeHelper();
          }
        }
      }

      // map ratings to the movie
      // implements hash function to find index of a key
      if (data.get(i).getAvgVote() == 10.0) { // if the rating is 10.0
        index = "10".hashCode() % 20; // get hash code for "10" 
      } else { // if the rating is not 10.0
        // get hash code for the digits
        index = data.get(i).getAvgVote().toString().substring(0, 1).hashCode() % 20;
      }
      // current node
      LinkedNode currNode = ratingList[index];

      // create a new node if empty
      if (currNode == null) {
        // if rating is not 10
        if (data.get(i).getAvgVote() != 10.0) {
          currNode = new LinkedNode(
              data.get(i).getGenres(),
              data.get(i).getAvgVote().toString().substring(0, 1),
              data.get(i));
        } else { // if rating is 10
          currNode = new LinkedNode(
              data.get(i).getGenres(),
              "10",
              data.get(i));
        }
        ratingList[index] = currNode;
      } else { // if not empty
        while (currNode.next != null) { // loop till the end
          currNode = currNode.next;
        }
        // if rating is not 10
        if (data.get(i).getAvgVote() != 10.0) {
          currNode.next = new LinkedNode(
              data.get(i).getGenres(), 
              data.get(i).getAvgVote().toString().substring(0, 1), 
              data.get(i)); // add a new node at the end
        } else { // if rating is 10
          currNode.next = new LinkedNode(
              data.get(i).getGenres(), 
              "10", 
              data.get(i));
        }
      }
    }
  }

  /**
   * A helper method that resizes and rehashes the genre hash table when the 
   * data takes up 90% above of capacity
   */
  private void resizeHelper() {
    this.capacity = 2 * capacity; // double the capacity
    LinkedNode[] temp = genreList; // copy the old hash table

    // initialize a new genre list with doubled capacity
    this.genreList = new LinkedNode[capacity]; 

    // copy all the nodes that are not null from old hash table to the new one
    for(int i = 0; i < capacity / 2; i++)
      if(temp[i] != null) { // check if the node is null
        // rehash
        int index = hashIndexHelper(temp[i].genre, capacity);
        genreList[index] = temp[i];
      }
  }

  /**
   * A helper method that selects movies that fulfill 
   * both rating and genre requirements
   */
  private void movieSelect() {
    int index = 0; // index of rating hash table
    movies = new ArrayList<MovieInterface>(); // reset movies

    if (currRating.size() == 0) { // if genre filter only
      for (int i = 0; i < currGenre.size(); i++) { // traverse selected genres
        index = hashIndexHelper(currGenre.get(i), capacity); // implement hash function
        LinkedNode currNode = genreList[index]; // current node

        if (movies.size() == 0) { // if first genre
          while (currNode != null) { // traverse the chain
            movies.add(currNode.movie); // add each movie on the chain
            currNode = currNode.next;
          }
        } else { // if not first genre
          // ArrayList used to save movies that fulfill all the genres
          List<MovieInterface> temp = new ArrayList<MovieInterface>();
          while (currNode != null) { // traverse the chain
            if (movies.contains(currNode.movie)) { // if fulfill all the genres
              temp.add(currNode.movie); // add movie
            }
            currNode = currNode.next;
          }
          movies = temp; // renew with movies that fulfill all the genres
        }
      }
    } else { // if there is rating filter
      // loop through rating hash table
      for (int i = 0; i < currRating.size(); i++) {
        // implement hash function to find index of rating
        if (currRating.get(i).contains("10")) {
          index = hashIndexHelper("10", 20);
        } else {
          index = hashIndexHelper(currRating.get(i).substring(0, 1), 20);
        }
        // current node
        LinkedNode currNode = ratingList[index];
        while (currNode != null) { // loop through the chain
          if (currNode.genreSet.containsAll(currGenre)) { // fulfills genre requirements
            for (int j = 0; j < currRating.size(); j++) { // traverse rating requirements
              // if fulfills rating requirement
              if ((currRating.get(j).contains("10") && currNode.rating.equals("10"))
                  || (currRating.get(j).substring(0, 1).equals(currNode.rating))) {
                movies.add(currNode.movie); // add movie
                break;
              }
            }
          }
          currNode = currNode.next;
        }
      } 
    }
  }

  /**
   * A helper method that produces the index in the hash table for keys
   * 
   * @param key the key value used to search in the hash table
   * @param num the number being modulo by key's hash code
   * @return the index of the input key
   */
  private int hashIndexHelper(String key, int num) {
    // implement hash function
    int index = key.hashCode() % num;
    if (index < 0) { // if index is negative
      index += num; // fix the negative index by adding num
    }
    return index;
  }

  /**
   * Adds another genre to select movies by
   * 
   * @param genre the string for the genre used in the data set
   */
  @Override
  public void addGenre(String genre) {
    currGenre.add(genre); // add a genre filter
    movieSelect(); // select movies
  }

  /**
   * Adds another rating to select movies by
   * 
   * @param rating a string representation of the number before 
   * the decimal point of the rating to select for
   */
  @Override
  public void addAvgRating(String rating) {
    currRating.add(rating); // add a rating filter
    movieSelect(); // select movies
  }

  /**
   * Removes another genre to select movies by
   * 
   * @param genre the string for the genre removed in the data set
   */
  @Override
  public void removeGenre(String genre) {
    currGenre.remove(genre); // remove a genre filter
    movieSelect(); // select movies
  }

  /**
   * Removes another average rating to select movies by
   * 
   * @param genre the string for the average rating removed in the data set
   */
  @Override
  public void removeAvgRating(String rating) {
    currRating.remove(rating); // remove a rating
    movieSelect(); // select movies
  }

  /**
   * Gets lists of the genres currently set
   * 
   * @return lists of the genres from the currently set
   */
  @Override
  public List<String> getGenres() {
    return currGenre;
  }

  /**
   * Gets lists of average ratings currently set
   * 
   * @return lists of average ratings from the currently set
   */
  @Override
  public List<String> getAvgRatings() {
    // insert sort
    for (int i = 1; i < currRating.size(); i++) {
      String temp = currRating.get(i);
      int j = i - 1;
      for (; j >= 0 && currRating.get(j).compareTo(temp) > 0; j--) {
        currRating.set(j+1, currRating.get(j));
      }
      currRating.set(j+1, temp);
    }
    // move 10 to the end
    if (currRating.contains("10")) {
      currRating.remove("10");
      currRating.add("10");
    }
    return currRating;
  }

  /**
   * Gets the number of movies in the resulting set
   * 
   * @return the number of movies in the resulting set
   */
  @Override
  public int getNumberOfMovies() {
    return movies.size();
  }

  /**
   * Gets a list of all the genres from the data set
   * 
   * @return a list of all the genres from the data set
   */
  @Override
  public List<String> getAllGenres() {
    // list of all genres
    List<String> genres = new ArrayList<String>();

    // loop through the genre hash table
    for (int i = 0; i < capacity; i++) {
      // current node
      if (genreList[i] != null) {
        genres.add(genreList[i].genre);
      }
    }
    return genres;
  }

  /**
   * Gets a list of three movies starting at (and including) the movie at 
   * the startingIndex of the resulting set ordered by average movie rating 
   * in descending order. If there are less than three movies left at the 
   * startingIndex, the resulting list contains all those movies 
   * (the list may be empty).
   * 
   * @return a list of three movies in descending order
   */
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    // top three movies starting from the startingIndex
    List<MovieInterface> threeMovies = new ArrayList<MovieInterface>();
    
    // check if startingIndex valid
    if (startingIndex < 0 || startingIndex > movies.size()-1) {
      return threeMovies;
    }

    // sort the result set in descending order
    for (int i = 1; i < movies.size(); i++) {
      MovieInterface temp = movies.get(i);
      int j = i - 1;
      for (; j >= 0 && movies.get(j).getAvgVote() < temp.getAvgVote(); j--) {
        movies.set(j+1, movies.get(j));
      }
      movies.set(j+1, temp);
    }

    // get three movies starting from startingIndex
    for (int i = startingIndex; i < startingIndex + 3 && i < movies.size(); i++) {
      threeMovies.add(movies.get(i));
    }
    return threeMovies;
  }
}