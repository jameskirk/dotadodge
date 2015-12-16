package dotadodge.ui.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        matchPanel = new MatchPanel();
        
        getContentPane().add(matchPanel);

        setTitle("Dota Dodge");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void initCore() {
	dotaDodge.run();
    }
    
    private void timer() {
	while (true) {
	    Match match = dotaDodge.getCurrentGameDao().getCurrentMatch();
	    //GuiceFactory.getInjector().getInstance(CustomStatisticDao.class).reportPlayer(new Report(), "", "matchId");
	    matchPanel.setModel(match);
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

}
