package sourcecodeanalyzerrefactored.contentreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads file stored on the web and returns its content as a single String or
 * a list of Strings depending on the requirements.
 * 
 * @author agkortzis
 *
 */
public class WebFileReader implements ContentReader {

	@Override
	public String readFileForRegex(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
        URL url = new URL(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
        	sb.append(line + "\n");
        }
        reader.close();
		return sb.toString();
	}

	@Override
	public List<String> readFileForStrComp(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
        URL url = new URL(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
        	lines.add(line);
        }
        reader.close();
		return lines;
	}

}
