package dotalike.common.model.external;

import java.util.ArrayList;
import java.util.List;

import dotalike.common.model.Match;
import dotalike.common.model.Player;

public class MatchHistory {
	
	private Player player;
	
	private List<Match> matches = new ArrayList<Match>();

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
}
