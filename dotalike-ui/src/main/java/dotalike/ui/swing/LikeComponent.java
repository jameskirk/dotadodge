package dotalike.ui.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		likeCountLabel.setFont(Constants.fontSmall);
		dislikeCountLabel = new JLabel(dislikeCount.toString());
		dislikeCountLabel.setFont(Constants.fontSmall);
		setBackground(Constants.frameBackgroundColor);
		likeCountLabel.setForeground(Constants.fontColor);
		dislikeCountLabel.setForeground(Constants.fontColor);
		setLiked(false);
		setDisliked(false);
		likeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isLiked) {
					setLiked(true);
					setLikeCount(getLikeCount() + 1);
				} else {
					setLiked(false);
					setLikeCount(getLikeCount() - 1);
				}
				if (isDisliked) {
					setDisliked(false);
					setDislikeCount(getDislikeCount() - 1);
				}
			}
		});
		dislikeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isDisliked) {
					setDisliked(true);
					setDislikeCount(getDislikeCount() + 1);
				} else {
					setDisliked(false);
					setDislikeCount(getDislikeCount() - 1);
				}
				if (isLiked) {
					setLiked(false);
					setLikeCount(getLikeCount() - 1);
				}
			}
		});
		
		
		setLayout(new GridBagLayout());
		add(likeLabel,  new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(dislikeLabel,  new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
		            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(likeCountLabel,  new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(-3, 7, 0, 0), 0, 0));
	    add(dislikeCountLabel,  new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
	            GridBagConstraints.NONE, new Insets(-3, 7, 0, 0), 0, 0));
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
		likeCountLabel.setText(likeCount.toString());
	}

	public Integer getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(Integer dislikeCount) {
		this.dislikeCount = dislikeCount;
		dislikeCountLabel.setText(dislikeCount.toString());
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
		String fileName;
		if (!isLiked) {
			fileName = "like.png";
		} else {
			fileName = "likeSelected.png";
		}
		try {
			BufferedImage myPicture = ImageIO.read(new File(System.getProperty("user.dir") 
					+ "\\src\\main\\resources\\" + "img\\" + fileName));
			likeLabel.setIcon(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isDisliked() {
		return isDisliked;
	}

	public void setDisliked(boolean isDisliked) {
		this.isDisliked = isDisliked;
		String fileName;
		if (!isDisliked) {
			fileName = "dislike.png";
		} else {
			fileName = "dislikeSelected.png";
		}
		try {
			BufferedImage myPicture = ImageIO.read(new File(System.getProperty("user.dir") 
					+ "\\src\\main\\resources\\" + "img\\" + fileName));
			dislikeLabel.setIcon(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
