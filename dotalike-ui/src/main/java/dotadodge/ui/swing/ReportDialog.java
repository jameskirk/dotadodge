package dotadodge.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import dotalike.common.model.Player;
import dotalike.common.model.Report;
import dotalike.core.misc.GuiceFactory;
import dotalike.core.service.DotaDodgeService;

public class ReportDialog extends JDialog {
    
    private Player model;
    
    private MatchPanel parentComponent;
    
    private JLabel headerLabel;
    
    private JComboBox<String> stars;
    
    private JTextArea reportDescription;
    
    private JButton submitButton;
    
    private DotaDodgeService dotaDodgeService = GuiceFactory.getInjector().getInstance(DotaDodgeService.class);
    
    public ReportDialog(MatchPanel c) {
	super(SwingUtilities.windowForComponent(c));
	parentComponent = c;
	setSize(300, 200);
	setLocationRelativeTo(c);
        setVisible(true);
        
        JPanel panel = new JPanel();
        headerLabel = new JLabel("Write report for player:");
        panel.add(headerLabel);
        stars = new JComboBox<String>(new String[]{"1", "2", "3", "4", "5"});
        stars.setSize(50, 40);
        panel.add(stars);
        reportDescription = new JTextArea(3, 20);
        reportDescription.setWrapStyleWord(true);
        panel.add(reportDescription);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
        	Report report = new Report();
        	report.setDescription(reportDescription.getText());
        	report.setStars(Integer.parseInt((String) stars.getSelectedItem()));
        	dotaDodgeService.report(report, model.getSteamId(), null);
        	model.getReports().add(report);
        	parentComponent.setModel(parentComponent.getModel());
        	dispose();
            }
        });
        panel.add(submitButton);
        add(panel);
    }
    
    public void setModel(Player model) {
	headerLabel.setText("Write report for player " + model.getSteamId());
	this.model = model;
    }

}
