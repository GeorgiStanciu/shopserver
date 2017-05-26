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
	public CategoryDataGateway(Connection conn){
		this.conn = conn;


	}
	
	public int add(Category category) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (name, parent, icon) VALUES (?,?,?)";
		 int id = -1;
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setString(1, category.getName());
			 preparedStmt.setString(2, category.getParent());
		     preparedStmt.setString(3, category.getImage());
		     preparedStmt.execute();
		     ResultSet result = preparedStmt.getGeneratedKeys();
		     if(result.next()){
		    	 id =  result.getInt(1);
		     }
		     result.close();
		     preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return id;
		
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
		     preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return categories;
	}
	
	public ArrayList<Category> findByParent(String parent) {
		String query = "SELECT * FROM " + table + " WHERE parent = ?";
		ArrayList<Category> categories = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, parent);
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("name");
				String icon = result.getString("icon");
				categories.add(new Category(id, name, parent, icon));
			}
		     result.close();
		     preparedStmt.close();
		     
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
			     preparedStmt.close();
			     
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return category;
	}
	
	
	public Category findByName(String name) {

		String query = "SELECT * FROM " + table + " WHERE name = ?";
		Category category= null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setString(1, name);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	        		int id = result.getInt("id");
	      			String parent = result.getString("parent");
	 				String icon = result.getString("icon");
	 				category = new Category(id, name, parent, icon);
	      				
	     		}
	             result.close();
			     preparedStmt.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return category;
	}
	
}
