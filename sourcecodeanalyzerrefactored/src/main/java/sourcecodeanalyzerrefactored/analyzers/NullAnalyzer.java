package sourcecodeanalyzerrefactored.analyzers;

import java.io.IOException;

public class NullAnalyzer extends SourceCodeAnalyzer {

	@Override
	public int calculateLOC(String filepath) throws IOException {
		throw new IllegalArgumentException("Unknown type of Analyzer, returning default -1.");
	}

	@Override
	public int calculateNOM(String filepath) throws IOException {
		throw new IllegalArgumentException("Unknown type of Analyzer, returning default -1.");
	}

	@Override
	public int calculateNOC(String filepath) throws IOException {
		throw new IllegalArgumentException("Unknown type of Analyzer, returning default -1.");
	}

}
