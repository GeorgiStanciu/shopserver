package DataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Product;
import Models.ProductBasket;

public class ProductBasketDataGateway {

	
	private Connection conn;
	private final String table = "basket_product";
	public ProductBasketDataGateway(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int add(ProductBasket productBasket) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (basket_id, product_id, quantity) VALUES (?,?,?)";
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, productBasket.getBasketId());
			 preparedStmt.setInt(2, productBasket.getProductId());
			 preparedStmt.setInt(3, productBasket.getQuantity());
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


	public boolean update(ProductBasket productBasket) {

		String query = "UPDATE " + table + " set basket_id = ?, product_id= ?, quantity where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, productBasket.getBasketId());
			preparedStmt.setInt(2, productBasket.getProductId());
			 preparedStmt.setInt(3, productBasket.getQuantity());
			preparedStmt.setInt(4, productBasket.getId());
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
	

	public ArrayList<ProductBasket> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<ProductBasket> productBaskets = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
     			int basketId = result.getInt("basket_id");
     			int productId = result.getInt("product_id");
     			int quantity = result.getInt("quanity");
				Product product = new ProductDataGateway().findById(productId);

     			productBaskets.add(new ProductBasket(id, basketId, productId, quantity, product));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return productBaskets;
	}
	
	public ArrayList<ProductBasket> findAllByShoppingBasket(int basketId) {
		String query = "SELECT * FROM " + table + " WHERE basket_id = ?";
		ArrayList<ProductBasket> productBaskets = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, basketId);
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()){
				int id = result.getInt("id");
     			int productId = result.getInt("product_id");
     			int quantity = result.getInt("quantity");
				Product product = new ProductDataGateway().findById(productId);

     			productBaskets.add(new ProductBasket(id, basketId, productId, quantity, product));
     					}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return productBaskets;
	}
	



	public ProductBasket findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		ProductBasket productBasket = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	        		int basketId = result.getInt("basket_id");
	      			int productId = result.getInt("product_id");
	     			int quantity = result.getInt("quanity");
	 				Product product = new ProductDataGateway().findById(productId);

	      			productBasket = new ProductBasket(id, basketId, productId, quantity, product);

	 			}
	     		result.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return productBasket;
	}
}
