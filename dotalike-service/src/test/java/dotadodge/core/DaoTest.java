package dotadodge.core;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Injector;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.Report;
import dotalike.service.db.ApplicationInitializer;
import dotalike.service.db.JPA;
import dotalike.service.misc.GuiceFactory;

public class DaoTest {
    
    @Test
    public void test() {
	Injector injector = GuiceFactory.getInjector();
	injector.getInstance(ApplicationInitializer.class).start();
	
	EntityTransaction tx = JPA.em().getTransaction();
	Match m = new Match();
	tx.begin();
	
	Report r = new Report();
	r.setStars(1);
	r.setDescription("noob");
	m.getPlayers().add(new Player(123));
	m.getPlayers().add(new Player(124));
	m.getPlayers().add(new Player(125));
	m.getPlayers().get(1).getReports().add(r);
	m.getPlayers().get(1).getReports().add(new Report());
	for (Player p : m.getPlayers()) {
	    for (Report r1 : p.getReports()) {
		JPA.em().persist(r1);
	    }
	    JPA.em().persist(p);
	}
	JPA.em().persist(m);
	tx.commit();
	
	Assert.assertEquals(1, JPA.findAll(Match.class).get(0).getTemporaryId());
	
	tx.begin();
	JPA.em().remove(m);
	tx.commit();
	
    }

}
