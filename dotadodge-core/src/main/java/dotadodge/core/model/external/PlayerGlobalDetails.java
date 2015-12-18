package dotadodge.core.model.external;

public class PlayerGlobalDetails {
    
    private int steamId;
    private String winRate;
    private String nickName;

    public PlayerGlobalDetails(int steamId, String winRate, String nickName) {
        this.steamId = steamId;
        this.winRate = winRate;
        this.nickName = nickName;
    }

    public void setSteamId(int steamId) {
        this.steamId = steamId;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getWinRate() {
        return winRate;
    }

    public PlayerGlobalDetails(int steamId) {
	this.steamId = steamId;
    }

    public int getSteamId() {
        return steamId;
    }



}
