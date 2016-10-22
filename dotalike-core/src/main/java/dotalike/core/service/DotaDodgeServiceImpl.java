package dotalike.core.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.google.inject.Inject;

import dotalike.common.dao.CustomStatisticDao;
import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.Report;
import dotalike.common.model.external.PlayerGlobalDetails;
import dotalike.core.dao.GlobalStatisticDao;

public class DotaDodgeServiceImpl implements DotaDodgeService {
    
    //@Inject
    //CustomStatisticDao customStatisticDao;
    
    @Inject
    GlobalStatisticDao globalStatisticDao;

    @Override
    public List<Player> getPlayers(List<Integer> playersId) {
    	
		try {
			URL url = new URL("http://localhost:9999/ws/customstatisticdao?wsdl");
			QName qname = new QName("http://impl.dao.service.dotalike/", "CustomStatisticDaoImplService");
			Service service = Service.create(url, qname);
			CustomStatisticDao customStatisticDao = service.getPort(CustomStatisticDao.class);
			System.out.println(customStatisticDao.getTime("mkyong"));
			System.out.println(customStatisticDao.getPlayers(playersId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Player> retVal = new ArrayList<>();// customStatisticDao.getPlayers(playersId);
		playersId.forEach(e -> retVal.add(new Player(e)));
		// TODO: uncomment, when performance of Dao will be improved
		List<PlayerGlobalDetails> playersDetails = globalStatisticDao.getPlayersDetails(playersId);
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
	//customStatisticDao.reportPlayer(report, toSteamId, toMatchDate);
	
    }

}
