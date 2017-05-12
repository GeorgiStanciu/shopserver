package DataGateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.ProductImages;

public class ProductImagesDataGateway {


	private Connection conn;
	private final String table = "product_images";
	public ProductImagesDataGateway(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int add(ProductImages image) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (product_id, picture) VALUES (?,?)";
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, image.getProductId());
			 preparedStmt.setString(2, image.getPicture());
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


	public boolean update(ProductImages image) {

		String query = "UPDATE " + table + " set product_id = ?, pciture = ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, image.getProductId());
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
	

	public ArrayList<ProductImages> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<ProductImages> images = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String picture = result.getString("picture");
     			int productId = result.getInt("product_id");
   
     			
     			images.add(new ProductImages(id, productId, picture));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return images;
	}
	
	public ArrayList<ProductImages> findAllByProduct(int productId) {
		String query = "SELECT * FROM " + table + " WHERE product_id = ?";
		ArrayList<ProductImages> images = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, productId);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String picture = result.getString("picture");
     			images.add(new ProductImages(id, productId, picture));
     					}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return images;
	}
	



	public ProductImages findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		ProductImages image = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	        		String picture = result.getString("picture");
	      			int productId = result.getInt("product_id");
	    
	      			image = new ProductImages(id, productId, picture);

	 			}
	     		result.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return image;
	}
	
}
