package sourcecodeanalyzerrefactored;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import sourcecodeanalyzerrefactored.analyzer.SourceCodeAnalyzer;
import sourcecodeanalyzerrefactored.analyzer.SourceCodeAnalyzerFactory;
import sourcecodeanalyzerrefactored.contentreader.ContentReader;
import sourcecodeanalyzerrefactored.contentreader.ContentReaderFactory;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporter;
import sourcecodeanalyzerrefactored.metricswriter.MetricsExporterFactory;

/**
 * A class that behaves as an entry point for the rest of the system. Provides a single interface (entry-point)
 * through which any Client can make use of the system's functionality without knowing the implementation
 * details of it. 
 * @author agkortzis
 *
 */
public class SourceCodeAnalyzerFacade {
	
	public void performFileAnalysis(String filepath, String analysisType, String fileLocation, String outputFileName, String outputFileType) throws IOException {
		
		// create a factory that is responsible for creating a concrete ContentReader
		ContentReaderFactory readerFactory = new ContentReaderFactory();
		// ask the factory to create and return a concrete ContentReader
		ContentReader contentReader = readerFactory.createContentReader(fileLocation);
		
		// create a factory that is responsible for creating a concrete SourceCodeAnalyzer
		SourceCodeAnalyzerFactory analyzerFactory = new SourceCodeAnalyzerFactory();
		// ask the factory to create and return a concrete SourceCodeAnalyzer 
		SourceCodeAnalyzer analyzer = analyzerFactory.createSourceCodeAnalyzer(analysisType);
		// inject the content reader into the analyzer
		analyzer.setContentReader(contentReader);
		
		// perform the analysis
		int loc = analyzer.calculateLOC(filepath);
		int nom = analyzer.calculateNOM(filepath);
		int noc = analyzer.calculateNOC(filepath);
		
		// store metrics in a HashMap
		Map<String,Integer> metrics = new HashMap<>();
		metrics.put("loc", loc);
		metrics.put("nom", nom);
		metrics.put("noc", noc);
		
		// create a factory that is responsible for creating a concrete MetricsExporter
		MetricsExporterFactory exporterFactory = new MetricsExporterFactory();
		// ask the factory to create and return a concrete MetricsExporter
		MetricsExporter metricsExporter = exporterFactory.createMetricsExporter(outputFileType);
		
		// export metrics to a file
		metricsExporter.writeToFile(outputFileName, metrics);
	}
	
}
