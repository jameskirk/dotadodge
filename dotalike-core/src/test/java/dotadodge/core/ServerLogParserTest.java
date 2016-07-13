package dotadodge.core;

import org.junit.Test;

import dotadodge.core.file.MatchNotStartedException;
import dotadodge.core.file.ServerLogParser;

public class ServerLogParserTest {
    
    @Test
    public void testOneTime() throws MatchNotStartedException {
	ServerLogParser parser = new ServerLogParser();
	parser.parse();
    }
    
}
