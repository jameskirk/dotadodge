package dotadodge.core.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotadodge.core.misc.Configuration;
import dotadodge.core.misc.Configuration.ConfigurationKey;
import dotadodge.core.model.Match;
import dotadodge.core.model.external.PlayerGlobalDetails;
import dotadodge.core.model.external.PlayerInMatch;

public class DotabuffGlobalStatisticDaoImpl implements GlobalStatisticDao {

    private final Logger logger = LoggerFactory.getLogger(DotabuffGlobalStatisticDaoImpl.class);

    private String WINRATE_REQ = "/matches?date=3month";
    private String WINRATE_SPAN_NAME = "span.color-stat-win";

    private String genReqString(String req, int id) {
        // TODO: add exception if url not found.
        return "http://www.dotabuff.com/players/" + id + req;
    }

    public List<Match> getMatchHistory(Integer id) {
        //<span class="color-stat-win">42.95%</span>
        // TODO: armad
        return null;
    }

    public String getWinRate(Integer id) throws IOException {
        if (!Configuration.getInstance().read(ConfigurationKey.PROXY).isEmpty()) {
            System.setProperty("http.proxyHost", Configuration.getInstance().read(ConfigurationKey.PROXY));
            System.setProperty("http.proxyPort", Configuration.getInstance().read(ConfigurationKey.PROXY_PORT));
        }

        List<String> winRatePlayers = new ArrayList();
        String url = new String(genReqString(WINRATE_REQ, id));
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .timeout(2000)
                .followRedirects(true)
                .get();
        if (!doc.select(WINRATE_SPAN_NAME).isEmpty()) {
            for (Element e : doc.select(WINRATE_SPAN_NAME)) {
                winRatePlayers.add(e.text());
            }
        } else {
            winRatePlayers.add("Unknown");
            logger.error("no found winrate for player " + id);
        }
        return winRatePlayers.get(0);
    }

    @Override
    public List<PlayerGlobalDetails> getPlayerStats(List<Integer> ids) {
        List<PlayerGlobalDetails> playerGlobalDetailses = new Vector<>();
        final CyclicBarrier barrier = new CyclicBarrier(11);
        for (Integer id : ids) {
        	Thread threadGetDetails = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
			            playerGlobalDetailses.add(getPlayerDetails(id));
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

        return playerGlobalDetailses;
    }
    
    private PlayerGlobalDetails getPlayerDetails(Integer id) throws IOException {
    	PlayerGlobalDetails retVal = new PlayerGlobalDetails();
    	if (!Configuration.getInstance().read(ConfigurationKey.PROXY).isEmpty()) {
            System.setProperty("http.proxyHost", Configuration.getInstance().read(ConfigurationKey.PROXY));
            System.setProperty("http.proxyPort", Configuration.getInstance().read(ConfigurationKey.PROXY_PORT));
        }

        List<String> winRatePlayers = new ArrayList();
        String url = new String(genReqString("", id));
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .timeout(2000)
                .followRedirects(true)
                .get();
        String nickName = doc.select("title").get(0).text().split(" - ")[0];
        try {
	        Elements rowsMatches = doc.getElementsByAttributeValue("class", "r-table r-only-mobile-5 performances-overview");
	        for (int i=0; i<10; i++) {
	        	String hero = rowsMatches.get(0).child(i).child(0).child(1).child(1).text();
	        	String win = rowsMatches.get(0).child(i).child(1).child(1).child(0).text();
	        	logger.info(id + " " + hero + " " + win);
	        	Match match = new Match();
	        	PlayerInMatch player = new PlayerInMatch();
	        	player.setHero(hero);
	        	boolean isWin = "Won Match".equals(win);
	        	match.getPlayersInMatch().add(player);
	        	match.setWin(isWin);
	        	retVal.getLastMatches().add(match);
	        }
        } catch (Exception e) {
        	e.printStackTrace();
		}
        retVal.setNickName(nickName);
        retVal.setSteamId(id);
    	return retVal;
    }
}
