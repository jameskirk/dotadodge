package dotadodge.core;

import javax.persistence.EntityTransaction;

import org.junit.Test;

import com.google.inject.Injector;

import dotadodge.core.db.ApplicationInitializer;
import dotadodge.core.db.JPA;
import dotadodge.core.misc.GuiceFactory;
import dotadodge.core.model.Match;

public class DaoTest {
    
    @Test
    public void test() {
	Injector injector = GuiceFactory.getInjector();
	injector.getInstance(ApplicationInitializer.class).start();
	
	EntityTransaction tx = JPA.em().getTransaction();
	Match m = new Match();
	tx.begin();
	JPA.em().persist(m);
	tx.commit();
	
	tx.begin();
	JPA.em().remove(m);
	tx.commit();
	
    }

}
