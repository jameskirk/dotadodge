package dotadodge.core.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dotadodge.core.file.ServerLogParser;
import dotadodge.core.misc.Configuration;
import dotadodge.core.misc.Configuration.ConfigurationKey;
import dotadodge.core.model.Match;
import dotadodge.core.model.external.PlayerGlobalDetails;
import dotadodge.core.model.external.PlayersInPartyInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public String getLastNickName(Integer id) throws IOException {
        if (!Configuration.getInstance().read(ConfigurationKey.PROXY).isEmpty()) {
            System.setProperty("http.proxyHost", Configuration.getInstance().read(ConfigurationKey.PROXY));
            System.setProperty("http.proxyPort", Configuration.getInstance().read(ConfigurationKey.PROXY_PORT));
        }
        List<String> nickNamePlayers = new ArrayList();

        String url = new String(genReqString("", id));
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .timeout(2000)
                .followRedirects(true)
                .get();
        for (Element e : doc.select("title")) {
            nickNamePlayers.add(e.text().split(" - ")[0]);
        }
        return nickNamePlayers.get(0);
    }

    @Override
    public List<PlayerGlobalDetails> getPlayerStats(List<Integer> ids) {
        List<PlayerGlobalDetails> playerGlobalDetailses = new ArrayList<>();
        for (Integer id : ids) {
            try {
                playerGlobalDetailses.add(new PlayerGlobalDetails(id, getWinRate(id), getLastNickName(id)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return playerGlobalDetailses;
    }
}
