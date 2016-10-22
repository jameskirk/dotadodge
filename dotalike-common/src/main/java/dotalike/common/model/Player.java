package dotalike.common.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import dotalike.common.model.external.PlayerGlobalDetails;

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
    private PlayerGlobalDetails playerDetails;
    
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
    
    public PlayerGlobalDetails getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(PlayerGlobalDetails playerDetails) {
        this.playerDetails = playerDetails;
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

	@Override
    public String toString() {
        return steamId + "";
    }
    
}
