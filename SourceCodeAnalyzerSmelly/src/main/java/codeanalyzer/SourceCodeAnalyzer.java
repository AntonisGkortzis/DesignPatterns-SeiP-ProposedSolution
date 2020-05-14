package codeanalyzer;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Analyzes the contents of a Java source code file 
 * and calculates the following metrics: loc = lines of code,
 * nom = number of methods, and noc=number of classes. 
 * The current functionality supports two types of source code analysis,
 * namely regex (with the use of regular expressions) and 
 * strcomp (with the use of string comparison).
 * This class deliberately contains code smells and violations of design principles. 
 * @author agkortzis
 *
 */
public class SourceCodeAnalyzer {
	
	private SourceFileReader fileReader;
	
	public SourceCodeAnalyzer(String fileReaderType) {
		this.fileReader = new SourceFileReader(fileReaderType);
	}
		
	public int calculateLOC(String filepath, String analyzerType) throws IOException {
		if(analyzerType.equals("regex")) {
			String sourceCode = fileReader.readFileIntoString(filepath);
			Pattern pattern = Pattern.compile("((//.*)|(/\\*.*)|(\\*+.*))");
	        Matcher nonCodeLinesMatcher = pattern.matcher(sourceCode);

	        int nonCodeLinesCounter = 0;
	        while (nonCodeLinesMatcher.find()) {
	        	nonCodeLinesCounter++;
	        }
			
	        int sourceFileLength = sourceCode.split("\n").length;
	        int loc =  sourceFileLength - nonCodeLinesCounter;
	        
			return loc;
		} else if (analyzerType.equals("strcomp")) {
			List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
			int nonCodeLinesCounter = 0;
			for (String line : sourceCodeList) {
				line = line.trim(); //clear all leading and trailing white spaces
				if (line.startsWith("//") || line.startsWith("/*") || line.startsWith("*") || line.equals("{") || line.equals("}") || line.equals(""))
					nonCodeLinesCounter++;
			}
			int loc = sourceCodeList.size() - nonCodeLinesCounter;
			return loc; 
		}
		return -1;
	}
	
	public int calculateNOM(String filepath, String analyzerType) throws IOException {
		if(analyzerType.equals("regex")) {
			String sourceCode = fileReader.readFileIntoString(filepath);
			Pattern pattern = Pattern.compile(".*(public |protected |private |static )?[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;]).*"); 
	        Matcher methodSignatures = pattern.matcher(sourceCode);

	        int methodCounter = 0;
	        while (methodSignatures.find()) {
	        	methodCounter++;
	        }
			return methodCounter;
		} else if (analyzerType.equals("strcomp")) {
			List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
			int methodCounter = 0;
			for (String line : sourceCodeList) {
				line = line.trim(); //clear all leading and trailing white spaces
				if ( ((line.contains("public") || line.contains("private") || line.contains("protected"))
						|| line.contains("void") || line.contains("int") || line.contains("String"))
					&& line.contains("(") && line.contains(")") && line.contains("{") )
					methodCounter++;
			}
			return methodCounter; 
		}
		return -1;
	}
	
	public int calculateNOC(String filepath, String analyzerType) throws IOException {
		if(analyzerType.equals("regex")) {
			String sourceCode = fileReader.readFileIntoString(filepath);
			Pattern pattern = Pattern.compile(".*\\s*class\\s+.*"); 
	        Matcher classSignatures = pattern.matcher(sourceCode);

	        int classCounter = 0;
	        while (classSignatures.find()) {
	        	classCounter++;
	        }
			return classCounter;
		} else if (analyzerType.equals("strcomp")) {
			List<String> sourceCodeList = fileReader.readFileIntoList(filepath);
			int classCounter = 0;
			for (String line : sourceCodeList) {
				line = line.trim(); //remove leading and trailing white spaces
				if ((line.startsWith("class ") || line.contains(" class ")) && line.contains("{")) {
					classCounter++;
				}
			}
			return classCounter; 
		}
		return -1;
	}
}
