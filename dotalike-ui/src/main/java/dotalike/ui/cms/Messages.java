package dotalike.ui.cms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Messages {
    
    private static final String FILENAME = "messages";
    
    private static final String DEFAULT_LOCALE = "en";
    
    public static String get(String key) {
	String retVal = key;
	InputStream inputStream = null;
	try {
	    Properties prop = new Properties();
	    String fileName = getFileName();

	    inputStream = Messages.class.getClassLoader().getResourceAsStream(fileName);

	    if (inputStream != null) {
		prop.load(inputStream);
	    } else {
		throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
	    }
	} catch (Exception e) {
	    e.printStackTrace();

	} finally {
	    try {
		inputStream.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return retVal;
    }
    
    private static String getFileName() {
	return FILENAME + "." + DEFAULT_LOCALE;
    }
}
