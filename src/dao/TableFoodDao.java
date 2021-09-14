package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.TableFood;

public class TableFoodDao {
	String sql;
	
	public TableFoodDao() throws SQLException {
		super();
	}

	public ArrayList<TableFood> getListTableFood(){
		sql = "select* from TableFood";
		ArrayList<TableFood> list = new ArrayList<TableFood>();
		try {
			Statement st = ConnecDataBase.getConnec().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				list.add(new TableFood(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean updateStatus(int id, boolean status) {
		sql = "update TableFood set Status = ? where TableFoodId = ?";
		try {
			PreparedStatement ps = ConnecDataBase.getConnec().prepareStatement(sql);
			ps.setBoolean(1, status);
			ps.setInt(2, id);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}