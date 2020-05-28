package sourcecodeanalyzerrefactored.metrciswriter;

import org.junit.Test;

import sourcecodeanalyzerrefactored.metricswriter.MetricsExporter;
import sourcecodeanalyzerrefactored.metricswriter.NullExporter;

public class NullExporterTest {
	MetricsExporter exporter = new NullExporter();
	
	@Test (expected = IllegalArgumentException.class)
	public void testEriteToFile() {
		exporter.writeToFile("any-filepath", null);
	}
	
	
}
