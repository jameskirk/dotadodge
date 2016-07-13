package dotadodge.core.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import dotadodge.core.file.MatchNotStartedException;
import dotadodge.core.file.ServerLogParser;
import dotadodge.core.service.DotaDodgeService;
import dotalike.common.model.Match;
import dotalike.common.model.Player;

public class DotaDodge {
    
    private static final Logger logger = LoggerFactory.getLogger(DotaDodge.class);
    
    
    @Inject
    private ServerLogParser serverLogParser;
    
    @Inject
    private DotaDodgeService dotaDodgeService;
    
    public DotaDodge() {
    }
    
    public void run() {
    }
    
    public void dodge() {
	//TODO
    }
    
    public Match getCurrentMatch() throws MatchNotStartedException {
	Match match = serverLogParser.parse();
	
	if (serverLogParser.isNewMatch()) {
	    logger.debug("loading players from service");
	    List<Integer> playersIds = new ArrayList<Integer>();
	    match.getPlayers().forEach(e -> playersIds.add(e.getSteamId()));
	    List<Player> players = dotaDodgeService.getPlayers(playersIds);
	    match.setPlayers(players);
	}
	return match;
    }
    
    public DotaDodgeService getDotaDodgeService() {
        return dotaDodgeService;
    }
    
}
