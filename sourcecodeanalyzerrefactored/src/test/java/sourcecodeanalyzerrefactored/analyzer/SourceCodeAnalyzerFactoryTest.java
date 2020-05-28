package sourcecodeanalyzerrefactored.analyzer;

import org.junit.Test;
import static org.junit.Assert.*;

public class SourceCodeAnalyzerFactoryTest {
	
	private static String REGEX_TYPE = "regex";
	private static String STRCOMP_TYPE = "strcomp";
	private SourceCodeAnalyzerFactory factory = new SourceCodeAnalyzerFactory();
	
	
	@Test
	public void testCreateRegexSourceCodeAnalyzer() {
		SourceCodeAnalyzer analyzer = factory.createSourceCodeAnalyzer(REGEX_TYPE);
		assertTrue(analyzer instanceof RegexAnalyzer);
	}
	
	@Test
	public void testCreateStrCompSourceCodeAnalyzer() {
		SourceCodeAnalyzer analyzer = factory.createSourceCodeAnalyzer(STRCOMP_TYPE);
		assertTrue(analyzer instanceof StringComparisonAnalyzer);
	}
	
	@Test
	public void testCreateNullSourceCodeAnalyzer() {
		SourceCodeAnalyzer analyzer = factory.createSourceCodeAnalyzer("any-non-existing-type");
		assertTrue(analyzer instanceof NullAnalyzer);
	}


}
