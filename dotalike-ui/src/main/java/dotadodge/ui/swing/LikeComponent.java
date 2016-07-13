package dotadodge.ui.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LikeComponent extends JPanel {
	
	private JLabel likeLabel = new JLabel();
	
	private JLabel dislikeLabel = new JLabel();
	
	private JLabel likeCountLabel;
	
	private JLabel dislikeCountLabel;
	
	private Integer likeCount = new Integer(0);
	
	private Integer dislikeCount = new Integer(0);
	
	private boolean isLiked;
	
	private boolean isDisliked;
	
	public LikeComponent() {
		likeCountLabel = new JLabel(likeCount.toString());
		dislikeCountLabel = new JLabel(dislikeCount.toString());
		setBackground(new Color(30, 40, 41));
		Color fontColor = new Color(185, 191, 191);
		likeCountLabel.setForeground(fontColor);
		dislikeCountLabel.setForeground(fontColor);
		try {
			String fileName = "img\\" + "like" + ".png";
			BufferedImage myPicture = ImageIO.read(new File(System.getProperty("user.dir") 
					+ "\\src\\main\\resources\\" + fileName));
			//myPicture = MatchPanel.resize(myPicture, 128/3, 72/2);
			likeLabel = new JLabel(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String fileName = "img\\" + "dislike" + ".png";
			BufferedImage myPicture = ImageIO.read(new File(System.getProperty("user.dir") 
					+ "\\src\\main\\resources\\" + fileName));
			//myPicture = MatchPanel.resize(myPicture, 128/3, 72/2);
			dislikeLabel = new JLabel(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		add(likeLabel,  new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(dislikeLabel,  new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(likeCountLabel,  new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(-5, 7, 0, 0), 0, 0));
	    add(dislikeCountLabel,  new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(-5, 7, 0, 0), 0, 0));
	    
	    
	}

}
