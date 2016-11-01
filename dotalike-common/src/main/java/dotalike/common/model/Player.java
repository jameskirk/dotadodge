package dotalike.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlType
public class Player {
    
    @Id
    private int steamId;
    
    private String name;
    
    @OneToMany
    private List<Like> likes;
    
    private int likeCount;
    
    private int dislikeCount;
    
    @Transient
    private String winRate;
    
    @Transient
    private String nickName;
    
    @Transient
    private Integer soloMmr;
    
    @Transient
    private boolean accountIsPrivate;
    
    @Transient
    private List<Match> lastMatches = new ArrayList<>();
    
    public Player() {
    }
    
    public Player(int steamId) {
	this.steamId = steamId;
    }

    public int getSteamId() {
        return steamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public void setSteamId(int steamId) {
		this.steamId = steamId;
	}
	
	public String getWinRate() {
		return winRate;
	}

	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSoloMmr() {
		return soloMmr;
	}

	public void setSoloMmr(Integer soloMmr) {
		this.soloMmr = soloMmr;
	}
	
	public List<Match> getLastMatches() {
		return lastMatches;
	}

	public void setLastMatches(List<Match> lastMatches) {
		this.lastMatches = lastMatches;
	}
	
	public boolean isAccountIsPrivate() {
		return accountIsPrivate;
	}

	public void setAccountIsPrivate(boolean accountIsPrivate) {
		this.accountIsPrivate = accountIsPrivate;
	}

	@Override
    public String toString() {
        return steamId + "";
    }
    
}
