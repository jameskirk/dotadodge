package dotadodge.core.file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotadodge.core.misc.Configuration;
import dotadodge.core.misc.WinRegistry;
import dotadodge.core.model.Match;
import dotadodge.core.model.Player;

public class ServerLogParser {
    
    private final Logger logger = LoggerFactory.getLogger(ServerLogParser.class);
    
    private Match currentMatch;
    
    private Date previousMatchDate;
    
    
    public ServerLogParser() {
	logger.debug("Path to server_log.txt: " + getPathToServiceLog());
    }
    
    public Match parse() {
	currentMatch = parseStateless();
	if (previousMatchDate == null || !previousMatchDate.equals(currentMatch.getStartDate())) { // not init or new match (new date)
	    previousMatchDate = currentMatch.getStartDate();
	}
	return currentMatch;
    }
    
    public boolean isPreviousAndCurrentMatchIsNotEqual() {
	boolean retVal = false;
	boolean forceCallStatistic = true;
	if (forceCallStatistic || previousMatchDate == null || !previousMatchDate.equals(currentMatch.getStartDate())) { // not init or new match (new date)
	    retVal = true;
	}
	return retVal;
    }
    
    
    private Match parseStateless() {
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
	String pathToSteam;
	try {
	    pathToSteam = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Valve\\Steam", "SteamPath");
	    pathToSteam = pathToSteam.replace("/", "\\");
	} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
	String pathToServerLog = pathToSteam + "\\" + Configuration.pathFromSteamToDota + "\\" + filenameOfServerLog; // TODO: determine real dota path
	return pathToServerLog;
    }

}
