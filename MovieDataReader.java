// --== CS400 File Header Information ==--
// Name: Gahan Sudhir
// Email: gsudhir@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: Changed FileReader inputFileReader to Reader inputFileReader in order to
// accommodate for StringReader in test cases

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {

  @Override
  /**
   * This method returns a list of movie objects
   * 
   * @return List<MovieInterface> of movies from parameter inputFileReader
   */
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException, DataFormatException {
    List<MovieInterface> movies = new ArrayList<MovieInterface>();
    int count = 0;
    int titleInd = -1, yearInd = -1, directorInd = -1, descriptInd = -1, genresInd = -1,
        voteInd = -1, headerLength = -1;
    String line = "";
    BufferedReader br = new BufferedReader(inputFileReader);
    while ((line = br.readLine()) != null) {
      if (count == 0) {
        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // deals with quotes
                                                                               // within quotes
        headerLength = values.length;
        for (int i = 0; i < values.length; i++) { // first loop establishes indexes to find
          if (values[i].equals("title")) {
            titleInd = i;
          } else if (values[i].contains("year")) {
            yearInd = i;
          } else if (values[i].contains("director")) {
            directorInd = i;
          } else if (values[i].contains("description")) {
            descriptInd = i;
          } else if (values[i].contains("vote")) {
            voteInd = i;
          } else if (values[i].contains("genre")) {
            genresInd = i;
          }
        }
        if (titleInd == -1 || yearInd == -1 || directorInd == -1 || genresInd == -1
            || voteInd == -1) {
          throw new DataFormatException();
        }
      }
      if (count > 0) {
        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // deals with quotes
                                                                               // within quotes
        if (values.length != headerLength)
          throw new DataFormatException();
        movies.add(new Movie(values[titleInd], values[yearInd], values[directorInd],
            values[descriptInd], values[genresInd], values[voteInd]));
      }
      count++;
    }
    return movies;
  }

}
