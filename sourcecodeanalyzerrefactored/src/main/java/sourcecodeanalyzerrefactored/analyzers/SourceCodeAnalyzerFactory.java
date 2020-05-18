package sourcecodeanalyzerrefactored.analyzers;

public class SourceCodeAnalyzerFactory {
	
	public SourceCodeAnalyzer createSourceCodeAnalyzer(String type) {
		if (type.equals("regex")) {
			return new RegexAnalyzer();
		} else if (type.equals("strcomp")) {
			return new StringComparisonAnalyzer();
		} else {
			return new NullAnalyzer();
		}
	}

}
