package DataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Category;
public class CategoryDataGateway {

	

	private Connection conn;
	private final String table = "category";
	public CategoryDataGateway(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int add(Category category) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (name, parent, icon) VALUES (?,?,?)";
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setString(1, category.getName());
			 preparedStmt.setString(2, category.getParent());
		     preparedStmt.setString(3, category.getImage());
		     preparedStmt.execute();
		     ResultSet result = preparedStmt.getGeneratedKeys();
		     if(result.next()){
		    	 return result.getInt(1);
		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return -1;
		
	}

	
	public ArrayList<Category> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<Category> categories = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("name");
     			String parent = result.getString("parent");
				String icon = result.getString("icon");
				categories.add(new Category(id, name, parent, icon));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return categories;
	}
	
	public Category findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		Category category= null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	        		 String name = result.getString("name");
	      			String parent = result.getString("parent");
	 				String icon = result.getString("icon");
	 				category = new Category(id, name, parent, icon);
	      				
	     		}
	     		result.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return category;
	}
}
