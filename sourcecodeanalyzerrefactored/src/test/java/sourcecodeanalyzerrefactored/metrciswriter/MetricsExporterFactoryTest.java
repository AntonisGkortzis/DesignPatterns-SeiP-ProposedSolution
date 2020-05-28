package sourcecodeanalyzerrefactored.metrciswriter;

import org.junit.Test;
import static org.junit.Assert.*;

import sourcecodeanalyzerrefactored.metricswriter.CSVExporter;
import sourcecodeanalyzerrefactored.metricswriter.JSONExporter;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporter;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporterFactory;
import sourcecodeanalyzerrefactored.metricswriter.NullExporter;

public class MetricsExporterFactoryTest {
	
	private static String CSV_TYPE = "csv";
	private static String JSON_TYPE = "json";
	MetricsExporterFactory factory = new MetricsExporterFactory();
	
	@Test
	public void testCreateCsvExporter() {
		MetricsExporter writer = factory.createMetricsExporter(CSV_TYPE);
		assertTrue(writer instanceof CSVExporter);
	}
	
	@Test
	public void testCreateJsonExporter() {
		MetricsExporter writer = factory.createMetricsExporter(JSON_TYPE);
		assertTrue(writer instanceof JSONExporter);
	}

	@Test
	public void testCreateNullExporter() {
		MetricsExporter writer = factory.createMetricsExporter("any-non-existing-export-type");
		assertTrue(writer instanceof NullExporter);
	}
}
