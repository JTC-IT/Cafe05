package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.FoodCategory;

public class FoodCategoryDao {
	String sql;
	
	public FoodCategoryDao() throws SQLException {
		super();
	}

	public ArrayList<FoodCategory> getListFoodCategory(){
		sql = "select* from FoodCategory order by FoodCategoryName";
		ArrayList<FoodCategory> list = new ArrayList<FoodCategory>();
		try {
			Statement st = ConnecDataBase.getConnec().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				list.add(new FoodCategory(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
