package dotalike.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import dotalike.common.model.external.PlayerInMatch;

@Entity
public class Match {
    
    /** temporary id - useless, needs just for index db */
    @Id
    @GeneratedValue
    private int temporaryId;
    
    /** id from dotabuff **/
    private int id;
    
    @ManyToMany
    private List<Player> players = new ArrayList<>();
    
    @Transient 
    private List<PlayerInMatch> playersInMatch = new ArrayList<>(); 
    
    private Date startDate;
    
    private boolean win;
    
    public int getTemporaryId() {
        return temporaryId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public List<PlayerInMatch> getPlayersInMatch() {
		return playersInMatch;
	}

	public void setPlayersInMatch(List<PlayerInMatch> playersInMatch) {
		this.playersInMatch = playersInMatch;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	@Override
    public String toString() {
	StringBuilder string = new StringBuilder();
	string.append("Match temporaryId = " + temporaryId + ", Players = [");
	for (Player p: players) {
	    string.append(p.toString());
	    if (players.lastIndexOf(p) != players.size() - 1) {
		string.append(", ");
	    }
	}
	string.append("]");
        return string.toString();
    }
    
}
