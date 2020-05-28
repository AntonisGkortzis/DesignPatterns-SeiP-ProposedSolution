package sourcecodeanalyzerrefactored;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/*
 * The Facade test behaves more like an integration test of the systems' modules than like
 * an independent unit-test of a fine-grained specific functionality. 
 */
public class SourceCodeAnalyzerFacadeTest {
	
	private final static String TEST_CLASS_LOCAL = "src/test/resources/TestClass.java";
	private final static String FILE_LOCATION_TYPE = "local";
	private final static String ANALYSIS_TYPE = "regex";
	private final static String OUTPUT_FILE = "output_results";
	private final static String EXPORT_TYPE = "csv";
	SourceCodeAnalyzerFacade codeAnalyzer = new SourceCodeAnalyzerFacade();
	
	@Test
	public void testHappyRunForAnalyzer() throws IOException {
		File outputFile = new File(OUTPUT_FILE + ".csv");
		if(outputFile.exists())
			outputFile.delete();
		
		try {
			codeAnalyzer.performFileAnalysis(
					TEST_CLASS_LOCAL, 
					ANALYSIS_TYPE, 
					FILE_LOCATION_TYPE, 
					OUTPUT_FILE, 
					EXPORT_TYPE);
			
			assertTrue(outputFile.exists());
			
			String[] expectedContent = {"loc,noc,nom,", "21,3,3,"};
			String[] actualContent = readTestFileContentIntoList(outputFile.getAbsolutePath());
			
			assertArrayEquals(expectedContent, actualContent);
			
		} finally {
			outputFile.delete();
		}
	}
	
	// For greater test-case independence you can give a manually created list of Strings
	private String[] readTestFileContentIntoList(String filepath) throws IOException {
		List<String> sourceCodeLines;
		try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
			sourceCodeLines = lines.collect(Collectors.toList());
		}
		return sourceCodeLines.stream().toArray(String[]::new);
	}
}
