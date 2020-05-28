/**
 * 
 */
package sourcecodeanalyzerrefactored.metricswriter;

import java.util.Map;

/**
 * Format a list of metrics into a valid JSON format and exports it to a file
 * (not implemented)
 * 
 * @author agkortzis
 */
public class JSONExporter implements MetricsExporter {

	@Override
	public void writeToFile(String filePath, Map<String, Integer> metrics) {
		throw new IllegalArgumentException("Exporting to JSON is not implemented");
	}

}
