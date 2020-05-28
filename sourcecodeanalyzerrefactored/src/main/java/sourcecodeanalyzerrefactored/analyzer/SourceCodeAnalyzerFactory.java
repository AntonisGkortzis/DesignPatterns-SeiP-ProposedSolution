package sourcecodeanalyzerrefactored.analyzer;

/**
 * This class is responsible for instantiating and returning 
 * a concrete type of SourceCodeAnalazyer
 * 
 * @author agkortzis
 */
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
