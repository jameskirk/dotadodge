package dotadodge.core.model.external;

import java.util.List;

//TODO: armad: rename to PartyOfPlayers or Party
public class PlayersInPartyInfo {
    private List<Integer> friendsId;

    public PlayersInPartyInfo(List<Integer> friendsId) {
        this.friendsId = friendsId;
    }
}
