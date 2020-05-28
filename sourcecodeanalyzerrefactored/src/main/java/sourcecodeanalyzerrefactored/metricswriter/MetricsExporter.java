package sourcecodeanalyzerrefactored.metricswriter;

import java.util.Map;

/**
 * Defines the method for concrete Exporters to implement in order 
 * format the output into the required output type and write it to a file.
 * 
 * @author agkortzis
 */
public interface MetricsExporter {
	
	public void writeToFile(String filePath, Map<String,Integer> metrics);

}
