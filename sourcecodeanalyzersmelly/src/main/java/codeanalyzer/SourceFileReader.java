package codeanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves (reads) the contents of a given file.
 * The file can be stored locally or exist on the web.
 * This class deliberately contains code smells and violations of design principles. 
 * @author agkortzis
 *
 */
public class SourceFileReader {
	
	private String type;
	
	public SourceFileReader(String _type) {
		this.type = _type;
	}

	/**
	 * Reads a file and returns its content in a List
	 * @param fileReaderType the location of a file 
	 * (<b>local</b> for locally stored files, 
	 * <b>web</b> for files stored on the web). 
	 * @param filepath the url of the file
	 * @return a List that contains the contents of the file 
	 * or null if the type is neither <b>local</b> nor <b>web</b>
	 * @throws IOException
	 */
	public List<String> readFileIntoList(String filepath) throws IOException {
		// read a locally stored file
		if (type.contentEquals("local")) {
			List<String> lines = new ArrayList<>();
			File file = new File(filepath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
			return lines;
		// read a file stored in the web
		} else if (type.contentEquals("web")) {
			List<String> lines = new ArrayList<>();
	        URL url = new URL(filepath);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	        	lines.add(line);
	        }
	        reader.close();
			return lines;
		} else {
			return null;
		}
	}
	
	/**
	 * Reads a file and returns its content in a single String
	 * @param fileReaderType the location of a file 
	 * (<b>local</b> for locally stored files, 
	 * <b>web</b> for files stored on the web). 
	 * @param filepath the url of the file
	 * @return a String that contains the contents of the file
	 * or null if the type is neither <b>local</b> nor <b>web</b>
	 * @throws IOException
	 */
	public String readFileIntoString(String filepath) throws IOException {
		// read a locally stored file
		if (type.contentEquals("local")) {
			StringBuilder sb = new StringBuilder();
			File file = new File(filepath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
			return sb.toString();
		// read a file stored in the web
		} else if (type.contentEquals("web")) {
			StringBuilder sb = new StringBuilder();
	        URL url = new URL(filepath);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	        	sb.append(line + "\n");
	        }
	        reader.close();
			return sb.toString();
		} else {
			return null;
		}
	}

}
