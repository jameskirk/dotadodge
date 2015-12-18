package dotadodge.core;

import com.google.inject.Injector;
import dotadodge.core.db.DotabuffGlobalStatisticDaoImpl;
import dotadodge.core.misc.GuiceFactory;
import dotadodge.core.model.external.PlayerGlobalDetails;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 18.12.2015.
 */
public class GlobalTest {
    @Test
    public void test() throws IOException {

        Injector injector = GuiceFactory.getInjector();
        DotabuffGlobalStatisticDaoImpl globalStatisticDao = injector.getInstance(DotabuffGlobalStatisticDaoImpl.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(49716582);
        ids.add(186782126);
        ids.add(71146225);
        ids.add(110645196);
        ids.add(26559018);
        List<PlayerGlobalDetails> blabla = new ArrayList<>(globalStatisticDao.getPlayerStats(ids));
        for (PlayerGlobalDetails bla : blabla){
            System.out.println(bla.getSteamId());
            System.out.println(bla.getNickName());
            System.out.println(bla.getWinRate());

        }

    }
}
