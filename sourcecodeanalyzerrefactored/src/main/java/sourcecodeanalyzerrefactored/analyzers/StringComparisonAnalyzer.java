package sourcecodeanalyzerrefactored.analyzers;

import java.io.IOException;
import java.util.List;

public class StringComparisonAnalyzer extends SourceCodeAnalyzer {

	@Override
	public int calculateLOC(String filepath) throws IOException {
		List<String> sourceCodeList = contentReader.readFileForStrComp(filepath);
		int nonCodeLinesCounter = 0;
		for (String line : sourceCodeList) {
			line = line.trim(); // clear all leading and trailing white spaces
			if (line.startsWith("//") || line.startsWith("/*") || line.startsWith("*") || line.equals("{")
					|| line.equals("}") || line.equals(""))
				nonCodeLinesCounter++;
		}
		int loc = sourceCodeList.size() - nonCodeLinesCounter;
		return loc;
	}

	@Override
	public int calculateNOM(String filepath) throws IOException {
		List<String> sourceCodeList = contentReader.readFileForStrComp(filepath);
		int methodCounter = 0;
		for (String line : sourceCodeList) {
			line = line.trim();
			// clear all leading and trailing white spaces
			if (((line.contains("public") || line.contains("private") || line.contains("protected"))
					|| line.contains("void") || line.contains("int") || line.contains("String")) && line.contains("(")
					&& line.contains(")") && line.contains("{"))
				methodCounter++;
		}
		return methodCounter;
	}

	@Override
	public int calculateNOC(String filepath) throws IOException {
		List<String> sourceCodeList = contentReader.readFileForStrComp(filepath);
		int classCounter = 0;
		for (String line : sourceCodeList) {
			line = line.trim();
			// remove leading and trailing white spaces
			if ((line.startsWith("class ") || line.contains(" class ")) && line.contains("{")) {
				classCounter++;
			}
		}
		return classCounter;
	}

}
