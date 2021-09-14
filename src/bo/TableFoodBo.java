package bo;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.TableFood;
import dao.TableFoodDao;

public class TableFoodBo {
	private TableFoodDao tableDao;
	private ArrayList<TableFood> TableFoodList;
	
	public TableFoodBo() throws SQLException {
		tableDao = new TableFoodDao();
		TableFoodList = tableDao.getListTableFood();
	}
	
	public ArrayList<TableFood> getListTableFood(){
		return TableFoodList;
	}
	
	public TableFood getTableFood(int id) {
		for(TableFood tf: TableFoodList) 
			if(tf.getId() == id) {
				return tf;
			}
		return null;
	}
	
	public void setStatus(int id, boolean status) {
		TableFood tf = getTableFood(id);
		if(tf != null) {
			tf.setStatus(status);
			tableDao.updateStatus(id, status);
		}
	}
	
	public int getSize() {
		return TableFoodList.size();
	}
}