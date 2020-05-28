package sourcecodeanalyzerrefactored.contentreader;

import java.io.IOException;
import java.util.List;

/**
 * Defines the methods for concrete Readers to implement in order 
 * to read a file and return its content as a single String or
 * a list of Strings depending on the requirements.
 * 
 * @author agkortzis
 */
public interface ContentReader {

	public String readFileForRegex(String filePath) throws IOException;

	public List<String> readFileForStrComp(String filePath) throws IOException;

}
