package dotadodge.ui.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotadodge.core.file.MatchNotStartedException;
import dotadodge.core.main.DotaDodge;
import dotadodge.core.misc.GuiceFactory;
import dotadodge.core.misc.StdOutErrLog;
import dotadodge.core.model.Match;

public class DotaDodgeApplication extends JFrame {
    
    private final Logger logger = LoggerFactory.getLogger(DotaDodgeApplication.class);
    
    private MatchPanel matchPanel;
    
    private DotaDodge dotaDodge = GuiceFactory.getInjector().getInstance(DotaDodge.class);
    
    public DotaDodgeApplication() {
        super();
        initUI();
    }
    
    private void initUI() {
    	setUIFont (new javax.swing.plaf.FontUIResource(new Font("Constantia",Font.BOLD, 18)));

    	matchPanel = new MatchPanel();
        getContentPane().add(matchPanel);

        setTitle("Dota Dodge");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void initCore() {
	dotaDodge.run();
    }
    
    private void timer() {
	while (true) {
	    Match currentMatch;
	    Match previousMatch = matchPanel.getModel();
	    try {
		currentMatch = dotaDodge.getCurrentMatch();
		if (previousMatch == null || !(currentMatch.getStartDate().equals(previousMatch.getStartDate())))
		matchPanel.setModel(currentMatch);
	    } catch (MatchNotStartedException e1) {
		matchPanel.setModel(new Match());
		logger.trace("match not started yet");
	    }
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
        	StdOutErrLog.tieSystemOutAndErrToLog();
                final DotaDodgeApplication main = new DotaDodgeApplication();
                
                Thread threadCore = new Thread() {
        	    public void run() {
        		main.initCore();
        		main.timer();
        	    };
        	};
        	threadCore.start();
            }
        });
    }
    
    private static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, f);
            }
        }
    }

}
