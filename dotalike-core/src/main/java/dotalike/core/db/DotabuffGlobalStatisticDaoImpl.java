package dotalike.core.db;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotalike.common.model.Match;
import dotalike.common.model.external.PlayerGlobalDetails;
import dotalike.common.model.external.PlayerInMatch;
import dotalike.core.dao.GlobalStatisticDao;
import us.codecraft.xsoup.Xsoup;

public class DotabuffGlobalStatisticDaoImpl implements GlobalStatisticDao {

    private final Logger logger = LoggerFactory.getLogger(DotabuffGlobalStatisticDaoImpl.class);
    
    private static final String DOTABUFF_PLAYERS = "http://www.dotabuff.com/players/";

    public List<Match> getMatchHistory(Integer id) {
        // TODO: armad
        return null;
    }

    @Override
    public List<PlayerGlobalDetails> getPlayersDetails(List<Integer> ids) {
        List<PlayerGlobalDetails> retVal = new Vector<>();
        final CyclicBarrier barrier = new CyclicBarrier(11);
        for (Integer id : ids) {
        	
        	Thread threadGetDetails = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
			            retVal.add(getPlayerDetails(id));
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException |IOException e) {
						e.printStackTrace();
					}
				}
			});
        	
        	threadGetDetails.start();
        }
        try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

        return retVal;
    }
    
    private PlayerGlobalDetails getPlayerDetails(Integer id) throws IOException {
    	PlayerGlobalDetails retVal = new PlayerGlobalDetails();
    	
        String url = new String(DOTABUFF_PLAYERS + id);
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .timeout(2000)
                .followRedirects(true)
                .get();
        String nickName = doc.select("title").get(0).text().split(" - ")[0];

        boolean accountIsPrivate = false;
        Element accountIsPrivateElement = Xsoup.compile("//div[@class='intro intro-smaller']/h1").evaluate(doc).getElements().first();
        if (accountIsPrivateElement != null && accountIsPrivateElement.text().contains("private")) {
        	accountIsPrivate = true;
        }
        
        if (!accountIsPrivate) {
			for (int i = 0; i < 10; i++) {
				try {
					String hero = Xsoup
							.compile("//*[@class='r-table r-only-mobile-5 performances-overview']/div["
									+ new Integer(i + 1).toString() + "]/div[1]//a[contains(@href, 'matches') ]")
							.evaluate(doc).getElements().get(0).text();
					String win = Xsoup
							.compile("//*[@class='r-table r-only-mobile-5 performances-overview']/div["
									+ new Integer(i + 1).toString() + "]/div[2]//a")
							.evaluate(doc).getElements().get(0).text();
					logger.info(id + " " + hero + " " + win);
					Match match = new Match();
					PlayerInMatch player = new PlayerInMatch();
					player.setHero(hero);
					boolean isWin = "Won Match".equals(win);
					match.getPlayersInMatch().add(player);
					match.setWin(isWin);
					retVal.getLastMatches().add(match);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("can not parse, steam id = " + id);
				}
			}
        } else {
        	logger.debug("account is private, steam id = " + id);
        }
        
        retVal.setNickName(nickName);
        retVal.setSteamId(id);
    	return retVal;
    }
}
