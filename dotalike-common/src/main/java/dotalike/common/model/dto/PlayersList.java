package dotalike.common.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import dotalike.common.model.Player;

@XmlRootElement
public class PlayersList {
	
	private List<Player> players = new ArrayList<>();

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	@Override
	public String toString() {
		StringBuilder retVal = new StringBuilder();
		players.forEach(e -> retVal.append(e.getSteamId() + " "));
		return retVal.toString();
	}
	

}
