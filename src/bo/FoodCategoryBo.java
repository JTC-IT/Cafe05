package bo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import bean.FoodCategory;
import dao.FoodCategoryDao;

public class FoodCategoryBo {
	private FoodCategoryDao fcDao;
	private ArrayList<FoodCategory> FoodCategoryList;
	
	public FoodCategoryBo() throws SQLException {
		fcDao = new FoodCategoryDao();
		FoodCategoryList = fcDao.getListFoodCategory();
	}
	
	public FoodCategory getFCategory(int id) {
		for(FoodCategory fc: FoodCategoryList)
			if(fc.getId() == id)
				return fc;
		return null;
	}
	
	public ArrayList<FoodCategory> getListFoodCategory() {
		return FoodCategoryList;
	}
	
	public DefaultComboBoxModel<String> getListName(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		ArrayList<FoodCategory> list = fcDao.getListFoodCategory();
		for(FoodCategory fc: list)
			model.addElement(fc.getName());
		return model;
	}
	
	public int getSize() {
		return FoodCategoryList.size();
	}
}
