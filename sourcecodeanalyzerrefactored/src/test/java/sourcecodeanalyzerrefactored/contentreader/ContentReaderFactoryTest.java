package sourcecodeanalyzerrefactored.contentreader;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContentReaderFactoryTest {
	
	private static String LOCAL_TYPE = "local";
	private static String WEB_TYPE = "web";
	ContentReaderFactory factory = new ContentReaderFactory();
	
	@Test
	public void testCreateLocalFileReader() {
		ContentReader reader = factory.createContentReader(LOCAL_TYPE);
		assertTrue(reader instanceof LocalFileReader);
	}
	
	@Test
	public void testCreateWebFileReader() {
		ContentReader reader = factory.createContentReader(WEB_TYPE);
		assertTrue(reader instanceof WebFileReader);
	}
	
	@Test
	public void testCreateNullFileReader() {
		ContentReader reader = factory.createContentReader("any-non-existing-type");
		assertTrue(reader instanceof NullFileReader);
	}
}
