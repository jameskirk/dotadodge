package dotadodge.core.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotadodge.core.misc.Configuration;
import dotadodge.core.model.Match;
import dotadodge.core.model.Player;

public class ServerLogParser {
    
    private final Logger logger = LoggerFactory.getLogger(ServerLogParser.class);
    
    @Deprecated
    private Match currentMatch;
    
    @Deprecated
    public void parseForever() {
	currentMatch = parseOneTime();
	try {
	    String dirServerLog = getPathToServiceLog().substring(0, getPathToServiceLog().lastIndexOf("/"));
	    
	    WatchService watcher = FileSystems.getDefault().newWatchService();
	    Path dir = Paths.get(dirServerLog);
	    dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
	    while (true) {
		    WatchKey key;
		    try {
		        // wait for a key to be available
			
		        key = watcher.take();
		    } catch (InterruptedException e) {
		        throw new RuntimeException(e);
		    }
		    
		 
		    for (WatchEvent<?> event : key.pollEvents()) {
		        // get event type
		        WatchEvent.Kind<?> kind = event.kind();
		 
		        // get file name
		        @SuppressWarnings("unchecked")
		        WatchEvent<Path> ev = (WatchEvent<Path>) event;
		        Path fileName = ev.context();
		 
		        System.out.println(kind.name() + ": " + fileName);
		 
		        if (kind == StandardWatchEventKinds.OVERFLOW) {
		            continue;
		        } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
		            try {
				Thread.sleep(100);
			    } catch (InterruptedException e) {
				e.printStackTrace();
			    }
		            currentMatch = parseOneTime();
		        }
		    }
		 
		    // IMPORTANT: The key must be reset after processed
		    boolean valid = key.reset();
		    if (!valid) {
			System.out.println("reset");
		        break;
		    }
		    try {
			Thread.sleep(200);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public Match parseOneTime() {
	ReversedLinesFileReader reader = null;
	try {
	    Match retVal = new Match();
	    String mySteamId = null;
	    
	    
	    File serverLogFile = new File(getPathToServiceLog());
	    if (!serverLogFile.exists()) {
		logger.error("no log file");
		throw new RuntimeException("no file");
	    }
	    reader = new ReversedLinesFileReader(serverLogFile);
	    String line = reader.readLine();
	    
	    while(line != null) {
		if (line.contains("loopback")) {
		    logger.trace("game is finished");
		    return retVal;
		}
		if (line.contains("Lobby")) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy - HH:mm:SS");
		    Date dateInFile = null;
		    try {
			dateInFile = dateFormat.parse(line.substring(0, 20));
		    } catch (ParseException e) {
			e.printStackTrace();
		    }
		    Calendar currentDate = Calendar.getInstance();
		    currentDate.add(Calendar.HOUR, -2);
		    if (dateInFile.before(currentDate.getTime())) {
			logger.trace("date in line server_log.txt too old: "+ line.substring(0, 20));
			return retVal;
		    }
		    retVal.setStartDate(dateInFile);
		    
		    logger.trace("Line in server_log.txt: " + line);
		    
		    for (String splittedLine: line.split(" ")) {
			if (splittedLine.contains(":[U:")) { //splittedLine = 0:[U:1:140878476]
			    String steamId = splittedLine.split(":")[3]
				    .replace("]", "")
				    .replace(")", "");
			    if (retVal.getPlayers().size() < 10) {
    			    	Player player = new Player(Integer.parseInt(steamId));
    			    	retVal.getPlayers().add(player);
			    } else {
				mySteamId = steamId; // last (11th) splittedLine - mySteamId
			    }
			}
		    }
		    break;
		}
		line = reader.readLine();
	    }
	    if (retVal.getPlayers().size() != 10) {
		throw new RuntimeException("size not 10");
	    }
	    if (mySteamId == null) {
		throw new RuntimeException("no my steamId");
	    }
	    
	    logger.trace("Parsed line in server_log.txt: " + retVal);
	    
	    return retVal;
	    
	} catch (IOException e) {
	    throw new RuntimeException(e);
	    
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    
    private String getPathToServiceLog() {
	final String filenameOfServerLog = "server_log.txt";
	String pathToServerLog = Configuration.pathToDota + "\\" + filenameOfServerLog; // TODO: determine real dota path
	
	logger.trace("Path to server_log.txt:" + pathToServerLog);
	return pathToServerLog;
    }

}
