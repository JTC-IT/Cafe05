package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Food;

public class FoodDao {
	String sql;
	
	public FoodDao() throws SQLException {
		super();
	}

	public ArrayList<Food> getListFood(){
		sql = "select * from Food order by FoodName";
		ArrayList<Food> list = new ArrayList<Food>();
		try {
			Statement st = ConnecDataBase.getConnec().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				list.add(new Food(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
