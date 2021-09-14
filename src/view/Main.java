package view;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Main {
	private CafeFrame frame;
	
	final static Color myColor = Color.decode("#383342");

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		try {
			frame = new CafeFrame();
			frame.setVisible(true);
		} catch (SQLException e) {
			showMessError("Unable to connect to database!", "Unable to connect to 'jdbc:sqlserver://localhost:1433;databaseName=CafeManageDB'!");
			System.exit(0);
			e.printStackTrace();
		}
	}
	
	public static boolean checkConfirm(String title, String message) {
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION
				, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}
	
	public static void showMessError(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showMessCorrect(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int getInputMess(String message, int k) {
		int n = -1;
		try {
			String kq = JOptionPane.showInputDialog(null, message,k);
			if(kq != null && !kq.isEmpty() && Integer.parseInt(kq) >= 0)
				n = Integer.parseInt(kq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}