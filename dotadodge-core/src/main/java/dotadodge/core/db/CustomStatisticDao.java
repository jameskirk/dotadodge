package dotadodge.core.db;

import javax.persistence.EntityTransaction;

import dotadodge.core.model.Match;
import dotadodge.core.model.Player;
import dotadodge.core.model.Report;

public class CustomStatisticDao {
    
    public Player getPlayer(int id) {
	// TODO
	return null;
    }
    
    public void reportPlayer(Report report, String toSteamId, String toMatchId) {
	
	EntityTransaction tx = JPA.em().getTransaction();
	tx.begin();
	
	Match m = JPA.findAll(Match.class).get(JPA.findAll(Match.class).size() - 1);
	Player p = JPA.em().find(Player.class, "229835061");
	for (Player pp: m.getPlayers()) {
	    pp.getReports();
	}
	Report r = new Report();
	r.setDescription("good");
	r.setStars(5);
	p.getReports().add(r);
	JPA.em().persist(r);
	JPA.em().merge(p);
	JPA.em().flush();
	tx.commit();
	System.out.println("REPORTED! ");
    }

}
