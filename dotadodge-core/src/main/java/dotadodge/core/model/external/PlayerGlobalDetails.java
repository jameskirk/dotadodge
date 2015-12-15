package dotadodge.core.model.external;

public class PlayerGlobalDetails {
    
    private int steamId;
    
    public PlayerGlobalDetails(int steamId) {
	this.steamId = steamId;
    }

    public int getSteamId() {
        return steamId;
    }

    //TODO: armad from dotabuff

}
