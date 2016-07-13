package dotalike.common.model.external;

import java.util.List;

//TODO: armad: rename to PartyOfPlayers or Party
public class Party {
	
    private List<Integer> playersId;

    public Party(List<Integer> playersId) {
        this.playersId = playersId;
    }
}
