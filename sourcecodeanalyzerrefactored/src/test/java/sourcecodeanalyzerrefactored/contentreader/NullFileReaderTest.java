package sourcecodeanalyzerrefactored.contentreader;

import java.io.IOException;

import org.junit.Test;

public class NullFileReaderTest {

	ContentReader reader = new NullFileReader();
	
	@Test (expected = IllegalArgumentException.class)
	public void testReadFileForRegex() throws IOException {
		reader.readFileForRegex("any-filepath");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testReadFileForStrComp() throws IOException {
		reader.readFileForStrComp("any-filepath");
	}
}
