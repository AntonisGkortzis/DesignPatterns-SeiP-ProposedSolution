package sourcecodeanalyzerrefactored.metricswriter;

/**
 * This class is responsible for instantiating and returning 
 * a concrete type of MetricsExporter
 * 
 * @author agkortzis
 */
public class MetricsExporterFactory {
	
	public MetricsExporter createMetricsExporter(String type) {
		if (type.equals("csv")) {
			return new CSVExporter();
		} else if(type.equals("json")) {
			return new JSONExporter();
		} else {
			return new NullExporter();
		}
	}
	
}
