package sourcecodeanalyzerrefactored.contentreaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalFileReader implements ContentReader {

	@Override
	public String readFileForRegex(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		File file = new File(filePath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
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
		File file = new File(filePath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}

}
