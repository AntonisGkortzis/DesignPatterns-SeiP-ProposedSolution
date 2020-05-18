package sourcecodeanalyzerrefactored.contentreaders;

import java.io.IOException;
import java.util.List;

public interface ContentReader {

	public String readFileForRegex(String filePath) throws IOException;

	public List<String> readFileForStrComp(String filePath) throws IOException;

}
