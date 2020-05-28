/**
 * 
 */
package sourcecodeanalyzerrefactored.contentreader;

import java.util.List;

/**
 * Defines the "Nothing" for any undefined type of ContentReader.
 * 
 * @author agkortzis
 *
 */
public class NullFileReader implements ContentReader {

	@Override
	public String readFileForRegex(String filePath) {
		throw new IllegalArgumentException("Cannot execute process due to unknown file reader type. Process terminated.");
	}

	@Override
	public List<String> readFileForStrComp(String filePath) {
		throw new IllegalArgumentException("Cannot execute process due to unknown file reader type. Process terminated.");
	}

}
