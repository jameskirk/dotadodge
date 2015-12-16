package dotadodge.ui.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
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
    
    private List<JLabel> players = new ArrayList<JLabel>();
    
    private List<JButton> reportButtons= new ArrayList<JButton>();
    
    private List<JLabel> reports = new ArrayList<JLabel>();
    
    //private ReportDialog reportDialog;

    public MatchPanel() {
	setLayout(new GridBagLayout());
	for (int i=0; i<PLAYERS_COUNT; i++) {
	    // 1. nickname
	    JLabel playerName = new JLabel("");
	    players.add(playerName);
	    add(playerName,  new GridBagConstraints(0, i, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
	    
	    // 2. button
	    JButton reportButton = new JButton("Report");
	    reportButton.setSize(4, 4);
	    add(reportButton,  new GridBagConstraints(1, i, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 1, 10, 15), 0, -10));
	    final int k = i;
	    reportButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	        	Window frame = (Window)reportButton.getParent().getParent().getParent().getParent().getParent();
	        	System.out.println("frame " + frame);
	                ReportDialog dialog = new ReportDialog(frame);
	                dialog.setModel(model.getPlayers().get(k));
	                
	            }
	        });
	    reportButtons.add(reportButton);
	    
	    // 3. last reports description
	    JLabel report = new JLabel();
	    reports.add(report);
	    add(report,  new GridBagConstraints(2, i, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
	}
	setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    
    
    public Match getModel() {
        return model;
    }

    public void setModel(Match model) {
	if (model == null || model.getPlayers().isEmpty()) {
	    players.forEach(e -> e.setText(""));
	    setVisible(false);
	} else {
	    for (int i = 0; i < model.getPlayers().size(); i++) {
		players.get(i).setText("Player " + (i + 1) + " : " + model.getPlayers().get(i).getSteamId());
		String reportString = "";
		for (Report r: model.getPlayers().get(i).getReports()) {
		    reportString += r.getStars() + ":" + r.getDescription() + "; ";
		}
		reports.get(i).setText(reportString);
	    }
	    setVisible(true);
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
