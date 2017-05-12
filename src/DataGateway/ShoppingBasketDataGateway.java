package DataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.ShoppingBasket;

public class ShoppingBasketDataGateway {

/*	private Connection conn;
	private final String table = "shopping_basket";
	public ShoppingBasketDataGateway(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int add(ShoppingBasket basket) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (user_id, product_id, product_quantity) VALUES (?,?,?)";
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, basket.getUser().getId());
			 preparedStmt.setString(2, basket.getProduct().getId());
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


	public boolean update(ShoppingBasketImages image) {

		String query = "UPDATE " + table + " set ShoppingBasket_id = ?, pciture = ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, image.getShoppingBasketId());
			 preparedStmt.setString(2, image.getPicture());
			 preparedStmt.setInt(3, image.getId());
		     preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int id) {
		String query = "DELETE FROM "+ table + " WHERE id = ?";
		 
        try  {
        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
        	 preparedStmt.setInt(1, id);
        	 preparedStmt.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }		
	

	public ArrayList<ShoppingBasketImages> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<ShoppingBasketImages> images = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String picture = result.getString("picture");
     			int ShoppingBasketId = result.getInt("ShoppingBasket_id");
   
     			
     			images.add(new ShoppingBasketImages(id, ShoppingBasketId, picture));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return images;
	}
	
	public ArrayList<ShoppingBasketImages> findAllByShoppingBasket(int ShoppingBasketId) {
		String query = "SELECT * FROM " + table + " WHERE ShoppingBasket_id = ?";
		ArrayList<ShoppingBasketImages> images = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, ShoppingBasketId);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String picture = result.getString("picture");
     			images.add(new ShoppingBasketImages(id, ShoppingBasketId, picture));
     					}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return images;
	}
	



	public ShoppingBasketImages findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		ShoppingBasketImages image = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	        		String picture = result.getString("picture");
	      			int ShoppingBasketId = result.getInt("ShoppingBasket_id");
	    
	      			image = new ShoppingBasketImages(id, ShoppingBasketId, picture);

	 			}
	     		result.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return image;
	}*/
}
