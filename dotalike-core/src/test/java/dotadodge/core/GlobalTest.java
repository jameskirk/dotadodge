package dotadodge.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.inject.Injector;

import dotalike.common.model.Player;
import dotalike.core.db.DotabuffGlobalStatisticDaoImpl;
import dotalike.core.misc.GuiceFactory;

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
        List<Player> blabla = new ArrayList<>(globalStatisticDao.getPlayersDetails(ids));
        for (Player bla : blabla){
            System.out.println(bla.getNickName());
            System.out.println(bla.getSteamId());
            System.out.println(bla.getWinRate());
            System.out.println();

        }

    }
}
