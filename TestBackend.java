// --== CS400 File Header Information ==--
// Name: Edward Zhao
// Email: edward.zhao@wisc.edu
// Team: GE red
// Role: Backend developer
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend {

  public static void main(String[] args) {
    (new TestBackend()).runTests();
  }

  public void runTests() {
    // add all tests to this method
    if (this.testInitialNumberOfMovies()) {
      System.out.println("Test initial number of movies: PASSED");
    } else {
      System.out.println("Test initial number of movies: FAILED");
    }
    if (this.testGetAllGenres()) {
      System.out.println("Test get all genres: PASSED");
    } else {
      System.out.println("Test get all genres: FAILED");
    }
    if (this.testGetThreeMoviesInitial()) {
      System.out.println("Test get three movies sorted by rating: PASSED");
    } else {
      System.out.println("Test get three movies sorted by rating: FAILED");
    }
    if (this.testGetAvgRatings()) {
      System.out.println("Test get average ratings: PASSED");
    } else {
      System.out.println("Test get average ratings: FAILED");
    }
    if (this.testGetThreeMovies()) {
      System.out.println("Test get three movies: PASSED");
    } else {
      System.out.println("Test get three movies: FAILED");
    }
    if (this.testGetGenres()) {
      System.out.println("Test get genres: PASSED");
    } else {
      System.out.println("Test get genres: FAILED");
    }
    if (this.testAddRemoveGenre()) {
      System.out.println("Test add and remove genres: PASSED");
    } else {
      System.out.println("Test add and remove genres: FAILED");
    }
    if (this.testAddRemoveRating()) {
      System.out.println("Test add and remove ratings: PASSED");
    } else {
      System.out.println("Test add and remove ratings: FAILED");
    }
    if (this.testNumberOfMovies()) {
      System.out.println("Test the number of movies: PASSED");
    } else {
      System.out.println("Test the number of movies: PASSED");
    }
  }
  /**
   * This test instantiates the back end with three movies and tests whether the
   * initial selection is empty (getNumberOfMovies() returns 0). It passes when
   * 0 is returned and fails in all other cases, including when an exception is
   * thrown.
   * @return true if the test passed, false if it failed
   */
  public boolean testInitialNumberOfMovies() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
          ));

      if (backendToTest.getNumberOfMovies() == 0) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether
   * the getAllGenres method return the correct set of genres for those three
   * movies.
   * @return true if the test passed, false if it failed
   */
  public boolean testGetAllGenres() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
          ));

      if (backendToTest.getAllGenres().size() == 5
          && backendToTest.getAllGenres().contains("Horror")
          && backendToTest.getAllGenres().contains("Action")
          && backendToTest.getAllGenres().contains("Comedy")
          && backendToTest.getAllGenres().contains("Musical")
          && backendToTest.getAllGenres().contains("Romance")) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the
   * initial list returned by getThreeMovies starting with the first movie (0)
   * is empty. It passes when 0 is returned and fails in all other cases, including
   * when an exception is thrown.
   * @return true if the test passed, false if its failed
   */
  public boolean testGetThreeMoviesInitial() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
          ));

      if (backendToTest.getThreeMovies(0).size() == 0) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the Backend with 3 movies and selects 5 ratings to
   * test whether the list returned by getRatings() has the expected size. It passes 
   * when there are 3 ratings with the correct values and fails in all other 
   * cases.
   * @return true if the test passed, false if its failed
   */
  public boolean testGetAvgRatings() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
          ));

      // add 5 ratings to the rating list
      backendToTest.addAvgRating("2");
      backendToTest.addAvgRating("3");
      backendToTest.addAvgRating("4");
      backendToTest.addAvgRating("5");
      backendToTest.addAvgRating("6");

      if (backendToTest.getAvgRatings().size() == 5
          && backendToTest.getAvgRatings().contains("2") 
          && backendToTest.getAvgRatings().contains("3")
          && backendToTest.getAvgRatings().contains("4")
          && backendToTest.getAvgRatings().contains("5")
          && backendToTest.getAvgRatings().contains("6")) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with 3 movies and tests whether the
   * list returned by getThreeMovies has a size of 3 and whether the output is 
   * correct. It passes when the size is 3 with the correct titles and fails in 
   * all other cases, including when an exception is thrown.
   * @return true if the test passed, false if its failed
   */
  public boolean testGetThreeMovies() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
          ));

      backendToTest.addGenre("Action"); // add genre Action
      if (backendToTest.getThreeMovies(0).size() == 1
          && backendToTest.getThreeMovies(0).get(0).getTitle().equals("The Insurrection")) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the Backend with 3 movies and selects 2 genres to
   * test whether the list returned by getGenres() has a size of 2. It passes 
   * when there are 3 ratings with the correct values and fails in all other 
   * cases.
   * @return true if the test passed, false if its failed
   */
  public boolean testGetGenres() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
          ));

      // add 3 genres to the genre list
      backendToTest.addGenre("Comedy");
      backendToTest.addGenre("Musical");
      backendToTest.addGenre("Action");

      if (backendToTest.getGenres().size() == 3
          && backendToTest.getGenres().contains("Comedy")
          && backendToTest.getGenres().contains("Musical")
          && backendToTest.getGenres().contains("Action")) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the Backend with 5 movies. By setting different choices
   * of genres and ratings, the method getNumberOfMovies() should return the expected
   * value. It passes if the number of movies returned are correct and fails in all
   * other cases.
   * @return true if the test passed, false if its failed
   */
  public boolean testNumberOfMovies() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Action\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
              + "Movie 1,Movie 1,2021,Family,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.2\n"
              + "Movie 2,Movie 1,2021,\"Comedy, Musical, Action\",83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",7.3\n"
          ));

      // scenario 1: rating only
      backendToTest.addAvgRating("3"); // add rating 3
      if (backendToTest.getNumberOfMovies() != 2) {
        // test failed
        return false;
      }
      backendToTest.addAvgRating("5"); // add rating 5
      if (backendToTest.getNumberOfMovies() != 3) {
        // test failed
        return false;
      }
      backendToTest.addAvgRating("9"); // add rating 9 but shouldn't affect
      if (backendToTest.getNumberOfMovies() != 3) {
        // test failed
        return false;
      }
      // remove all the ratings
      backendToTest.removeAvgRating("3");
      backendToTest.removeAvgRating("5");
      backendToTest.removeAvgRating("9");

      // scenario 2: genre only
      backendToTest.addGenre("Action"); // add genre Action
      if (backendToTest.getNumberOfMovies() != 3) {
        // test failed
        return false;
      }
      backendToTest.addGenre("Family"); // add genre Family and expect 0 movie returned
      if (backendToTest.getNumberOfMovies() != 0) {
        // test failed
        return false;
      }
      backendToTest.removeGenre("Family"); // remove genre Family
      backendToTest.addGenre("Comedy"); // add genre Comedy
      if (backendToTest.getNumberOfMovies() != 2) {
        // test failed
        return false;
      }

      // scenario 3: both rating and genres
      backendToTest.addAvgRating("5"); // add rating 5
      if (backendToTest.getNumberOfMovies() != 1) {
        // test failed
        return false;
      }
      backendToTest.addAvgRating("7"); // add rating 7
      if (backendToTest.getNumberOfMovies() != 2) {
        // test failed
        return false;
      }
      backendToTest.addAvgRating("3"); // add rating 3 but shouldn't affect
      if (backendToTest.getNumberOfMovies() != 2) {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    return true;
  }

  /**
   * This test instantiates the Backend with five movies and adds and removes
   * some genres to check if the result set has the expected size, and if the
   * target movies are thus selected. It passes if the genres are successfully 
   * added and removed, reflected by the correct size of the result set. It
   * passes if the number and content of the movies returned are correct and
   * fails in all other cases.
   * @return true if the test passed, false if its failed
   */
  public boolean testAddRemoveGenre() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,\"Action, Horror\",90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Action\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
              + "Movie 1,Movie 1,2021,Family,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.2\n"
              + "Movie 2,Movie 1,2021,\"Comedy, Musical, Action\",83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",7.3\n"
          ));

      List<String> titles = new ArrayList<String>();
      backendToTest.addGenre("Action"); // add genre Action
      // retrieve all the titles
      titles.add(backendToTest.getThreeMovies(0).get(0).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(1).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(2).getTitle());
      if (backendToTest.getThreeMovies(0).size() != 3
          || !titles.contains("Movie 2")
          || !titles.contains("The Insurrection")
          || !titles.contains("Valley Girl")) {
        // test failed
        return false;
      }

      titles = new ArrayList<String>(); // reset titles
      backendToTest.addGenre("Comedy"); // add genre Comedy
      // retrieve all the titles
      titles.add(backendToTest.getThreeMovies(0).get(0).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(1).getTitle());
      if (backendToTest.getThreeMovies(0).size() != 2
          || !titles.contains("Movie 2")
          || !titles.contains("Valley Girl")) {
        // test failed
        return false;
      }

      backendToTest.addGenre("Horror"); // add genre Horror and should have 0 result
      if (backendToTest.getThreeMovies(0).size() != 0) {
        // test failed
        return false;
      }

      titles = new ArrayList<String>(); // reset titles
      backendToTest.removeGenre("Comedy"); // remove genre Comedy
      // retrieve all the titles
      titles.add(backendToTest.getThreeMovies(0).get(0).getTitle());
      if (backendToTest.getThreeMovies(0).size() != 1
          || !titles.contains("The Insurrection")) {
        // test failed
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    return true;
  }

  /**
   * This test instantiates the Backend with five movies and adds and removes
   * some ratings to check if the result set has the expected size, and if the
   * target movies are thus selected. It passes if the ratings are successfully 
   * added and removed, reflected by the correct size of the result set. It
   * passes if the number and content of the movies returned are correct and
   * fails in all other cases.
   * @return true if the test passed, false if its failed
   */
  public boolean testAddRemoveRating() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,\"Action, Horror\",90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Action\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
              + "Movie 1,Movie 1,2021,Family,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.2\n"
              + "Movie 2,Movie 1,2021,\"Comedy, Musical, Action\",83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",7.3\n"
          ));

      List<String> titles = new ArrayList<String>();
      backendToTest.addAvgRating("3"); // add rating 3
      // retrieve all the titles
      titles.add(backendToTest.getThreeMovies(0).get(0).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(1).getTitle());
      if (backendToTest.getThreeMovies(0).size() != 2
          || !titles.contains("Movie 1")
          || !titles.contains("The Source of Shadows")) {
        // test failed
        return false;
      }

      titles = new ArrayList<String>(); // reset titles
      backendToTest.addAvgRating("5"); // add rating 5
      // retrieve all the titles
      titles.add(backendToTest.getThreeMovies(0).get(0).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(1).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(2).getTitle());
      if (backendToTest.getThreeMovies(0).size() != 3
          || !titles.contains("Movie 1")
          || !titles.contains("The Source of Shadows")
          || !titles.contains("Valley Girl")) {
        // test failed
        return false;
      }

      titles = new ArrayList<String>(); // reset titles
      backendToTest.addAvgRating("1"); // add rating 1 and should have no change
      // retrieve all the titles
      titles.add(backendToTest.getThreeMovies(0).get(0).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(1).getTitle());
      titles.add(backendToTest.getThreeMovies(0).get(2).getTitle());
      if (backendToTest.getThreeMovies(0).size() != 3
          || !titles.contains("Movie 1")
          || !titles.contains("The Source of Shadows")
          || !titles.contains("Valley Girl")) {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    return true;
  }
}