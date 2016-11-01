package dotadodge.core;

import java.io.FileNotFoundException;

import org.junit.Test;

import dotalike.core.file.ServerLogParser;
import dotalike.core.misc.MatchNotStartedException;

public class ServerLogParserTest {
    
    @Test
	public void testOneTime() throws MatchNotStartedException, FileNotFoundException {
		ServerLogParser parser = new ServerLogParser();
		parser.parse();
	}

}
