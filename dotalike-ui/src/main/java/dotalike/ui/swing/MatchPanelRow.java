package dotalike.ui.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class MatchPanelRow {
	
	private JLabel nickname = new JLabel();
	
	private LikeComponent likeComponent = new LikeComponent();
	
	private List<JLabel> signatures = new ArrayList<JLabel>();
	
	private List<JLabel> lastHeroes = new ArrayList<JLabel>();
	
	private JLabel accountIsPrivate = new JLabel("account is private");
	
	private JLabel soloMMR = new JLabel();
	
	public MatchPanelRow() {
		signatures.add(new JLabel());
		signatures.add(new JLabel());
		for (int i=0; i<10; i++) {
			lastHeroes.add(new JLabel());
		}
	}
	
	public void clearPanel() {
		nickname.setVisible(false);
		nickname.setText("");
		nickname.setForeground(Constants.fontColor);
		
		likeComponent.setVisible(false);
		likeComponent.clear();
		
		signatures.forEach(e -> { e.setVisible(false); e.setIcon(null); });
		
		accountIsPrivate.setForeground(Constants.fontColor);
	    accountIsPrivate.setVisible(false);
	    
	    lastHeroes.forEach(e -> {e.setVisible(false); e.setIcon(null); e.setBorder(null);});
	    
	    soloMMR.setVisible(false);
	    soloMMR.setText("");
	    soloMMR.setForeground(Constants.fontColor);
    }
	
	public void setVisibleForAll() {
		nickname.setVisible(true);
		likeComponent.setVisible(true);
		signatures.forEach(e -> { e.setVisible(true); });
		lastHeroes.forEach(e -> {e.setVisible(true);});
		soloMMR.setVisible(true);
    }

	public JLabel getNickname() {
		return nickname;
	}

	public void setNickname(JLabel players) {
		this.nickname = players;
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
