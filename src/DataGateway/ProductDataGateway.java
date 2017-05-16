package DataGateway;

import java.sql.*;
import java.util.ArrayList;

import Models.Category;
import Models.Product;
import Models.ProductImages;
import Models.ReviewModel;

public class ProductDataGateway {


	private Connection conn;
	private final String table = "product";
	public ProductDataGateway(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int add(Product product) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (name, description, category, discount, seller,"
		 		+ "guarantee, quantity,  price, rating) VALUES (?,?,?,?,?,?,?,?,?)";
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setString(1, product.getName());
			 preparedStmt.setString(2, product.getDescription());
			 Category category = new CategoryDataGateway().findByName(product.getCategory());
		     preparedStmt.setInt(3, category.getId());
		     preparedStmt.setInt(4, product.getDiscount());
		     preparedStmt.setString(5, product.getSeller());
		     preparedStmt.setInt(6, product.getGuarantee());
		     preparedStmt.setInt(7, product.getQuantity());
		     preparedStmt.setFloat(8, product.getPrice());
		     preparedStmt.setFloat(9, product.getRating());
		     preparedStmt.execute();
		     ResultSet result = preparedStmt.getGeneratedKeys();
		     if(result.next()){
		    	 if(product.getImages() != null && product.getImages().size() >0){
		    		 ProductImagesDataGateway imageGateway = new ProductImagesDataGateway();
		    		 for(int i = 0; i < product.getImages().size(); i++){
		    			 ProductImages image = new ProductImages(result.getInt(1), product.getImages().get(i));
		    			 imageGateway.add(image);
		    		 }
		    	 }
		    	 return result.getInt(1);
		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return -1;
		
	}


	public boolean update(Product product) {

		String query = "UPDATE " + table + " set name = ?, description = ?, category = ?,"
				+ "discount = ?, seller = ?, guarantee = ?, quantity = ?, price = ?, rating = ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			 preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, product.getName());
			 preparedStmt.setString(2, product.getDescription());
			 Category category = new CategoryDataGateway().findByName(product.getCategory());
		     preparedStmt.setInt(3, category.getId());
		     preparedStmt.setInt(4, product.getDiscount());
		     preparedStmt.setString(5, product.getSeller());
		     preparedStmt.setInt(6, product.getGuarantee());
		     preparedStmt.setInt(7, product.getQuantity());
		     preparedStmt.setFloat(8, product.getPrice());
		     preparedStmt.setFloat(9, product.getRating());
		     preparedStmt.setInt(10,  product.getId());
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
	

	public ArrayList<Product> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<Product> products = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				float price = result.getFloat("price");
     			int categoryId = result.getInt("category");
     			int discount = result.getInt("discount");
     			String seller = result.getString("seller");
     			int guarantee = result.getInt("guarantee");
     			int quantity = result.getInt("quantity");
     			float rating = result.getFloat("rating");
     			
     			ReviewDataGateway reviewGateway = new ReviewDataGateway();
     			ArrayList<ReviewModel> reviews = reviewGateway.findAllByProduct(id);
     			
     			ProductImagesDataGateway imageGateway = new ProductImagesDataGateway();
     			if(id == 71)
     				System.out.println("");
     			ArrayList<ProductImages> productImages = imageGateway.findAllByProduct(id);
     			ArrayList<String> images = new ArrayList();
     			for(ProductImages image: productImages){
     				images.add(image.getPicture());
     			}
     			
     			Category category = new CategoryDataGateway().findById(categoryId);
     			products.add(new Product(id, name, description, images, category.getName(), price, discount, seller, guarantee,
     					quantity, reviews, rating));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return products;
	}
	



	public Product findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		Product product = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 				String name = result.getString("name");
	 				String description = result.getString("description");
	 				float price = result.getFloat("price");
	      			int categoryId = result.getInt("category");
	      			int discount = result.getInt("discount");
	      			String seller = result.getString("seller");
	      			int guarantee = result.getInt("guarantee");
	      			int quantity = result.getInt("quantity");
	      			float rating = result.getFloat("rating");
	      			
	      			ReviewDataGateway reviewGateway = new ReviewDataGateway();
	     			ArrayList<ReviewModel> reviews = reviewGateway.findAllByProduct(id);
	     			
	     			ProductImagesDataGateway imageGateway = new ProductImagesDataGateway();
	     			ArrayList<ProductImages> productImages = imageGateway.findAllByProduct(id);
	     			ArrayList<String> images = new ArrayList();
	     			for(ProductImages image: productImages){
	     				images.add(image.getPicture());
	     			}
	     			
	     			Category category = new CategoryDataGateway().findById(categoryId);

	      			product = new Product(id, name, description, images, category.getName(), price, discount, seller, guarantee,
	      					quantity, reviews, rating);
	     		}
	     		result.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return product;
	}

}
