package dotadodge.core.service;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import dotadodge.core.db.CustomStatisticDao;
import dotadodge.core.db.GlobalStatisticDao;
import dotadodge.core.model.Match;
import dotadodge.core.model.Player;
import dotadodge.core.model.Report;
import dotadodge.core.model.external.PlayerGlobalDetails;

public class DotaDodgeServiceImpl implements DotaDodgeService {
    
    @Inject
    CustomStatisticDao customStatisticDao;
    
    @Inject
    GlobalStatisticDao globalStatisticDao;

    @Override
    public List<Player> getPlayers(List<Integer> playersId) {
	List<Player> retVal = customStatisticDao.getPlayers(playersId);
	//TODO: uncomment, when performance of Dao will be improved
	List<PlayerGlobalDetails> playersDetails = globalStatisticDao.getPlayerStats(playersId);
	for (Player player : retVal) {
	    for (PlayerGlobalDetails playerDetails : playersDetails) {
		if (player.getSteamId() == playerDetails.getSteamId()) {
		    player.setPlayerDetails(playerDetails);
		    break;
		}
	    }
	}

	return retVal;
    }

    @Override
    public List<Match> getMatchHistory(Integer playerId) {
	// TODO from global
	return null;
    }

    @Override
    public void report(Report report, Integer toSteamId, Date toMatchDate) {
	customStatisticDao.reportPlayer(report, toSteamId, toMatchDate);
	
    }

}
