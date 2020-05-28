package sourcecodeanalyzerrefactored.metricswriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Format a list of metrics into a valid CSV format and exports it to a file
 * 
 * @author agkortzis
 */
public class CSVExporter implements MetricsExporter {

	@Override
	public void writeToFile(String filePath, Map<String, Integer> metrics) {
		File outputFile = new File(filePath + ".csv");
		StringBuilder metricsNames = new StringBuilder();
		StringBuilder metricsValues = new StringBuilder();

		for (Map.Entry<String, Integer> entry : metrics.entrySet()) {
			metricsNames.append(entry.getKey() + ",");
			metricsValues.append(entry.getValue() + ",");
		}

		try {
			FileWriter writer = new FileWriter(outputFile);
			writer.append(metricsNames + "\n");
			writer.append(metricsValues + "\n");
			writer.close();
			System.out.println("Metrics saved in " + outputFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
