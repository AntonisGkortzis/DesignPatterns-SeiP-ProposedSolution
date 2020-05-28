package sourcecodeanalyzerrefactored.analyzer;

import org.junit.Test;

import sourcecodeanalyzerrefactored.contentreader.ContentReader;
import sourcecodeanalyzerrefactored.contentreader.LocalFileReader;
import static org.junit.Assert.*;

public class SourceCodeAnalyzerTest {
	
	@Test
	public void testSetContentReader() {
		SourceCodeAnalyzer analyzer = new StringComparisonAnalyzer(); // any analyzer can be used
		ContentReader expectedReader = new LocalFileReader(); // any reader can be used
		
		analyzer.setContentReader(expectedReader);
		ContentReader actualReader = analyzer.getContentReader();
		
		assertSame(expectedReader, actualReader);
	}
}
