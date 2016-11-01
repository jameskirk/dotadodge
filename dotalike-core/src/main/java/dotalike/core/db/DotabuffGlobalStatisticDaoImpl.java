package dotalike.core.db;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.xsoup.Xsoup;
import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.external.PlayerInMatch;
import dotalike.core.dao.GlobalStatisticDao;

public class DotabuffGlobalStatisticDaoImpl implements GlobalStatisticDao {

    private final Logger logger = LoggerFactory.getLogger(DotabuffGlobalStatisticDaoImpl.class);
    
    private static final String DOTABUFF_PLAYERS = "http://www.dotabuff.com/players/";
    
    private static final int MAX_TIMEOUT = 30000;
    
    private static int timeout = 1000;
    
    public List<Match> getMatchHistory(Integer id) {
        // TODO: armad
        return null;
    }

    @Override
    public List<Player> getPlayersDetails(List<Integer> ids) {
        List<Player> retVal = new Vector<>();
        final CyclicBarrier barrier = new CyclicBarrier(11);
        for (Integer id : ids) {
        	
        	Thread threadGetDetails = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
			            retVal.add(getPlayerDetails(id));
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException |ConnectException e) {
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
    
    private Player getPlayerDetails(Integer id) throws ConnectException {
    	try {
	    	Player retVal = new Player();
	    	
	        String url = new String(DOTABUFF_PLAYERS + id);
	        Document doc = Jsoup.connect(url)
	                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
	                .referrer("http://www.google.com")
	                .timeout(timeout)
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
						boolean soloMmrIsPresent =  Xsoup
								.compile("//*[@class='header-content-secondary']/dl[2]/dt[1]")
								.evaluate(doc).getElements().get(0).text().equalsIgnoreCase("Solo MMR");
						Integer soloMmr = null;
						if (soloMmrIsPresent) {
							soloMmr = Integer.valueOf(Xsoup
									.compile("//*[@class='header-content-secondary']/dl[2]/dd[1]")
									.evaluate(doc).getElements().get(0).text());
						}
						logger.trace(id + " " + hero + " " + win + " " + soloMmr);
						Match match = new Match();
						PlayerInMatch player = new PlayerInMatch();
						player.setHero(hero);
						retVal.setSoloMmr(soloMmr);
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
	        	retVal.setAccountIsPrivate(true);
	        }
	        
	        retVal.setNickName(nickName);
	        retVal.setSteamId(id);
	        return retVal;
    	} catch (SocketTimeoutException e ) {
    		if (timeout < MAX_TIMEOUT) {
    			logger.error("SocketTimeoutException, timeout will be increased to: " + timeout + 2000);
    			timeout += 2000;
    			return getPlayerDetails(id);
    		} else {
    			logger.error("SocketTimeoutException, timeout is max: " + (timeout), e);
    			throw new ConnectException(e.getMessage());
    		}
    	} catch (IOException e) {
    		logger.error("IOException", e);
    		throw new ConnectException(e.getMessage());
		}  catch (RuntimeException e) {
    		logger.error("Exception", e);
    		throw new ConnectException(e.getMessage());
		}
    }
}
