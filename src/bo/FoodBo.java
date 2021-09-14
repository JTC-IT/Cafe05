package bo;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Food;
import dao.FoodDao;

public class FoodBo {
	private FoodDao foodDao;
	private ArrayList<Food> FoodList;
	
	public FoodBo() throws SQLException{
		foodDao = new FoodDao();
		FoodList = foodDao.getListFood();
	}
	
	public ArrayList<Food> getListFood(){
		return FoodList;
	}
	
	public Food getFood(int id) {
		for(Food food: FoodList)
			if(food.getId() == id)
				return food;
		return null;
	}
	
	public ArrayList<Food> getListFood(int foodCategoryId){
		ArrayList<Food> list = new ArrayList<Food>();
		for(Food food: FoodList)
			if(food.getCategory() == foodCategoryId)
				list.add(food);
		return list;
	}
	
	public int getSize() {
		return FoodList.size();
	}
}
