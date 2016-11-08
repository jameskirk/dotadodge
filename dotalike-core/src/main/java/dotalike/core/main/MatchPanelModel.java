package dotalike.core.main;

import java.util.ArrayList;
import java.util.List;

import dotalike.common.model.Match;
import dotalike.common.model.external.Signature;

public class MatchPanelModel {
	
	private Match match;
	
	private String errorMessage;
	
	public MatchPanelModel() {
		super();
	}
	
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
