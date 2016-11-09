package dotalike.core.main;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.external.MatchHistory;
import dotalike.common.model.external.Signature;
import dotalike.core.file.ServerLogParser;
import dotalike.core.misc.Configuration;
import dotalike.core.misc.Configuration.ConfigurationKey;
import dotalike.core.misc.MatchNotStartedException;
import dotalike.core.service.DotaLikeService;

public class DotaLikeEngine {
    
    private static final Logger logger = LoggerFactory.getLogger(DotaLikeEngine.class);
    
    @Inject
    private ServerLogParser serverLogParser;
    
    @Inject
    private DotaLikeService dotaLikeService;
    
    public DotaLikeEngine() {
    	if (!Configuration.getInstance().read(ConfigurationKey.PROXY).isEmpty()) {
            System.setProperty("http.proxyHost", Configuration.getInstance().read(ConfigurationKey.PROXY));
            System.setProperty("http.proxyPort", Configuration.getInstance().read(ConfigurationKey.PROXY_PORT));
        }
    }
    
    public void dodge() {
	//TODO
	}

	public MatchPanelModel getCurrentMatch() throws MatchNotStartedException, FileNotFoundException, ConnectException {
		MatchPanelModel retVal = new MatchPanelModel();
		Match match = serverLogParser.parse();
		retVal.setMatch(match);

		if (serverLogParser.isNewMatch()) {
			logger.debug("loading players from service");
			List<Integer> playersIds = new ArrayList<Integer>();
			match.getPlayers().forEach(e -> playersIds.add(e.getSteamId()));
			List<Player> players = dotaLikeService.getPlayers(playersIds);
			match.setPlayers(players);
			
			// match history
			List<MatchHistory> mhList = dotaLikeService.getMatchHistory(playersIds);
			for (int i = 0; i < playersIds.size(); i++) {
				mhList.get(i).getPlayer().setNickName(players.get(i).getNickName());
			}
			for (int i = 0; i < mhList.size(); i++) {
				MatchHistory mh = mhList.get(i);
				retVal.getMatch().getPlayers().get(i).setSignatures(calculateSignatures(mh));
			}
		}
		return retVal;
	}

	public DotaLikeService getDotaLikeService() {
		return dotaLikeService;
	}
	
	public int getMySteamId() {
		//TODO
		return 110645196;
	}
	
	private List<Signature> calculateSignatures(MatchHistory mh) {
		List<Signature> retVal = new ArrayList<>();
		Map<String, Integer> heroStatistic = new HashMap<>();
		for (Match m: mh.getMatches()) {
			String hero = m.getPlayersInMatch().get(0).getHero();
			if (!heroStatistic.containsKey(hero)) {
				heroStatistic.put(hero, 1);
			} else {
				heroStatistic.put(hero, heroStatistic.get(hero)+1);
			}
		}
		String maxHero = "";
		Integer maxCount = 0;
		for (Entry<String, Integer> e: heroStatistic.entrySet()) {
			if (maxCount < e.getValue()) {
				maxCount = e.getValue();
				maxHero = e.getKey();
			}
		}
		if (maxCount != 0) {
			logger.info("signature: " + mh.getPlayer().getNickName() + ", " 
					+ maxHero + " " + maxCount + "/" + mh.getMatches().size());
		}
		if (maxCount > 7) {
			Signature s = new Signature();
			s.setHero(maxHero);
			s.setMatchesWithHero(maxCount);
			s.setAllMatches(mh.getMatches().size());
			retVal.add(s);
		}
		
		heroStatistic.remove(maxHero);
		maxHero = "";
		maxCount = 0;
		for (Entry<String, Integer> e: heroStatistic.entrySet()) {
			if (maxCount < e.getValue()) {
				maxCount = e.getValue();
				maxHero = e.getKey();
			}
		}
		if (maxCount != 0) {
			logger.info("signature2: " + mh.getPlayer().getNickName() + ", " 
					+ maxHero + " " + maxCount + "/" + mh.getMatches().size());
		}
		if (maxCount > 7) {
			Signature s = new Signature();
			s.setHero(maxHero);
			s.setMatchesWithHero(maxCount);
			s.setAllMatches(mh.getMatches().size());
			retVal.add(s);
		}
		return retVal;
	}

}
