package dotadodge.ui.main;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReportDialog extends JDialog {
    
    private JTextArea reportDescription;
    
    private JButton submitButton;
    
    public ReportDialog(Window c) {
	super(c);//(SwingUtilities.windowForComponent(c2));
	setSize(300, 200);
	setLocationRelativeTo(c);
        setVisible(true);
        
        JPanel panel = new JPanel();
        JLabel headerLabel = new JLabel("Write report for player:");
        panel.add(headerLabel);
        reportDescription = new JTextArea(3, 20);
        reportDescription.setWrapStyleWord(true);
        panel.add(reportDescription);
        submitButton = new JButton("Submit");
        panel.add(submitButton);
        add(panel);
    }

}
