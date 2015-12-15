package dotadodge.core;

import org.junit.Ignore;
import org.junit.Test;

import dotadodge.core.file.ServerLogParser;

public class ServerLogParserTest {
    
    @Test
    public void testOneTime() {
	ServerLogParser parser = new ServerLogParser();
	parser.parseOneTime();
    }
    
    
    @Ignore
    @Test
    public void testForever() throws InterruptedException {
	final ServerLogParser parser = new ServerLogParser();
	
	Thread threadCore = new Thread() {
	    public void run() {
		parser.parseForever();
	    };
	};
	
	Thread threadUI = new Thread() {
	    public void run() {
		while (true) {
		    //System.out.println("Match from UI: " + parser.getCurrentMatch());
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    };
	};
	
	threadCore.start();
	threadUI.start();
	// modify server_log.txt manually and see in console. 10 sec max
	Thread.sleep(10 * 1000);
    }

}
