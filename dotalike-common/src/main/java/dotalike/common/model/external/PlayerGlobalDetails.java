package dotalike.common.model.external;

import java.util.ArrayList;
import java.util.List;

import dotalike.common.model.Match;

public class PlayerGlobalDetails {
    
    private int steamId;
    private String winRate;
    private String nickName;
    private Integer soloMmr;
    
    private List<Match> lastMatches = new ArrayList<>();
    
    public PlayerGlobalDetails() {
    }

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
    
    public List<Match> getLastMatches() {
		return lastMatches;
	}

	public void setLastMatches(List<Match> lastMatches) {
		this.lastMatches = lastMatches;
	}

	public int getSteamId() {
        return steamId;
    }

	public Integer getSoloMmr() {
		return soloMmr;
	}

	public void setSoloMmr(Integer soloMmr) {
		this.soloMmr = soloMmr;
	}
	

}
