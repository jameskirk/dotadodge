package dotadodge.core.db;

import javax.persistence.EntityTransaction;

import com.google.inject.Inject;

import dotadodge.core.file.ServerLogParser;
import dotadodge.core.model.Match;
import dotadodge.core.model.Player;
import dotadodge.core.model.Report;

public class CurrentGameDao {
    
    @Inject
    private ServerLogParser serverLogParser;
    
    private boolean inited = false;
    
    public Match getCurrentMatch() {
	Match currMatch = serverLogParser.parseOneTime();
	if (!inited) {
	    initCurrentMatchInDB(currMatch);
	    inited = true;
	}
	
//	EntityTransaction tx = JPA.em().getTransaction();
//	tx.begin();
	Match m = JPA.findAll(Match.class).get(JPA.findAll(Match.class).size() - 1);
//	Player p = JPA.em().find(Player.class, "229835061");
//	for (Player pp: m.getPlayers()) {
//	    pp.getReports();
//	}
//	Report r = new Report();
//	r.setDescription("good");
//	r.setStars(5);
//	p.getReports().add(r);
//	JPA.em().persist(r);
//	JPA.em().merge(p);
//	tx.commit();
	return m;
    }
    
    private void initCurrentMatchInDB(Match currMatch) {
	Report r = new Report();
	    r.setStars(1);
	    r.setDescription("noob");
	    currMatch.getPlayers().get(1).getReports().add(r);
	    Report r2 = new Report();
	    r2.setStars(2);
	    r2.setDescription("noob1");
	    currMatch.getPlayers().get(2).getReports().add(r2);
	    EntityTransaction tx = JPA.em().getTransaction();
	    tx.begin();
	    for (Player p: currMatch.getPlayers()) {
		for (Report r1:p.getReports()) {
		    JPA.em().persist(r1);
		}
		JPA.em().persist(p);
	    }
	    JPA.em().persist(currMatch);
	    JPA.em().flush();
	    tx.commit();
    }

}
