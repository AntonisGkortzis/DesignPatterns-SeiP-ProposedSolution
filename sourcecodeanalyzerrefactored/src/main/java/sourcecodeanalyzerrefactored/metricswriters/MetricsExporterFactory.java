package sourcecodeanalyzerrefactored.metricswriters;

public class MetricsExporterFactory {
	
	public MetricsExporter createMetricsExporter(String type) {
		if (type.equals("csv")) {
			return new CVSExporter();
		} else if(type.equals("json")) {
			return new JSONExporter();
		} else {
			return new NullExporter();
		}
	}
	
}
