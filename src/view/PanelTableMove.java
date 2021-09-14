package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import bean.TableFood;
import bo.TableFoodBo;

public class PanelTableMove extends JPanel{
	private static final long serialVersionUID = 1L;
	private  int Id;
	private String Name;
	
	class TbButton extends JButton{
		
		private static final long serialVersionUID = 1L;
		private TableFood Table;
		
		public TbButton(TableFood table, Color color1, Color color2) {
			Table = new TableFood(table.getId(), table.getName(), table.getStatus());
			setText("Bàn " + Table.getName());
			setBackground(color1);
			setBorder(new LineBorder(Color.BLACK, 2, true));
			setFont(new Font("Segoe UI", Font.BOLD, 15));
			setForeground(Color.black);
			setHorizontalAlignment(SwingConstants.CENTER);
			setFocusPainted(false);
			setPreferredSize(new Dimension(0,30));
			
			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					setBackground(color2);
				}
				public void mouseExited(MouseEvent e) {
					setBackground(color1);
				}
			});
			
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moveTable();
				}
			});
		}

		public void moveTable() {
			if(Table.getId() != Id) {
				if(Table.getStatus()) {
					if(Main.checkConfirm("Gộp bàn", "Bàn "+Name+" gộp vào Bàn "+Table.getName()+"?")) {
						PanelBill.megreTable(Id, Table.getId());
						PanelTable.setStatusTable(Id, false);
						CafeFrame.showPanelRight(0);
						PanelTable.openTable(Table.getId());
					}
				}else if(Main.checkConfirm("Chuyển bàn", "Bàn "+Name+" chuyển tới Bàn "+Table.getName()+"?")) {
					PanelBill.moveTable(Id, Table.getId());
					PanelTable.setStatusTable(Table.getId(), true);
					PanelTable.setStatusTable(Id, false);
					CafeFrame.showPanelRight(0);
				}
			}
		}
	}
	
	private JPanel panel;

	public PanelTableMove() {
		setOpaque(false);
		setLayout(new BorderLayout());
		
		TitledBorder border = new TitledBorder(" Chọn Bàn chuyển đến ? ");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitleFont(new Font("Segoe UI", Font.BOLD, 24));
		border.setTitleColor(Color.white);
		setBorder(new CompoundBorder(border, new EmptyBorder(20, 10, 10, 10)));
		
		panel = new JPanel(new GridLayout(0, 3, 10, 10));
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(new Dimension(0,50));
		add(panel, BorderLayout.CENTER);
		
		JButton btnCancel = new JButton("Hủy bỏ"
				, new ImageIcon(PanelTableMove.class.getResource("/imgs/delete-24.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CafeFrame.showPanelRight(0);
			}
		});
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCancel.setForeground(Color.red);
		add(btnCancel, BorderLayout.PAGE_END);
	}
	
	public void setPanel(TableFoodBo tableFoodBo, int id) {
		Id = id;
		Name = "0";
		panel.removeAll();
		
		for(TableFood tf: tableFoodBo.getListTableFood()) {
			if(tf.getStatus()) {
				if(tf.getId() == id) {
					Name = tf.getName();
					panel.add(new TbButton(tf, Color.red, Color.red));
				}else
					panel.add(new TbButton(tf, Color.GREEN, Color.decode("#2cc79b")));
			}else
				panel.add(new TbButton(tf, Color.LIGHT_GRAY, Color.decode("#2cc79b")));
		}
	}
}
