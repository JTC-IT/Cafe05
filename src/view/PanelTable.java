package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.TableFood;
import bo.TableFoodBo;

public class PanelTable extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static TableFoodBo tbfoodBo;
	private static ArrayList<TableButton> listTable;
	private static int total, count;
	
	private class TableButton extends JButton{
		private static final long serialVersionUID = 1L;
		private int Id;
		private boolean isUsing;
		private String name;
		
		private ImageIcon imgPress, imgGreen, imgGray;
		private Image img;

		public TableButton(TableFood table) {
			
			imgPress = new ImageIcon(PanelTable.class.getResource("/imgs/presstb.png"));
			imgGreen = new ImageIcon(PanelTable.class.getResource("/imgs/greentb.png"));
			imgGray = new ImageIcon(PanelTable.class.getResource("/imgs/graytb.png"));
			
			Id = table.getId();
			setName(table.getName());
			setUsing(table.getStatus());
			
			setFont(new Font("Segoe UI", Font.BOLD, 18));
			setBorder(null);
			setForeground(Color.YELLOW);
			setContentAreaFilled(false);
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					setImage(imgPress);
				}
				public void mouseExited(MouseEvent e) {
					if(isUsing) {
						setImage(imgGreen);
					}else {
						setImage(imgGray);
					}
				}
				public void mouseReleased(MouseEvent e) {
					if(isUsing) {
						setImage(imgGreen);
					}else {
						setImage(imgGray);
					}
				}
			});
		}
		
		public int getId() {
			return Id;
		}
		
		public void setName(String s) {
			name = s;
			repaint();
		}
		
		public String getName() {
			return name;
		}
		
		public void setUsing(boolean using) {
			isUsing = using;
			if(using) {
				setImage(imgGreen);
			}else {
				setImage(imgGray);
			}
		}
		
		public void setImage(ImageIcon m) {
			img = m.getImage();
			repaint();
		}
		
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		    g.drawString("Bàn "+name, (int)(getWidth()/2)-25, getHeight()/2+5);
		}
	}
	
	public PanelTable(TableFoodBo tbbo) throws SQLException {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setOpaque(false);
		
		tbfoodBo = tbbo;
		refeshPaneTable();
	}
	
	public void refeshPaneTable() {
		total = tbfoodBo.getSize();
		int n = (int) Math.sqrt(total);
		removeAll();
		setLayout(new GridLayout(n, 0, 15, 15));
		listTable = new ArrayList<>();
		count = 0;
		for(TableFood table:  tbfoodBo.getListTableFood()) {
			TableButton btn = new TableButton(table);
			btn.addActionListener(this);
			if(table.getStatus())
				count++;
			listTable.add(btn);
			add(btn);
		}
		CafeFrame.setlblBanUse(count,total-count);
	}
	
	public void actionPerformed(ActionEvent e) {
		for(TableButton btn: listTable)
			if(e.getSource() == btn)
				openTable(btn.getId());
	}
	
	public static void openTable(int id) {
		CafeFrame.setVisibleItemFcategory(true);
		PanelBill.setPaneHead(tbfoodBo.getTableFood(id));
	}
	
	public static void setStatusTable(int id, boolean status) {
		tbfoodBo.setStatus(id, status);
		for(TableButton btn: listTable)
			if(btn.getId() == id) {
				if(status) {
					PanelBill.setPaneHead(tbfoodBo.getTableFood(id));
					btn.setUsing(true);
					count++;
				}else {
					btn.setUsing(false);
					btn.setComponentPopupMenu(null);
					count--;
				}
				CafeFrame.setlblBanUse(count,total-count);
				return;
			}
	}
	
	public static void searchTable(String name) {
		for(TableButton btn: listTable)
			btn.setVisible(btn.getName().indexOf(name) >= 0);
	}
}