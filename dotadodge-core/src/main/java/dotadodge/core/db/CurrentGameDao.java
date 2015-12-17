package dotadodge.core.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import dotadodge.core.file.ServerLogParser;
import dotadodge.core.model.Match;
import dotadodge.core.model.Player;

public class CurrentGameDao {
    
    @Inject
    private ServerLogParser serverLogParser;
    
    @Inject
    private CustomStatisticDao customStatisticDao;
    
    private Date prevMatch;
    
    public Match getCurrentMatch() {
	Match currMatch = serverLogParser.parseOneTime();
	boolean forceCallStatistic = true;
	if (forceCallStatistic || prevMatch == null || !prevMatch.equals(currMatch.getStartDate())) { // not init or new match (new date)
	    prevMatch= currMatch.getStartDate();
	    List<Integer> playersIds = new ArrayList<Integer>();
	    currMatch.getPlayers().forEach(e -> playersIds.add(e.getSteamId()));
	    List<Player> players = customStatisticDao.getPlayers(playersIds);
	    currMatch.setPlayers(players);
	}
	
	return currMatch;
    }
    
}
