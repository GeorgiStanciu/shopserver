package DataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Product;
import Models.ProductBasket;
import Models.ShoppingBasket;
import Models.UserModel;

public class ShoppingBasketDataGateway {

	private Connection conn;
	private final String table = "shopping_basket";
	public ShoppingBasketDataGateway(Connection conn){
		this.conn = conn;
	}
	
	public int add(ShoppingBasket basket) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (user_id) VALUES (?)";
		 int id = -1;
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, basket.getUser().getId());
		     preparedStmt.execute();
		     ResultSet result = preparedStmt.getGeneratedKeys();
		     if(result.next()){
		    	 id = result.getInt(1);
		     }
		     result.close();
		     preparedStmt.close();
		     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return id;
		
	}


	public boolean update(ShoppingBasket basket) {

		String query = "UPDATE " + table + " set user_id ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setInt(1, basket.getUser().getId());
			 preparedStmt.setInt(2, basket.getId());
		     preparedStmt.executeUpdate();
		     preparedStmt.close();
		     
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
		     preparedStmt.close();
		     
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }		
	

	public ArrayList<ShoppingBasket> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<ShoppingBasket> baskets = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				int userId = result.getInt("userId");
     			
				UserModel user = new UserDataGateway(conn).findById(userId);
				ArrayList<Product> products = new ArrayList<>();
				ArrayList<Integer> quantities = new ArrayList<>();
     			
				ArrayList<ProductBasket> productBaskets = new ProductBasketDataGateway(conn).findAllByShoppingBasket(id);
				for(int i = 0; i < productBaskets.size(); i++){
					products.add(productBaskets.get(i).getProduct());
					quantities.add(productBaskets.get(i).getQuantity());
				}
				
     			baskets.add(new ShoppingBasket(id, user, products, quantities));
			}
		     result.close();
		     preparedStmt.close();
		     		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return baskets;
	}




	public ShoppingBasket findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		ShoppingBasket basket = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 				int userId = result.getInt("user_id");
	      			
	 				UserModel user = new UserDataGateway(conn).findById(userId);
	 				ArrayList<Product> products = new ArrayList<>();
	 				ArrayList<Integer> quantities = new ArrayList<>();
	      			
	 				ArrayList<ProductBasket> productBaskets = new ProductBasketDataGateway(conn).findAllByShoppingBasket(id);
	 				for(int i = 0; i < productBaskets.size(); i++){
	 					products.add(productBaskets.get(i).getProduct());
	 					quantities.add(productBaskets.get(i).getQuantity());
	 				}
	 				
	      			basket= new ShoppingBasket(id, user, products, quantities);

	 			}
	             result.close();
			     preparedStmt.close();
			     	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return basket;
	}
	
	
	
	public ShoppingBasket findByUserId(int userId) {

		String query = "SELECT * FROM " + table + " WHERE user_id = ?";
		ShoppingBasket basket = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, userId);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 				int id = result.getInt("id");
	 				UserModel user = new UserDataGateway(conn).findById(userId);
	 				ArrayList<Product> products = new ArrayList<>();
	 				ArrayList<Integer> quantities = new ArrayList<>();
	      			
	 				ArrayList<ProductBasket> productBaskets = new ProductBasketDataGateway(conn).findAllByShoppingBasket(id);
	 				for(int i = 0; i < productBaskets.size(); i++){
	 					products.add(productBaskets.get(i).getProduct());
	 					quantities.add(productBaskets.get(i).getQuantity());
	 				}
	 				
	      			basket= new ShoppingBasket(id, user, products, quantities);

	 			}
	             result.close();
			     preparedStmt.close();
			     	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return basket;
	}

	
}
