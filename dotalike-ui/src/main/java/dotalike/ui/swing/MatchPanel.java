package dotalike.ui.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import dotalike.core.main.MatchPanelModel;
import dotalike.core.misc.GuiceFactory;

public class MatchPanel extends JPanel {
	
	private final Logger logger = LoggerFactory.getLogger(MatchPanel.class);
    
    private static final int PLAYERS_COUNT = 10;
    
    private MatchPanelModel model = new MatchPanelModel(new Match());
    
    // Headers
    private JLabel header = new JLabel("Loading...");
    
    private JLabel firstTeamName = new JLabel("Radiant");
    
    private JLabel secondTeamName = new JLabel("Dire");
    
    private JLabel signatureHeader = new JLabel("Signature");
    
    private JLabel lastHeroesHeader = new JLabel("Last matches");
    
    private JLabel soloMMRHeader = new JLabel("Solo MMR");
    
    // Rows
    private List<MatchPanelRow> rows = new ArrayList<>();
    
    private DotaLikeEngine dotaLikeEngine = GuiceFactory.getInjector().getInstance(DotaLikeEngine.class);
    
    public MatchPanel() {
		setLayout(new GridBagLayout());
		add(header,  new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 60, 10, 15), 0, 0));
		add(firstTeamName,  new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
		add(secondTeamName,  new GridBagConstraints(0, 1 + PLAYERS_COUNT/2 + 1, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
		add(signatureHeader,  new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
		add(lastHeroesHeader,  new GridBagConstraints(3, 1, 10, 1, 0, 0, GridBagConstraints.CENTER,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
		add(soloMMRHeader,  new GridBagConstraints(13, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
		
		for (int i=0; i<PLAYERS_COUNT; i++) {
		    int gridy = i + 2 + (i*2/PLAYERS_COUNT);
		    MatchPanelRow row = new MatchPanelRow();
		    rows.add(row);
		    // 1. nickname
		    add(row.getNickname(),  new GridBagConstraints(0, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
		    // 2. like
		    row.getLikeComponent().setVisible(false);
			add(row.getLikeComponent(), new GridBagConstraints(1, gridy, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
					new Insets(5, 1, 5, 15), 0, 0));
			// 3. signature
			for (JLabel s: row.getSignatures()) {
			    add(s, new GridBagConstraints(2, gridy, 1, 1, 0, 0, GridBagConstraints.CENTER,
			            GridBagConstraints.NONE, new Insets(0, 0, 10, 30), 0, 0));
			}
			
			// 4. label private account
			add(row.getAccountIsPrivate(),  new GridBagConstraints(3, gridy, 10, 1, 0, 0, GridBagConstraints.CENTER,
		            GridBagConstraints.NONE, new Insets(0, 0, 10, 15), 0, 0));
			
			// 4b. last heroes
			for (int j=0; j<row.getLastHeroes().size(); j++) {
				add(row.getLastHeroes().get(j), new GridBagConstraints(3 + j, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
						GridBagConstraints.NONE, new Insets(0, 1, 10, 15), 0, -10));
			}
			
			// 5. solo mmr
		    add(row.getSoloMMR(),  new GridBagConstraints(13, gridy, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 20, 10, 15), 0, 0));
		    
		}
		setBorder(new EmptyBorder(10, 10, 10, 10));
		clearPanel();
		setBackground(Constants.frameBackgroundColor);
		header.setForeground(Constants.fontColor);
		firstTeamName.setForeground(Constants.fontHeaderColor);
		secondTeamName.setForeground(Constants.fontHeaderColor);
		soloMMRHeader.setForeground(Constants.fontHeaderColor);
		signatureHeader.setForeground(Constants.fontHeaderColor);
		lastHeroesHeader.setForeground(Constants.fontHeaderColor);
	}
    
    public MatchPanelModel getModel() {
        return model;
    }

	public void setModel(MatchPanelModel model) {
		Match match = model.getMatch();
		if (model.getErrorMessage() != null) {
			clearPanel();
			header.setText(model.getErrorMessage());
			header.setVisible(true);
		} else if (match == null || match.getPlayers().isEmpty()) {
			clearPanel();
			header.setText("Match not started");
			header.setVisible(true);
		} else {
			clearPanel();
			header.setText("Current match:");
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
						rows.get(i).getAccountIsPrivate().setVisible(true);
					}

					int gridy = i + 2 + (i * 2 / PLAYERS_COUNT);
					String soloMMRText = "";
					if (match.getPlayers().get(i).getSoloMmr() != null) {
						soloMMRText = match.getPlayers().get(i).getSoloMmr().toString();
					}
					rows.get(i).getSoloMMR().setText(soloMMRText);

					if (match.getPlayers().get(i).getSignatures().get(0).getAllMatches() != 0) {
						try {
							String fileName = "heroes\\" + match.getPlayers().get(i).getSignatures().get(0).getHero() + ".jpg";
							BufferedImage myPicture = ImageIO.read(new File(
									System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName));
							myPicture = resize(myPicture, 128 / 3, 72 / 5 * 2);
							rows.get(i).getSignatures().get(0).setIcon(new ImageIcon(myPicture));
						} catch (IOException e) {
							logger.error("Can not find image for: " + match.getPlayers().get(i).getSignatures().get(i).getHero(), e);
						}
					}
					
					try {
						int iHero = 0;
						for (Match m : match.getPlayers().get(i).getLastMatches()) {
							String fileName = "heroes\\" + m.getPlayersInMatch().get(0).getHero() + ".jpg";
							try {
								BufferedImage myPicture = ImageIO.read(new File(
										System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName));
								myPicture = resize(myPicture, 128 / 3, 72 / 2);
								rows.get(i).getLastHeroes().get(iHero).setIcon(new ImageIcon(myPicture));
							} catch (IIOException e) {
								logger.error("Can not find image for: " + m.getPlayersInMatch().get(0).getHero(), e);
							}
							Border b;
							if (m.isWin()) {
								b = BorderFactory.createMatteBorder(1, 2, 1, 2, Color.GREEN);
							} else {
								b = BorderFactory.createMatteBorder(1, 2, 1, 2, Color.RED);
							}
							rows.get(i).getLastHeroes().get(iHero).setBorder(b);
							iHero++;
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				rows.get(i).getNickname().setText(name);
				if (match.getPlayers().get(i).getSteamId() == dotaLikeEngine.getMySteamId()) {
					rows.get(i).getNickname().setForeground(Constants.fontHeaderColor);
				}
			}
			setVisibleForAll();
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

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	if (firstTeamName.isVisible()) {
	        Graphics2D g2 = (Graphics2D) g;
	        for (int i=0; i<4; i++) {
	        	int y = 130 + i*43;
	        	drawLine(g2, y);
	        }
	        for (int i=0; i<4; i++) {
	        	int y = 368 + i*43;
	        	drawLine(g2, y);
	        }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    private void clearPanel() {
    	header.setText("");
    	header.setVisible(false);
    	rows.forEach(e -> e.clearPanel());
	    firstTeamName.setVisible(false);
	    secondTeamName.setVisible(false);
	    soloMMRHeader.setVisible(false);
	    signatureHeader.setVisible(false);
	    lastHeroesHeader.setVisible(false);
    }
    
    private void setVisibleForAll() {
    	header.setVisible(true);
    	firstTeamName.setVisible(true);
		secondTeamName.setVisible(true);
		rows.forEach(e -> e.setVisibleForAll());
		soloMMRHeader.setVisible(true);
		signatureHeader.setVisible(true);
		lastHeroesHeader.setVisible(true);
    }
    
    private void drawLine(Graphics2D g2, int y) {
    	Line2D lin = new Line2D.Float(45, y, 1050, y);
        Line2D lin2 = new Line2D.Float(45, y+1, 1050, y+1);
        g2.draw(lin);
        g2.draw(lin2);
    }
    
}
