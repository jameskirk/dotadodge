package dotadodge.core.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;

import dotadodge.core.model.Player;
import dotadodge.core.model.Report;

public class CustomStatisticDao {
    
    public List<Player> getPlayers(List<Integer> ids) {
	List<Player> retVal = new ArrayList<Player>();
	EntityTransaction tx = JPA.em().getTransaction();
	tx.begin();
	for (int id: ids) {
	    Player p = JPA.findById(Player.class, id);
	    if (p == null) {
		p = new Player(id);
	    }
	    retVal.add(p);
	}
	tx.commit();
	return retVal;
    }
    
    public void reportPlayer(Report report, int toSteamId, Date toMatchDate) {
	
	EntityTransaction tx = JPA.em().getTransaction();
	tx.begin();
	Player p = JPA.findById(Player.class, toSteamId);
	if (p == null) {
	    JPA.em().persist(new Player(toSteamId));
	    p = JPA.findById(Player.class, toSteamId);
	}
	p.getReports().add(report);
	JPA.em().persist(report);
	JPA.em().merge(p);
	tx.commit();
	System.out.println("REPORTED! ");
    }

}
