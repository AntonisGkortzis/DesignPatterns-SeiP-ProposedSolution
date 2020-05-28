package sourcecodeanalyzerrefactored.metrciswriter;

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

import sourcecodeanalyzerrefactored.metricswriter.CSVExporter;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporter;

public class CSVExporterTest {

	MetricsExporter exporter = new CSVExporter();
	private static String OUTPUT_FILEPATH = "metrics_results";

	@Test
	public void testWriteToFile() throws IOException {
		File outputFile = new File(OUTPUT_FILEPATH + ".csv");

		// delete the output files if it does pre-exist
		if (outputFile.exists())
			outputFile.delete();

		Map<String, Integer> metrics = new HashMap<>();
		metrics.put("loc", 21);
		metrics.put("nom", 3);
		metrics.put("noc", 3);
		exporter.writeToFile(OUTPUT_FILEPATH, metrics);

		// verify that the output file was created successfully
		try {
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