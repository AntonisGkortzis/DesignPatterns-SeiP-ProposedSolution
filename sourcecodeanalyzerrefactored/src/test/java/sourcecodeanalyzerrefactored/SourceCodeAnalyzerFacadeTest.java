package sourcecodeanalyzerrefactored;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import sourcecodeanalyzerrefactored.analyzer.SourceCodeAnalyzer;
import sourcecodeanalyzerrefactored.analyzer.SourceCodeAnalyzerFactory;
import sourcecodeanalyzerrefactored.contentreader.ContentReader;
import sourcecodeanalyzerrefactored.contentreader.ContentReaderFactory;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporter;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporterFactory;


public class SourceCodeAnalyzerFacadeTest {
	
	private final static String TEST_CLASS_LOCAL = "src/test/resources/TestClass.java";
	private final static String FILE_LOCATION_TYPE = "local";
	private final static String ANALYSIS_TYPE = "regex";
	private final static String OUTPUT_FILE = "output_results";
	private final static String EXPORT_TYPE = "csv";
	
	
	
	@Test
	public void testPerformFileAnalysisWithMocks() throws IOException {
		MetricsExporter exporter = mock(MetricsExporter.class);
		ContentReader reader = mock(ContentReader.class);
		SourceCodeAnalyzer analyzer = mock(SourceCodeAnalyzer.class);

		when(reader.readFileForRegex(TEST_CLASS_LOCAL)).thenReturn(new String());

		when(analyzer.calculateLOC(TEST_CLASS_LOCAL)).thenReturn(21);
		when(analyzer.calculateNOM(TEST_CLASS_LOCAL)).thenReturn(3);
		when(analyzer.calculateNOC(TEST_CLASS_LOCAL)).thenReturn(3);
		
		MetricsExporterFactory exporterFactory = mock(MetricsExporterFactory.class);
		ContentReaderFactory readerFactory = mock(ContentReaderFactory.class);
		SourceCodeAnalyzerFactory analyzerFactory= mock(SourceCodeAnalyzerFactory.class);

		when(exporterFactory.createMetricsExporter(EXPORT_TYPE)).thenReturn(exporter);
		when(readerFactory.createContentReader(FILE_LOCATION_TYPE)).thenReturn(reader);
		when(analyzerFactory.createSourceCodeAnalyzer(ANALYSIS_TYPE)).thenReturn(analyzer);
		
		SourceCodeAnalyzerFacade codeAnalyzer = new SourceCodeAnalyzerFacade(readerFactory, analyzerFactory, exporterFactory);
		codeAnalyzer.performFileAnalysis(
				TEST_CLASS_LOCAL, 
				ANALYSIS_TYPE, 
				FILE_LOCATION_TYPE, 
				OUTPUT_FILE, 
				EXPORT_TYPE);
		
		// verify that all operations (methods) in the flow of performFileAnalysis are executed (called)
		verify(readerFactory).createContentReader(FILE_LOCATION_TYPE);
		verify(exporterFactory).createMetricsExporter(EXPORT_TYPE);
		verify(analyzerFactory).createSourceCodeAnalyzer(ANALYSIS_TYPE);
		
		verify(analyzer).setContentReader(reader);
		
		verify(analyzer).calculateLOC(TEST_CLASS_LOCAL);
		verify(analyzer).calculateNOM(TEST_CLASS_LOCAL);
		verify(analyzer).calculateNOC(TEST_CLASS_LOCAL);
		
		Map<String,Integer> metrics = new HashMap<>();
		metrics.put("loc", 21);
		metrics.put("nom", 3);
		metrics.put("noc", 3);
		
		verify(exporter).writeToFile(OUTPUT_FILE, metrics);
	}
	
	/*
	 * The following test case behaves more like an integration test of the systems' modules than like
	 * an independent unit-test of a fine-grained specific functionality since dependencies are not 
	 * mocked and thus, their behavior cannot be defined.
	 */
	@Test
	public void testPerformFileAnalysisWithoutInjectingDependencies() throws IOException {
		File outputFile = new File(OUTPUT_FILE + ".csv");
		if(outputFile.exists())
			outputFile.delete();
		
		SourceCodeAnalyzerFacade codeAnalyzer = new SourceCodeAnalyzerFacade();
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
