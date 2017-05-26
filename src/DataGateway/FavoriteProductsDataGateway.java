package DataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Product;
import Models.UserModel;
import Models.FavoriteProduct;

public class FavoriteProductsDataGateway {

	
	private Connection conn;
	private final String table = "favorite_products";
	public FavoriteProductsDataGateway(Connection conn){
		this.conn = conn;

	}
	
	
	public int add(FavoriteProduct favoriteProduct) {
		
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (user_id, product_id) VALUES (?,?)";
		 int id = -1;
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, favoriteProduct.getUser().getId());
			 preparedStmt.setInt(2, favoriteProduct.getProduct().getId());
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


	public boolean update(FavoriteProduct favoriteProduct) {
		

		String query = "UPDATE " + table + " set basket_id = ?, product_id= ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, favoriteProduct.getUser().getId());
			preparedStmt.setInt(2, favoriteProduct.getProduct().getId());
			preparedStmt.setInt(3, favoriteProduct.getId());
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
		     conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }		
	

	public ArrayList<FavoriteProduct> findAll() {
		

		String query = "SELECT * FROM " + table;
		ArrayList<FavoriteProduct> favoriteProducts = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
     			int userId = result.getInt("user_id");
     			int productId = result.getInt("product_id");
				Product product = new ProductDataGateway(conn).findById(productId);
				
				UserModel user = new UserDataGateway(conn).findById(userId);
     			favoriteProducts.add(new FavoriteProduct(id, user, product));
			}
		     result.close();
		     preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return favoriteProducts;
	}
	
	public ArrayList<FavoriteProduct> findAllByUserId(int userId) {
		

		String query = "SELECT * FROM " + table + " WHERE user_id = ?";
		ArrayList<FavoriteProduct> favoriteProducts = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()){
				int id = result.getInt("id");
     			int productId = result.getInt("product_id");
				Product product = new ProductDataGateway(conn).findById(productId);
				UserModel user = new UserDataGateway(conn).findById(userId);

     			favoriteProducts.add(new FavoriteProduct(id, user, product));
     					}
		     result.close();
		     preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return favoriteProducts;
	}
	

	public boolean findByUserIdAndProduct(int userId, int productId) {
		

		String query = "SELECT * FROM " + table + " WHERE user_id = ? AND product_id = ?";
		boolean isFavorite = false;;
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			preparedStmt.setInt(2, productId);
			ResultSet result = preparedStmt.executeQuery();
			if(result.next()){
			
				isFavorite = true;
     		}
		     result.close();
		     preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isFavorite;
	}
	
	public int findIdByUserAndProduct(int userId, int productId) {
		

		String query = "SELECT * FROM " + table + " WHERE user_id = ? AND product_id = ?";
		int id = 0;;
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, userId);
			preparedStmt.setInt(2, productId);
			ResultSet result = preparedStmt.executeQuery();
			if(result.next()){
			
				id = result.getInt("id");
     		}
		     result.close();
		     preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return id;
	}
	

	public FavoriteProduct findById(int id) {
		

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		FavoriteProduct favoriteProduct = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	     			int userId = result.getInt("user_id");
	     			int productId = result.getInt("product_id");
					Product product = new ProductDataGateway(conn).findById(productId);
					UserModel user = new UserDataGateway(conn).findById(userId);

	     			favoriteProduct = new FavoriteProduct(id, user, product);

	 			}
	             result.close();
			     preparedStmt.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return favoriteProduct;
	}
	
	
}
