/**
 * 
 */
package sourcecodeanalyzerrefactored.metricswriters;

import java.util.Map;

/**
 * @author agkortzis
 *
 */
public class NullExporter implements MetricsExporter {

	@Override
	public void writeToFile(String filePath, Map<String, Integer> metrics) {
		throw new IllegalArgumentException("Unknown eport type, process terminated");
	}

}
