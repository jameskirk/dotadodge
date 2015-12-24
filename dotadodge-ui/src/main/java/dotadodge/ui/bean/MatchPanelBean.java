package dotadodge.ui.bean;

import java.util.ArrayList;
import java.util.List;

public class MatchPanelBean {
    
    private List<PlayerBean> firstTeamPlayers = new ArrayList<PlayerBean>(10);
    
    private List<PlayerBean> secondTeamPlayers = new ArrayList<PlayerBean>(10);

    public List<PlayerBean> getFirstTeamPlayers() {
        return firstTeamPlayers;
    }

    public List<PlayerBean> getSecondTeamPlayers() {
        return secondTeamPlayers;
    }

}
