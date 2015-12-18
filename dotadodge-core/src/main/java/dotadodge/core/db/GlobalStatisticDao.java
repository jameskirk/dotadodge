package dotadodge.core.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dotadodge.core.model.Match;
import dotadodge.core.model.external.PlayerGlobalDetails;
import dotadodge.core.model.external.PlayersInPartyInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GlobalStatisticDao {

    private String WINRATE_REQ = "/matches?date=3month";
    private String WINRATE_SPAN_NAME = "span.color-stat-win";

    private String genReqString(String req, int id) {
        // TODO: add exception if url not found.
        return "https://www.dotabuff.com/players/" + id + req;
    }

    public List<Match> getMatchHistory(Integer id) {
        //<span class="color-stat-win">42.95%</span>
        // TODO: armad
        return null;
    }

    public List<String> getWinRate(List<Integer> id) throws IOException {
        List<String> winRatePlayers = new ArrayList();
        for (int ide : id) {
            String url = new String(genReqString(WINRATE_REQ, ide));
        //    String url = new String("https://www.dotabuff.com/players/");
            //blablabla
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .timeout(2000)
                    .followRedirects(true)
                    .get();
            for (Element e : doc.select(WINRATE_SPAN_NAME)) {
                winRatePlayers.add(e.text());
            }
        }

        return winRatePlayers;
    }

    public List<String> getLastNickName(Integer... id) throws IOException {
        List<String> nickNamePlayers = new ArrayList();
        for (int ide : id) {
            String url = new String(genReqString("", ide));
            Document doc = Jsoup.connect(url).get();
            for (Element e : doc.select("title")) {
                nickNamePlayers.add(e.text());
            }
        }

        return nickNamePlayers;
    }

    public List<PlayerGlobalDetails> getPlayerStats(Integer... id) throws IOException {

        return null;
    }

    public PlayersInPartyInfo getPlayersInPartyInfo(Integer... id) {
        //TODO: armad
        return null;
    }

}
