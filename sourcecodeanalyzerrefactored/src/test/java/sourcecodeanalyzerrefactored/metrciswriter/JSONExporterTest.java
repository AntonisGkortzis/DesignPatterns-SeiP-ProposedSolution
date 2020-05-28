package sourcecodeanalyzerrefactored.metrciswriter;

import org.junit.Test;

import sourcecodeanalyzerrefactored.metricswriter.JSONExporter;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporter;

public class JSONExporterTest {
	MetricsExporter exporter = new JSONExporter();

	@Test (expected = IllegalArgumentException.class)
	public void testWriteToFile() {
		exporter.writeToFile("any-file-path", null);
	}

}
