/**
 * 
 */
package sourcecodeanalyzerrefactored.metricswriters;

import java.util.Map;

/**
 * @author agkortzis
 *
 */
public class JSONExporter implements MetricsExporter {

	@Override
	public void writeToFile(String filePath, Map<String, Integer> metrics) {
		throw new IllegalAccessError("Exporting to JSON is not implemented");
	}

}
