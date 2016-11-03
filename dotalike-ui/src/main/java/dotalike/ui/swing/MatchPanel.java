package dotalike.ui.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dotalike.common.model.Match;
import dotalike.core.main.DotaLikeEngine;
import dotalike.core.misc.GuiceFactory;

public class MatchPanel extends JPanel {
	
	private final Logger logger = LoggerFactory.getLogger(MatchPanel.class);
    
    private static final int PLAYERS_COUNT = 10;
    
    private MatchPanelModel model = new MatchPanelModel(new Match());
    
    private JLabel header = new JLabel("Loading...");
    
    private JLabel firstTeamName = new JLabel("Radiant");
    
    private JLabel secondTeamName = new JLabel("Dire");
    
    private JLabel soloMMRHeader = new JLabel("Solo MMR");
    
    private List<JLabel> players = new ArrayList<JLabel>();
    
    private List<JLabel> lastHeroes = new ArrayList<JLabel>();
    
    private List<JLabel> accountIsPrivateList = new ArrayList<JLabel>();
    
    private List<LikeComponent> likeComponents = new ArrayList<>();
    
    private List<JLabel> soloMMRs = new ArrayList<JLabel>();
    
    private BufferedImage backgroundImage;
    
    private DotaLikeEngine dotaLikeEngine = GuiceFactory.getInjector().getInstance(DotaLikeEngine.class);
    
