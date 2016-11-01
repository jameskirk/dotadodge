package dotalike.core.service;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.Vote;
import dotalike.core.dao.GlobalStatisticDao;

public class DotaDodgeServiceImpl implements DotaLikeService {
    
    //@Inject
    //CustomStatisticDao customStatisticDao;
    
    @Inject
    GlobalStatisticDao globalStatisticDao;

    @Override
    public List<Player> getPlayers(List<Integer> playersId) {
    	//1. get players from dotabuff
    	List<Player> retVal = globalStatisticDao.getPlayersDetails(playersId);
    	
    	//2. get likes, dislikes from our service 
//		try {
//			URL url = new URL("http://localhost:9999/ws/customstatisticdao?wsdl");
//			QName qname = new QName("http://impl.dao.service.dotalike/", "CustomStatisticDaoImplService");
//			Service service = Service.create(url, qname);
//			CustomStatisticDao customStatisticDao = service.getPort(CustomStatisticDao.class);
//			System.out.println(customStatisticDao.getTime("mkyong"));
//			System.out.println(customStatisticDao.getPlayers(playersId));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return retVal;
	}

    @Override
    public List<Match> getMatchHistory(Integer playerId) {
	// TODO from global
	return null;
    }

    @Override
    public void report(Vote report, Integer toSteamId, Date toMatchDate) {
	//customStatisticDao.reportPlayer(report, toSteamId, toMatchDate);
	
    }

}
