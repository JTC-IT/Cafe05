package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bean.Food;
import bean.FormatMoney;
import bo.FoodBo;

public class PanelFood extends JPanel implements ActionListener{
	
	public class FoodButton extends JButton{
		private static final long serialVersionUID = 1L;
		private int id;
		private Image img;

		public FoodButton(Food f) {
			super();
			this.id = f.getId();
			setFont(new Font("Segoe UI", Font.PLAIN, 16));
			setText(f.getName(),f.getPrice());
			setForeground(Color.WHITE);
			setContentAreaFilled(false);
			setBorder(new EmptyBorder(5, 10, 5, 10));
			setHorizontalAlignment(SwingConstants.CENTER);
			setHorizontalTextPosition(SwingConstants.CENTER);
			setVerticalAlignment(SwingConstants.CENTER);
			setVerticalTextPosition(SwingConstants.CENTER);
			setImage("/imgs/green.png");
			setPressedImage("/imgs/green2.png");
		}

		public int getId() {
			return id;
		}
		
		public void setText(String name, int price) {
			setText("<html><b>"+name+"</b><br>"+FormatMoney.formatVnd(price)+"</html>");
		}
		
		public void setImage(String path) {
			img = new ImageIcon(FoodButton.class.getResource(path)).getImage();
			setIcon(new ImageIcon(img.getScaledInstance(140, 80, java.awt.Image.SCALE_SMOOTH)));
		}
		
		public void setPressedImage(String path) {
			img = new ImageIcon(FoodButton.class.getResource(path)).getImage();
			setPressedIcon(new ImageIcon(img.getScaledInstance(140, 80, java.awt.Image.SCALE_SMOOTH)));
		}
	}
	
	private static final long serialVersionUID = 1L;
	private static FoodBo foodBo;
	private static ArrayList<FoodButton> listButtonFood;
	
	public PanelFood(FoodBo fb) throws SQLException {
		setOpaque(false);
		setBorder(new EmptyBorder(15,10,10,10));
		setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		
		foodBo = fb;
		listButtonFood = new ArrayList<>();
		
		for(Food food: foodBo.getListFood()) {
			FoodButton btnFood = new FoodButton(food);
			btnFood.setPreferredSize(new Dimension(140,80));
			btnFood.addActionListener(this);
			add(btnFood);
			btnFood.setVisible(false);
			listButtonFood.add(btnFood);
		}
	}
	
	public static void showFoodOfCategory(int id) {
		for(FoodButton btn: listButtonFood)
			btn.setVisible(foodBo.getFood(btn.getId()).getCategory() == id);
	}
	
	public static void searchFood(int id, String name) {
		for(FoodButton btn: listButtonFood) {
			Food f = foodBo.getFood(btn.getId());
			btn.setVisible(f.getCategory() == id && f.getName().toLowerCase().indexOf(name.toLowerCase()) >= 0);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CafeFrame.showPanelRight(0);
		for(FoodButton btn: listButtonFood) {
			if(e.getSource() == btn) {
				try {
					PanelBill.addFood(foodBo.getFood(btn.getId()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}