    public MatchPanel() {
		setLayout(new GridBagLayout());
		add(header,  new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 60, 10, 15), 0, 0));
		add(firstTeamName,  new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
		add(secondTeamName,  new GridBagConstraints(0, 1 + PLAYERS_COUNT/2 + 1, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
		add(soloMMRHeader,  new GridBagConstraints(12, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
		
		for (int i=0; i<PLAYERS_COUNT; i++) {
		    int gridy = i + 2 + (i*2/PLAYERS_COUNT);
		    // 1. nickname
		    JLabel playerName = new JLabel();
		    players.add(playerName);
		    add(playerName,  new GridBagConstraints(0, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
		    // 2a. like
		    LikeComponent likeComponent = new LikeComponent();
			likeComponents.add(likeComponent);
			likeComponent.setVisible(false);
			add(likeComponent, new GridBagConstraints(1, gridy, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
					new Insets(5, 1, 5, 15), 0, 0));
			// 2b. label private account
		    JLabel accountIsPrivate = new JLabel("account is private");
		    accountIsPrivate.setForeground(Constants.fontColor);
		    accountIsPrivate.setVisible(false);
			accountIsPrivateList.add(accountIsPrivate);
			add(accountIsPrivate,  new GridBagConstraints(2, gridy, 10, 1, 0, 0, GridBagConstraints.CENTER,
		            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
			// 3. solo mmr
		    JLabel soloMMR = new JLabel("");
		    soloMMRs.add(soloMMR);
		    add(soloMMR,  new GridBagConstraints(12, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
		}
		setBorder(new EmptyBorder(10, 10, 10, 10));
		firstTeamName.setVisible(false);
		secondTeamName.setVisible(false);
		soloMMRHeader.setVisible(false);
		setBackground(Constants.frameBackgroundColor);
		header.setForeground(Constants.fontColor);
		firstTeamName.setForeground(Constants.fontHeaderColor);
		secondTeamName.setForeground(Constants.fontHeaderColor);
		soloMMRHeader.setForeground(Constants.fontHeaderColor);
		soloMMRs.forEach(e -> e.setForeground(Constants.fontColor));
		players.forEach(e -> e.setForeground(Constants.fontColor));
		try {
			backgroundImage= ImageIO.read(new File(
					System.getProperty("user.dir") + "\\src\\main\\resources\\" + "img\\matchPanelBackground.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    
    public MatchPanelModel getModel() {
        return model;
    }

	public void setModel(MatchPanelModel model) {
		Match match = model.getMatch();
		if (model.getErrorMessage() != null) {
			clearPanel();
			header.setText(model.getErrorMessage());
		} else if (match == null || match.getPlayers().isEmpty()) {
			clearPanel();
			header.setText("Match not started");
		} else {
			header.setText("Current match:");
			firstTeamName.setVisible(true);
			secondTeamName.setVisible(true);
			soloMMRHeader.setVisible(true);
			lastHeroes.forEach(e -> {
				e.setIcon(null);
				e.setVisible(false);
				e.repaint();
				remove(e);
			});
			lastHeroes.clear();
			likeComponents.forEach(e -> { e.setVisible(true); e.clear(); } );
			accountIsPrivateList.forEach(e -> e.setVisible(false));
			soloMMRs.forEach(e -> e.setText(""));
			players.forEach(e -> e.setForeground(Constants.fontColor));
			for (int i = 0; i < match.getPlayers().size(); i++) {
				String name = new Integer(match.getPlayers().get(i).getSteamId()).toString();
				if (match.getPlayers().get(i) != null) {
					name = match.getPlayers().get(i).getNickName();
					if (name == null || name.isEmpty()) {
						name = new Integer(match.getPlayers().get(i).getSteamId()).toString();
					}
					if (name.length() > 15) {
						name = name.substring(0, 15);
					}
					if (match.getPlayers().get(i).isAccountIsPrivate()) {
						accountIsPrivateList.get(i).setVisible(true);
					}

					int gridy = i + 2 + (i * 2 / PLAYERS_COUNT);
					String soloMMRText = "";
					if (match.getPlayers().get(i).getSoloMmr() != null) {
						soloMMRText = match.getPlayers().get(i).getSoloMmr().toString();
					}
					soloMMRs.get(i).setText(soloMMRText);

					try {
						int iHero = 0;
						for (Match m : match.getPlayers().get(i).getLastMatches()) {
							String fileName = "heroes\\" + m.getPlayersInMatch().get(0).getHero() + ".jpg";
							JLabel picLabel = new JLabel();
							try {
								BufferedImage myPicture = ImageIO.read(new File(
										System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName));
								myPicture = resize(myPicture, 128 / 3, 72 / 2);
								picLabel.setIcon(new ImageIcon(myPicture));
								lastHeroes.add(picLabel);
							} catch (IIOException e) {
								logger.error("Can not find image for: " + m.getPlayersInMatch().get(0).getHero(), e);
							}
							Border b;
							if (m.isWin()) {
								b = BorderFactory.createMatteBorder(1, 2, 1, 2, Color.GREEN);
							} else {
								b = BorderFactory.createMatteBorder(1, 2, 1, 2, Color.RED);
							}
							picLabel.setBorder(b);
							add(picLabel, new GridBagConstraints(2 + iHero, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
									GridBagConstraints.NONE, new Insets(0, 1, 10, 15), 0, -10));
							iHero++;
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				players.get(i).setText(name);
				if (match.getPlayers().get(i).getSteamId() == dotaLikeEngine.getMySteamId()) {
					players.get(i).setForeground(Constants.fontHeaderColor);
				}
			}
		}
		this.model = model;
	}
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  

    public List<JLabel> getPlayers() {
        return players;
    }

    public void setPlayers(List<JLabel> players) {
        this.players = players;
    }
    
    private void clearPanel() {
    	header.setText("");
	    players.forEach(e -> e.setText(""));
	    firstTeamName.setVisible(false);
	    secondTeamName.setVisible(false);
	    soloMMRHeader.setVisible(false);
	    lastHeroes.forEach(e -> { e.setVisible(false); e.setIcon(null); remove(e);} );
	    likeComponents.forEach(e -> { e.setVisible(false); e.clear(); } );
	    accountIsPrivateList.forEach(e -> e.setVisible(false));
	    soloMMRs.forEach(e -> e.setText(""));
    }
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	if (header.getText().equals("Current match:")) {
	        Graphics2D g2 = (Graphics2D) g;
	        for (int i=0; i<4; i++) {
	        	int y = 106 + i*43;
	        	drawLine(g2, y);
	        }
	        for (int i=0; i<4; i++) {
	        	int y = 344 + i*43;
	        	drawLine(g2, y);
	        }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    private void drawLine(Graphics2D g2, int y) {
    	Line2D lin = new Line2D.Float(45, y, 1050, y);
        Line2D lin2 = new Line2D.Float(45, y+1, 1050, y+1);
        g2.draw(lin);
        g2.draw(lin2);
    }
    
}
