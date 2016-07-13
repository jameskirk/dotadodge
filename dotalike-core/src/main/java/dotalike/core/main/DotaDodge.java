package dotalike.core.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.core.file.ServerLogParser;
import dotalike.core.misc.Configuration;
import dotalike.core.misc.MatchNotStartedException;
import dotalike.core.misc.Configuration.ConfigurationKey;
import dotalike.core.service.DotaDodgeService;

public class DotaDodge {
    
    private static final Logger logger = LoggerFactory.getLogger(DotaDodge.class);
    
    @Inject
    private ServerLogParser serverLogParser;
    
    @Inject
    private DotaDodgeService dotaDodgeService;
    
    public DotaDodge() {
    	if (!Configuration.getInstance().read(ConfigurationKey.PROXY).isEmpty()) {
            System.setProperty("http.proxyHost", Configuration.getInstance().read(ConfigurationKey.PROXY));
            System.setProperty("http.proxyPort", Configuration.getInstance().read(ConfigurationKey.PROXY_PORT));
        }
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
