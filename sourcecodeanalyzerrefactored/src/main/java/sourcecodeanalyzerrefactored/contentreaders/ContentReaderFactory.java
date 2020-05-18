package sourcecodeanalyzerrefactored.contentreaders;

public class ContentReaderFactory {
	
	public ContentReader createContentReader(String type) {
		if (type.equals("local")) {
			return new LocalFileReader();
		} else if (type.equals("web")) {
			return new WebFileReader();
		} else {
			return new NullFileReader();
		}
	}
}
