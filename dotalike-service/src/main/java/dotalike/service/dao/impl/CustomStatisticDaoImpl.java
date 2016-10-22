package dotalike.service.dao.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityTransaction;

import dotalike.common.dao.CustomStatisticDao;
import dotalike.common.model.Player;
import dotalike.common.model.Report;
import dotalike.common.model.dto.PlayersList;
import dotalike.service.db.JPA;

@WebService(endpointInterface = "dotalike.common.dao.CustomStatisticDao")
public class CustomStatisticDaoImpl implements CustomStatisticDao {
	
	@Override
	public String getTime(String str) {
		return "1234";
	}
    
    public PlayersList getPlayers(List<Integer> ids) {
		PlayersList retVal = new PlayersList();
		EntityTransaction tx = JPA.em().getTransaction();
		tx.begin();
		for (int id : ids) {
			Player p = JPA.findById(Player.class, id);
			if (p == null) {
				p = new Player(id);
			}
			
			retVal.getPlayers().add(p);
		}
		tx.commit();
		System.out.println(retVal.toString());
		return retVal;
	}
    
    public void reportPlayer(Report report, int toSteamId, Date toMatchDate) {
//		EntityTransaction tx = JPA.em().getTransaction();
//		tx.begin();
//		Player p = JPA.findById(Player.class, toSteamId);
//		if (p == null) {
//			JPA.em().persist(new Player(toSteamId));
//			p = JPA.findById(Player.class, toSteamId);
//		}
//		p.getReports().add(report);
//		JPA.em().persist(report);
//		JPA.em().merge(p);
//		tx.commit();
//		System.out.println("REPORTED! ");
	}

}
