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

public class MatchPanel extends JPanel {
    
    private static final int PLAYERS_COUNT = 10;
    
    private List<JLabel> players = new ArrayList<JLabel>();
    
    private List<JButton> reportButtons= new ArrayList<JButton>();

    public MatchPanel() {
	setLayout(new GridBagLayout());
	for (int i=0; i<PLAYERS_COUNT; i++) {
	    JLabel playerName = new JLabel("");
	    players.add(playerName);
	    add(playerName,  new GridBagConstraints(0, i, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
	    
	    JButton reportButton = new JButton("Report");
	    reportButton.setSize(4, 4);
	    add(reportButton,  new GridBagConstraints(1, i, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 1, 10, 1), 0, -10));
	    reportButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	        	Window frame = (Window)reportButton.getParent().getParent().getParent().getParent().getParent();
	        	System.out.println("frame " + frame);
	                ReportDialog dialog = new ReportDialog(frame);
	            }
	        });
	    reportButtons.add(reportButton);
	}
	setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public List<JLabel> getPlayers() {
        return players;
    }

    public void setPlayers(List<JLabel> players) {
        this.players = players;
    }
    
}
