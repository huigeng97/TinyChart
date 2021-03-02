// --== CS400 File Header Information ==--
// Name: Gahan Sudhir
// Email: gsudhir@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.util.List;
import java.util.ArrayList;

/**
 * This class creates Movies with attributes of title, year, director, description, genres, and an
 * average vote
 * 
 * @author Gahan Sudhir
 */
public class Movie implements MovieInterface {

  private String title;
  private Integer year;
  private String director;
  private String description;
  private List<String> genres;
  private float avgVote;

  /**
   * constructs movie objects with attributes
   * 
   * @param title
   * @param year
   * @param director
   * @param description
   * @param genres
   * @param avgVote
   */
  public Movie(String title, String year, String director, String description, String genres,
      String avgVote) {
    this.title = title;
    this.year = Integer.parseInt(year);
    director = director.replace("\"", "");
    this.director = director;
    description = description.replace("\"", "");
    this.description = description;
    genres = genres.replace("\"", "");
    String[] gen = genres.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // deals with quotes
                                                                          // within quotes
    this.genres = new ArrayList<String>();
    for (int i = 0; i < gen.length; i++) {
      gen[i] = gen[i].replace(" ", "");
      this.genres.add(gen[i]);
    }
    this.avgVote = Float.parseFloat(avgVote);
  }

  /**
   * prints out attributes of movie objects
   */
  public String toString() {
    String ret = "";
    ret += "Title: \"" + title + "\", Genre: \"";
    for (int i = 0; i < genres.size(); i++) {
      if (i == genres.size() - 1)
        ret += genres.get(i);
      else
        ret += genres.get(i) + ", ";
    }
    ret += "\", Vote: " + avgVote + ", Year: " + year + ", Director(s): \"" + director
        + "\", Description: \"" + description + "\"";
    return ret;
  }

  @Override
  /**
   * returns title
   */
  public String getTitle() {
    return title;
  }

  @Override
  /**
   * returns year
   */
  public Integer getYear() {
    return year;
  }

  @Override
  /**
   * return list of genres
   */
  public List<String> getGenres() {
    return genres;
  }

  @Override
  /**
   * returns director(s)
   */
  public String getDirector() {
    return director;
  }

  @Override
  /**
   * return description
   */
  public String getDescription() {
    return description;
  }

  @Override
  /**
   * returns average vote
   */
  public Float getAvgVote() {
    return avgVote;
  }

  @Override
  /**
   * @return 1 if avgvote less than, -1 if avgvote greater than, 0 if equal avgvotes
   */
  public int compareTo(MovieInterface otherMovie) {
    if (avgVote - otherMovie.getAvgVote() > 0)
      return -1;
    else if (avgVote - otherMovie.getAvgVote() == 0)
      return 0;
    else
      return 1;
  }

}
