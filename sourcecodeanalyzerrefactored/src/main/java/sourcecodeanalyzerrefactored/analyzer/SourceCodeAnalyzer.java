package sourcecodeanalyzerrefactored.analyzer;


import java.io.IOException;

import sourcecodeanalyzerrefactored.contentreader.ContentReader;

/**
 * Defines the methods for other SourceCodeAnalyzers to implement 
 * in order to calculate a set of source code metrics.
 * 
 * @author agkortzis
 *
 */
public abstract class SourceCodeAnalyzer {
	
	protected ContentReader contentReader;
	
	public void setContentReader(ContentReader contentReader) {
		this.contentReader = contentReader;
	}

	public ContentReader getContentReader() {
		return contentReader;
	}

	// Sublclasses are obliged to @Override the abstract methods
	public abstract int calculateLOC(String filepath) throws IOException;
	
	public abstract int calculateNOM(String filepath) throws IOException;
	
	public abstract int calculateNOC(String filepath) throws IOException;

}