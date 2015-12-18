package dotadodge.core.main;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import dotadodge.core.db.ApplicationInitializer;
import dotadodge.core.file.ServerLogParser;
import dotadodge.core.model.Match;
import dotadodge.core.model.Player;
import dotadodge.core.service.DotaDodgeService;

public class DotaDodge {
    
    @Inject
    private ApplicationInitializer applicationInitializer;
    
    @Inject
    private ServerLogParser serverLogParser;
    
    @Inject
    private DotaDodgeService dotaDodgeService;
    
    public DotaDodge() {
    }
    
    public void run() {
	applicationInitializer.start();
    }
    
    public void dodge() {
	//TODO
    }
    
    public Match getCurrentMatch() {
	Match match = serverLogParser.parse();
	if (serverLogParser.isPreviousAndCurrentMatchIsNotEqual()) {
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
