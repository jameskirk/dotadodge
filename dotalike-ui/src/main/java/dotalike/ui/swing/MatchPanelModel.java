package dotalike.ui.swing;

import dotalike.common.model.Match;

public class MatchPanelModel {
	
	private Match match;
	
	private String errorMessage;
	
	public MatchPanelModel(Match match) {
		super();
		this.match = match;
	}
	
	public MatchPanelModel(Match match, String errorMessage) {
		super();
		this.match = match;
		this.errorMessage = errorMessage;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
