package dotadodge.ui.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dotadodge.core.model.Match;
import dotadodge.core.model.Report;

public class MatchPanel extends JPanel {
    
    private static final int PLAYERS_COUNT = 10;
    
    private Match model = new Match();
    
    private JLabel header = new JLabel("Loading...");
    
    private JLabel firstTeamName = new JLabel("Radiant");
    
    private JLabel secondTeamName = new JLabel("Dire");
    
    private List<JLabel> players = new ArrayList<JLabel>();
    
    private List<JButton> reportButtons= new ArrayList<JButton>();
    
    private List<JLabel> reports = new ArrayList<JLabel>();
    
    public MatchPanel() {
	setLayout(new GridBagLayout());
	add(header,  new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 60, 10, 15), 0, 0));
	add(firstTeamName,  new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
	add(secondTeamName,  new GridBagConstraints(0, 1 + PLAYERS_COUNT/2 + 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
	
	for (int i=0; i<PLAYERS_COUNT; i++) {
	    int gridy = i + 2 + (i*2/PLAYERS_COUNT);
	    // 1. nickname
	    JLabel playerName = new JLabel();
	    players.add(playerName);
	    add(playerName,  new GridBagConstraints(0, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
	    
	    // 2. button
	    JButton reportButton = new JButton("Report");
	    reportButton.setSize(4, 4);
	    add(reportButton,  new GridBagConstraints(1, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 1, 10, 15), 0, -10));
	    final int iFinal = i;
	    MatchPanel thiz = this;
	    reportButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                ReportDialog dialog = new ReportDialog(thiz);
	                dialog.setModel(model.getPlayers().get(iFinal));
	                
	            }
	        });
	    reportButtons.add(reportButton);
	    
	    // 3. last reports description
	    JLabel report = new JLabel();
	    reports.add(report);
	    add(report,  new GridBagConstraints(2, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
	}
	setBorder(new EmptyBorder(10, 10, 10, 10));
	firstTeamName.setVisible(false);
	secondTeamName.setVisible(false);
	reportButtons.forEach(e -> e.setVisible(false));
    }
    
    public Match getModel() {
        return model;
    }

    public void setModel(Match model) {
	if (model == null || model.getPlayers().isEmpty()) {
	    header.setText("Match not started");
	    players.forEach(e -> e.setText(""));
	    reports.forEach(e -> e.setText(""));
	    reportButtons.forEach(e -> e.setVisible(false));
	    firstTeamName.setVisible(false);
	    secondTeamName.setVisible(false);
	} else {
	    header.setText("Current match:");
	    firstTeamName.setVisible(true);
	    secondTeamName.setVisible(true);
	    for (int i = 0; i < model.getPlayers().size(); i++) {
		String name = new Integer(model.getPlayers().get(i).getSteamId()).toString();
		if (model.getPlayers().get(i).getPlayerDetails() != null) {
		    name = model.getPlayers().get(i).getPlayerDetails().getNickName();
		    if (name == null || name.isEmpty()) {
			name = new Integer(model.getPlayers().get(i).getSteamId()).toString();
		    }
		    String winrate = model.getPlayers().get(i).getPlayerDetails().getWinRate();
		    name += " " + winrate;
		}

		players.get(i).setText(name);
		String reportString = "";
		for (Report r: model.getPlayers().get(i).getReports()) {
		    reportString += r.getStars() + ":" + r.getDescription() + "; ";
		}
		reports.get(i).setText(reportString);
	    }
	    reportButtons.forEach(e -> e.setVisible(true));
	}
        this.model = model;
    }

    public List<JLabel> getPlayers() {
        return players;
    }

    public void setPlayers(List<JLabel> players) {
        this.players = players;
    }
    
}
