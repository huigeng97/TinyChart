import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {

  @Override
  public List<MovieInterface> readDataSet(FileReader inputFileReader)
      throws FileNotFoundException, IOException, DataFormatException {
    List<MovieInterface> movies = new ArrayList<MovieInterface>();
    int count = 0;
    int titleInd = -1, yearInd = -1, directorInd = -1, descriptInd = -1, genresInd = -1,
        voteInd = -1, headerLength = -1;
    String line = "";
    BufferedReader br = new BufferedReader(inputFileReader);
    while ((line = br.readLine()) != null) {
      if (count == 0) {
        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        headerLength = values.length;
        for (int i = 0; i < values.length; i++) {
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
        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (values.length != headerLength)
          throw new DataFormatException();
        movies.add(new Movie(values[titleInd], values[yearInd], values[directorInd],
            values[descriptInd], values[genresInd], values[voteInd]));
        System.out.println(movies.get(count-1));
      }
      count++;
    }
    return movies;
  }

  public static void main(String[] args) {
    String path = "\\Users\\sudhi\\Downloads\\movies.csv";
    MovieDataReader mdr = new MovieDataReader();
    try {
      mdr.readDataSet(new FileReader(path));
    } catch (Exception e) {
      System.out.println("Error found!");
    }
  }

}
