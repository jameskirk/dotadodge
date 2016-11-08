package dotalike.ui.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class MatchPanelRow {
	
	private JLabel players = new JLabel();
	
	private LikeComponent likeComponent = new LikeComponent();
	
	private List<JLabel> signatures = new ArrayList<JLabel>();
	
	private List<JLabel> lastHeroes = new ArrayList<JLabel>();
	
	private JLabel accountIsPrivate = new JLabel();
	
	private JLabel soloMMR = new JLabel();

	public JLabel getPlayers() {
		return players;
	}

	public void setPlayers(JLabel players) {
		this.players = players;
	}

	public LikeComponent getLikeComponent() {
		return likeComponent;
	}

	public void setLikeComponent(LikeComponent likeComponent) {
		this.likeComponent = likeComponent;
	}

	public List<JLabel> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<JLabel> signatures) {
		this.signatures = signatures;
	}

	public List<JLabel> getLastHeroes() {
		return lastHeroes;
	}

	public void setLastHeroes(List<JLabel> lastHeroes) {
		this.lastHeroes = lastHeroes;
	}

	public JLabel getAccountIsPrivate() {
		return accountIsPrivate;
	}

	public void setAccountIsPrivate(JLabel accountIsPrivate) {
		this.accountIsPrivate = accountIsPrivate;
	}

	public JLabel getSoloMMR() {
		return soloMMR;
	}

	public void setSoloMMR(JLabel soloMMR) {
		this.soloMMR = soloMMR;
	}
	
	
}
