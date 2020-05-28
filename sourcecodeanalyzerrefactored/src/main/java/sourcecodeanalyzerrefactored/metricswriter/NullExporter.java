package sourcecodeanalyzerrefactored.metricswriter;

import java.util.Map;

/**
 * This class defines the "Nothing" for any undefined type of MetricsExporter.
 * 
 * @author agkortzis
 *
 */
public class NullExporter implements MetricsExporter {

	@Override
	public void writeToFile(String filePath, Map<String, Integer> metrics) {
		throw new IllegalArgumentException("Unknown eport type, process terminated");
	}

}
