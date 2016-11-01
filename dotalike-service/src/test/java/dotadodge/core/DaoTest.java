package dotadodge.core;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Injector;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.Vote;
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
	
	Vote r = new Vote();
	r.setStars(1);
	r.setDescription("noob");
	m.getPlayers().add(new Player(123));
	m.getPlayers().add(new Player(124));
	m.getPlayers().add(new Player(125));
	JPA.em().persist(m);
	tx.commit();
	
	Assert.assertEquals(1, JPA.findAll(Match.class).get(0).getTemporaryId());
	
	tx.begin();
	JPA.em().remove(m);
	tx.commit();
	
    }

}
