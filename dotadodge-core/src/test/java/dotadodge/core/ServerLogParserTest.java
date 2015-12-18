package dotadodge.core;

import org.junit.Test;

import dotadodge.core.file.ServerLogParser;

public class ServerLogParserTest {
    
    @Test
    public void testOneTime() {
	ServerLogParser parser = new ServerLogParser();
	parser.parse();
    }
    
}
