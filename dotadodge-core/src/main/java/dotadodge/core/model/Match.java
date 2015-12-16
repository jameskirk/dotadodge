package dotadodge.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Match {
    
    /** temporary id - useless, needs just for index db */
    @Id
    @GeneratedValue
    private int temporaryId;
    
    /** id from dotabuff **/
    private int id;
    
    @ManyToMany
    private List<Player> players = new ArrayList<Player>();
    
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
