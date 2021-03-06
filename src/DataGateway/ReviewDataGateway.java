package DataGateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Product;
import Models.ReviewModel;
import Models.UserModel;

public class ReviewDataGateway {


	private Connection conn;
	private final String table = "review";
	public ReviewDataGateway(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int add(ReviewModel review) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (title, review, date, qualifying, user_id, product_id) VALUES (?,?,?,?,?,?)";
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setString(1, review.getTitle());
			 preparedStmt.setString(2, review.getReview());
		     preparedStmt.setDate(3, new Date(review.getDate().getTime()));
		     preparedStmt.setInt(4, review.getQualifying());
		     preparedStmt.setInt(5, review.getUser().getId());
		     preparedStmt.setInt(6, review.getProductId());
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


	public boolean update(ReviewModel review) {

		String query = "UPDATE " + table + " set title = ?, review = ?, date = ?, qualifying = ?"
				+ "user_id = ?, rpoduct_id = ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, review.getTitle());
			 preparedStmt.setString(2, review.getReview());
		     preparedStmt.setDate(3, new Date(review.getDate().getTime()));
		     preparedStmt.setInt(4, review.getQualifying());
		     preparedStmt.setInt(5, review.getUser().getId());
		     preparedStmt.setInt(6, review.getProductId());
		     preparedStmt.setInt(7,  review.getId());
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
	

	public ArrayList<ReviewModel> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<ReviewModel> reviews = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String title = result.getString("title");
				String review = result.getString("review");
				Date date = result.getDate("date");
				int qualifying = result.getInt("qualifying");
     			int userId = result.getInt("user_id");
     			int productId = result.getInt("product_id");
   
     			UserDataGateway userGateway = new UserDataGateway();
     			UserModel user = userGateway.findById(userId);
     			reviews.add(new ReviewModel(id, title, review, user, date, qualifying, productId));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return reviews;
	}
	
	public ArrayList<ReviewModel> findAllByProduct(int productId) {
		String query = "SELECT * FROM " + table + " WHERE product_id = ?";
		ArrayList<ReviewModel> reviews = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, productId);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String title = result.getString("title");
				String review = result.getString("review");
				Date date = result.getDate("date");
				int qualifying = result.getInt("qualifying");
     			int userId = result.getInt("user_id");
     		
   
     			UserDataGateway userGateway = new UserDataGateway();
     			UserModel user = userGateway.findById(userId);
     			reviews.add(new ReviewModel(id, title, review, user, date, qualifying, productId));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return reviews;
	}
	



	public ReviewModel findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		ReviewModel review = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	 				String title = result.getString("title");
	 				String reviewText = result.getString("review");
	 				Date date = result.getDate("date");
	 				int qualifying = result.getInt("qualifying");
	      			int userId = result.getInt("user_id");
	      			int productId = result.getInt("product_id");
	    
	      			UserDataGateway userGateway = new UserDataGateway();
	      			UserModel user = userGateway.findById(userId);
	      			review = new ReviewModel(id, title, reviewText, user, date, qualifying, productId);
	 			}
	     		result.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return review;
	}
}
