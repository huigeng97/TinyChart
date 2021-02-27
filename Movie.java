import java.util.List;
import java.util.ArrayList;

public class Movie implements MovieInterface {

  private String title;
  private Integer year;
  private String director;
  private String description;
  private List<String> genres;
  private float avgVote;
  
  public Movie (String title, String year, String director, String description, String genres, String avgVote) {
    this.title = title;
    this.year = Integer.parseInt(year);
    this.director = director;
    this.description = description;
    genres = genres.replace("\"", "");
    String[] gen = genres.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    this.genres = new ArrayList<String>();
    for (int i = 0; i < gen.length; i++) {
      this.genres.add(gen[i]);
    }
    this.avgVote = Float.parseFloat(avgVote);
  }
  
  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public Integer getYear() {
    return year;
  }

  @Override
  public List<String> getGenres() {
    return genres;
  }

  @Override
  public String getDirector() {
    return director;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Float getAvgVote() {
    return avgVote;
  }

  @Override
  public int compareTo(MovieInterface otherMovie) {
    if (avgVote-otherMovie.getAvgVote()>0)
      return 1;
    else if (avgVote-otherMovie.getAvgVote()==0)
      return 0;
    else
      return -1;
  }

}
