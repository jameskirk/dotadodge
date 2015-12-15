package dotadodge.core.db;

import java.util.List;

import dotadodge.core.model.Match;
import dotadodge.core.model.external.PlayerGlobalDetails;
import dotadodge.core.model.external.PlayersInPartyInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GlobalStatisticDao {

    public List<Match> getMatchHistory(int id) {
        // TODO: armad
        return null;
    }

    public List<PlayerGlobalDetails> getPlayers(int... id) {

        Document doc;
     //   doc = Jsoup.connect("http://google.com").get();
        //blabla
        return null;
    }

    public PlayersInPartyInfo getPlayersInPartyInfo(int... id) {
        //TODO: armad
        return null;
    }

}
