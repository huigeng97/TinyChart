import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

public interface MovieDataReaderInterface {
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException, DataFormatException;
}
