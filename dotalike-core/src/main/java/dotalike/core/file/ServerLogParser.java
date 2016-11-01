package dotalike.core.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.core.misc.Configuration;
import dotalike.core.misc.MatchNotStartedException;
import dotalike.core.misc.WinRegistry;
import dotalike.core.misc.Configuration.ConfigurationKey;

public class ServerLogParser {

	private final Logger logger = LoggerFactory.getLogger(ServerLogParser.class);
	/** queue with 2 last matches, it is needed for method isNewMatch() **/
	private Queue<Match> parsedMatches = new LinkedList<Match>();

	public ServerLogParser() {
		logger.debug("Path to server_log.txt: " + getPathToServiceLog());
	}

	public Match parse() throws MatchNotStartedException, FileNotFoundException {
		Match match = parseStateless();
		if (parsedMatches.size() == 0) {
			parsedMatches.add(match);
		} else if (parsedMatches.size() == 1) {
			parsedMatches.add(match);
		} else if (parsedMatches.size() == 2) {
			parsedMatches.poll();
			parsedMatches.add(match);
		}
		return match;
	}

	public boolean isNewMatch() {
		if (parsedMatches.size() == 1) {
			return true;
		} else if (parsedMatches.size() == 2) {
			Iterator<Match> iter = parsedMatches.iterator();
			Match headMatch = null;
			Match tailMatch = null;
			while (iter.hasNext()) {
				Match match = iter.next();
				if (headMatch == null) {
					headMatch = match;
				} else {
					tailMatch = match;
				}
			}
			return !headMatch.getStartDate().equals(tailMatch.getStartDate());
		}
		return false;
	}

	private Match parseStateless() throws MatchNotStartedException, FileNotFoundException {
		ReversedLinesFileReader reader = null;
		try {
			Match retVal = new Match();
			String mySteamId = null;

			File serverLogFile = new File(getPathToServiceLog());
			if (!serverLogFile.exists()) {
				logger.error("no log file");
				throw new FileNotFoundException("no log file");
			}
			reader = new ReversedLinesFileReader(serverLogFile);
			String line = reader.readLine();

			while (line != null) {
				if (line.contains("loopback")) {
					logger.trace("game is finished");
					throw new MatchNotStartedException();
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
						logger.trace("date in line server_log.txt too old: " + line.substring(0, 20) 
								+ " .date in file: " + dateInFile + ", current date:" + currentDate.getTime());
						throw new MatchNotStartedException();
					}
					retVal.setStartDate(dateInFile);

					logger.trace("Line in server_log.txt: " + line);

					for (String splittedLine : line.split(" ")) {
						if (splittedLine.contains(":[U:")) { // splittedLine =
																// 0:[U:1:140878476]
							String steamId = splittedLine.split(":")[3].replace("]", "").replace(")", "");
							if (retVal.getPlayers().size() < 10) {
								Player player = new Player(Integer.parseInt(steamId));
								retVal.getPlayers().add(player);
							} else {
								mySteamId = steamId; // last (11th) splittedLine
														// - mySteamId
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
			throw new FileNotFoundException();

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
		if ("test".equals(Configuration.getInstance().read(ConfigurationKey.ENV))) {
			return System.getProperty("user.dir") + "\\server_log.txt";
		}
		String pathToServerLog = pathToSteam + "\\" + Configuration.pathFromSteamToDota + "\\" + filenameOfServerLog; 
		// TODO:
		// determine
		// real
		// dota
		// path
		return pathToServerLog;
	}

}
