package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;
	private String text;
	
	public ImagePanel() {
		super();
		setFont(new Font("MV Boli", Font.BOLD, 33));
		setForeground(Color.LIGHT_GRAY);
	}
	public ImagePanel(LayoutManager layout) {
		super(layout);
	}
	public void setImage(String path) {
		img = new ImageIcon(ImagePanel.class.getResource(path)).getImage();
	}
	public void setText(String st) {
		text = st;
	}
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	    g.drawString(text, (int)(getWidth()/6), getHeight()/2+10);
	}
}