package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import bean.FoodCategory;
import bo.FoodBo;
import bo.FoodCategoryBo;
import bo.TableFoodBo;
import dao.ConnecDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

public class CafeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTop, panelLeft, panelCenter;
	private static JPanel PanelRight;
	private JPanel PanelBottom;
	
	private static JLabel lblMenu,lblBanUse, lblBanEmpty, lblGreen, lblGray;
	private static JTextField txtSearch;
	private JLabel lblSearch_Icon;
	
	private static MyButton btnTable;
	private static JLabel lblSearch;
	private static MyButton[] listItemFCategory;
	
	private static PanelBill panelBill;
	private static PanelPay panelPay;
	private static PanelTableMove panelTableMove;
	
	private static PanelFood paneFood;
	private static PanelTable paneTable;
	
	private FoodCategoryBo fcBo;
	private FoodBo foodBo;
	private static TableFoodBo tableFoodBo;
	
	private static int itemLeft;
	private static int fcsize;
	
	public CafeFrame() throws SQLException {
		UIManager.put("OptionPane.background", Main.myColor);
		UIManager.put("OptionPane.messageForeground", Color.ORANGE);
		UIManager.put("Panel.background", Main.myColor);
		UIManager.put("ScrollBarUI", "view.MyScrollBarUI");
		
		new ConnecDataBase();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(Main.checkConfirm("Close System!", "Bạn có muốn đóng chương trình ko?")) {
					ConnecDataBase.close();
					System.exit(0);
				}
			}
		});
		setTitle("Quản lý tính tiền quán Cafe");
		setIconImage(new ImageIcon(CafeFrame.class.getResource("/imgs/logo.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(1000,700));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		fcBo = new FoodCategoryBo();
		foodBo = new FoodBo();
		tableFoodBo = new TableFoodBo();

		setPanelTop();
		setPanelBottom();
		setPanelCenter();
		setPanelRight();
		setPanelLeft();
	}
	
	public void setPanelTop() throws SQLException {
		panelTop = new JPanel();
		panelTop.setBackground(new Color(93,84,109));
		panelTop.setLayout(new BorderLayout());
		panelTop.setPreferredSize(new Dimension(0, 100));
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		ImagePanel plogo = new ImagePanel();
		plogo.setImage("/imgs/head.jpg");
		plogo.setText("Cafe 05");
		plogo.setPreferredSize(new Dimension(196,0));
		panelTop.add(plogo,BorderLayout.WEST);
		
		Clock clock = new Clock();
		Thread t = new Thread(clock);
		t.start();
		
		clock.setPreferredSize(new Dimension(170,0));
		panelTop.add(clock, BorderLayout.EAST);
		
		SpringLayout layout = new SpringLayout();
		
		JPanel panelHead = new JPanel(layout);
		panelHead.setOpaque(false);
		panelTop.add(panelHead, BorderLayout.CENTER);
		
		lblBanUse = new JLabel();
		lblBanUse.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblBanUse.setForeground(Color.WHITE);
		panelHead.add(lblBanUse);
		layout.putConstraint(SpringLayout.NORTH, lblBanUse, 15, SpringLayout.NORTH, panelHead);
		layout.putConstraint(SpringLayout.WEST, lblBanUse, 30, SpringLayout.WEST, panelHead);
		
		lblBanEmpty = new JLabel();
		lblBanEmpty.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblBanEmpty.setForeground(Color.WHITE);
		panelHead.add(lblBanEmpty);
		layout.putConstraint(SpringLayout.SOUTH, lblBanEmpty, -20, SpringLayout.SOUTH, panelHead);
		layout.putConstraint(SpringLayout.WEST, lblBanEmpty, 30, SpringLayout.WEST, panelHead);
		
		lblGreen = new JLabel();
		lblGreen.setOpaque(true);
		lblGreen.setBackground(Color.green);
		lblGreen.setPreferredSize(new Dimension(15,15));
		panelHead.add(lblGreen);
		layout.putConstraint(SpringLayout.NORTH, lblGreen, 23, SpringLayout.NORTH, panelHead);
		layout.putConstraint(SpringLayout.WEST, lblGreen, 218, SpringLayout.WEST, panelHead);
		
		lblGray = new JLabel();
		lblGray.setOpaque(true);
		lblGray.setBackground(Color.LIGHT_GRAY);
		lblGray.setPreferredSize(new Dimension(15,15));
		panelHead.add(lblGray);
		layout.putConstraint(SpringLayout.SOUTH, lblGray, -24, SpringLayout.SOUTH, panelHead);
		layout.putConstraint(SpringLayout.WEST, lblGray, 140, SpringLayout.WEST, panelHead);
		
		txtSearch = new JTextField();
		txtSearch.setOpaque(false);
		txtSearch.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtSearch.setCaretColor(Color.white);
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setPreferredSize(new Dimension(350, 30));
		txtSearch.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				txtSearch.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			}
			public void focusGained(FocusEvent e) {
				txtSearch.setBorder(new LineBorder(Color.green, 1));
			}
		});
		txtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				lblSearch.setVisible(txtSearch.getText().isEmpty());
				search();
			}
		});
		panelHead.add(txtSearch);
		layout.putConstraint(SpringLayout.SOUTH, txtSearch, -15, SpringLayout.SOUTH, panelHead);
		layout.putConstraint(SpringLayout.EAST, txtSearch, -30, SpringLayout.EAST, panelHead);
		
		lblSearch_Icon = new JLabel(new ImageIcon(CafeFrame.class.getResource("/imgs/search-24.png")));
		panelHead.add(lblSearch_Icon);
		layout.putConstraint(SpringLayout.SOUTH, lblSearch_Icon, -18, SpringLayout.SOUTH, panelHead);
		layout.putConstraint(SpringLayout.EAST, lblSearch_Icon, -35, SpringLayout.EAST, panelHead);
		
		lblSearch = new JLabel("Tìm bàn");
		lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSearch.setForeground(Color.LIGHT_GRAY);
		panelHead.add(lblSearch);
		layout.putConstraint(SpringLayout.SOUTH, lblSearch, -19, SpringLayout.SOUTH, panelHead);
		layout.putConstraint(SpringLayout.WEST, lblSearch, -370, SpringLayout.EAST, panelHead);
	}
	
	public void setPanelLeft() throws SQLException {
		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(122,111,143));
		panelLeft.setPreferredSize(new Dimension(196,0));
		
		fcsize = fcBo.getSize();
		panelLeft.setLayout(new GridLayout(2+fcsize, 1, 0, 0));
		
		btnTable = new MyButton();
		btnTable.setText("Bàn");
		btnTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnTable.setFont(22);
		btnTable.setIcon("/imgs/table-48.png");
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTableFood();
			}
		});
		btnTable.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(itemLeft != -1) {
					btnTable.setBackground(Color.decode("#5d546d"));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(itemLeft != -1) {
					btnTable.setBackground(new Color(122,111,143));
				}
			}
		});
		panelLeft.add(btnTable);
		
		lblMenu = new JLabel("Đồ uống:     ");
		lblMenu.setIcon(new ImageIcon(CafeFrame.class.getResource("/imgs/food-48.png")));
		lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblMenu.setForeground(Color.white);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
		panelLeft.add(lblMenu);
		
		listItemFCategory = new MyButton[fcsize];
		int i = 0;
		for(FoodCategory fc: fcBo.getListFoodCategory()) {
			listItemFCategory[i] = new MyButton();
			listItemFCategory[i].setText(fc.getName());
			listItemFCategory[i].setId(fc.getId());
			listItemFCategory[i].setForeground(Color.green);
			listItemFCategory[i].setFont(18);
			listItemFCategory[i].setBackground(new Color(122,111,143));
			listItemFCategory[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPanelRight(0);
					for(int i = 0; i < fcsize; i++)
						if(e.getSource() == listItemFCategory[i])
							showFoodCategory(i);
				}
			});
			listItemFCategory[i].addMouseListener(mouseAdapterLeft);
			panelLeft.add(listItemFCategory[i]);
			i++;
		}
		itemLeft = -2;
		showTableFood();
		
		contentPane.add(panelLeft, BorderLayout.WEST);
	}
	
	public void setPanelCenter() throws SQLException {
		panelCenter = new JPanel();
		panelCenter.setOpaque(false);
		panelCenter.setLayout(new CardLayout());
		
		paneTable = new PanelTable(tableFoodBo);
		panelCenter.add(paneTable);
		
		paneFood = new PanelFood(foodBo);
		panelCenter.add(paneFood);
		
		contentPane.add(panelCenter, BorderLayout.CENTER);
	}
	
	public void setPanelBottom() {
		PanelBottom = new JPanel();
		PanelBottom.setBackground(Color.decode("#5D546D"));
		PanelBottom.setPreferredSize(new Dimension(0,25));
		PanelBottom.setLayout(new BorderLayout());
		
		JLabel lblContact = new JLabel("Trần Trung Chính - 18t1021022@husc.edu.vn - 0362.942.329"
				, new ImageIcon(CafeFrame.class.getResource("/imgs/c-18.png"))
				, SwingConstants.CENTER);
		lblContact.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblContact.setVerticalAlignment(SwingConstants.CENTER);
		lblContact.setVerticalTextPosition(SwingConstants.CENTER);
		lblContact.setForeground(Color.white);
		PanelBottom.add(lblContact);
		
		contentPane.add(PanelBottom, BorderLayout.SOUTH);
	}
	
	public void setPanelRight() throws SQLException {
		PanelRight = new JPanel();
		PanelRight = new JPanel();
		PanelRight.setLayout(new CardLayout());
		PanelRight.setBackground(new Color(122,111,143));
		
		panelBill = new PanelBill(foodBo);
		PanelRight.add(panelBill);
		
		panelPay = new PanelPay();
		PanelRight.add(panelPay);
		
		panelTableMove = new PanelTableMove();
		PanelRight.add(panelTableMove);
		
		contentPane.add(PanelRight, BorderLayout.EAST);
	}
	
	public static void showPanelCenter(int k) {
		paneTable.setVisible(k==0);
		paneFood.setVisible(k==1);
		if(k==0) {
			lblSearch.setText("Tìm bàn");
		}else lblSearch.setText("Tìm món");
	}
	
	public static void showPanelRight(int k) {
		PanelRight.setVisible(k != -1);
		panelBill.setVisible(k == 0);
		panelPay.setVisible(k == 1);
		panelTableMove.setVisible(k == 2);
	}
	
	public static void setlblBanUse(int use, int empty) {
		lblBanUse.setText("Bàn đang phục vụ:     "+use);
		lblBanEmpty.setText("Bàn trống:     "+empty);
	}
	
	public static void showTableFood() {
		if(itemLeft != -1) {
			if(itemLeft > -1) {
				listItemFCategory[itemLeft].setBackground(new Color(122,111,143));
				listItemFCategory[itemLeft].setForeground(Color.green);
			}
			btnTable.setForeground(Color.white);
			btnTable.setBackground(Color.decode("#383342"));
			
			setVisibleItemFcategory(false);
			showPanelCenter(0);
			showPanelRight(-1);
			itemLeft = -1;
		}
		txtSearch.setText("");
		lblSearch.setVisible(true);
		search();
	}
	
	public static void setVisibleItemFcategory(boolean b) {
		lblMenu.setVisible(b);
		for(int i = 0; i < fcsize; i++) {
			listItemFCategory[i].setVisible(b);
		}
		if(b) {
			showPanelRight(0);
			showPanelCenter(1);
			showFoodCategory(0);
		}
	}
	
	public static void showFoodCategory(int i) {
		if(i != itemLeft) {
			if(itemLeft == -1) {
				btnTable.setForeground(Color.yellow);
				btnTable.setBackground(new Color(122,111,143));
			}else {
				listItemFCategory[itemLeft].setBackground(new Color(122,111,143));
				listItemFCategory[itemLeft].setForeground(Color.green);
			}
			listItemFCategory[i].setBackground(Color.decode("#383342"));
			listItemFCategory[i].setForeground(Color.white);
			
			PanelFood.showFoodOfCategory(listItemFCategory[i].getId());
			
			itemLeft = i;
		}
		txtSearch.setText("");
		lblSearch.setVisible(true);
	}
	
	MouseAdapter mouseAdapterLeft = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			for(int i = 0; i < fcsize; i++) {
				if(e.getSource() == listItemFCategory[i]) {
					if(i != itemLeft)
						listItemFCategory[i].setBackground(Color.decode("#5d546d"));
				}
			}
		}
		public void mouseExited(MouseEvent e) {
			for(int i = 0; i < fcsize; i++) {
				if(e.getSource() == listItemFCategory[i]) {
					if(i != itemLeft)
						listItemFCategory[i].setBackground(new Color(122,111,143));
				}
			}
		}
	};
	
	public static void setPanelTableMove(int id) {
		panelTableMove.setPanel(tableFoodBo, id);
		showPanelRight(2);
	}
	
	public static void search() {
		if(lblSearch.getText().equals("Tìm bàn"))
			PanelTable.searchTable(txtSearch.getText().trim());
		else 
			PanelFood.searchFood(listItemFCategory[itemLeft].getId(), txtSearch.getText().trim());
	}
}