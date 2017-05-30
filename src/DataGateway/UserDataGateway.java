package DataGateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.UserModel;

public class UserDataGateway {


	private Connection conn;
	private final String table = "user";
	public UserDataGateway(Connection conn){
		this.conn = conn;
	}
	
	public int add(UserModel user) {
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (name, email, address, birth_date, picture, phone,"
		 		+ "card_number, sex, user_firebase, function) VALUES (?,?,?,?,?,?,?,?,?, ?)";
		 int id = -1;
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setString(1, user.getName());
			 preparedStmt.setString(2, user.getEmail());
		     preparedStmt.setString(3, user.getAddress());
		     if(user.getBirthDate() != null)
		    	 preparedStmt.setDate(4, new Date(user.getBirthDate().getTime()));
		     else
		    	 preparedStmt.setDate(4, null);
		     preparedStmt.setString(5, user.getPictureUrl());
		     preparedStmt.setString(6, user.getPhone());
		     preparedStmt.setString(7, user.getCardNumber());
		     preparedStmt.setString(8, user.getSex());
		     preparedStmt.setString(9, user.getFirebaseId());
		     preparedStmt.setString(10, user.getFunction());
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


	public boolean update(UserModel user) {

		String query = "UPDATE " + table + " set name = ?, email = ?, address = ?, birth_date = ?,"
				+ "picture = ?, phone = ?, card_number = ?, sex = ?, function = ? where id = ?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, user.getName());
			 preparedStmt.setString(2, user.getEmail());
		     preparedStmt.setString(3, user.getAddress());
		     if(user.getBirthDate() != null)
		    	 preparedStmt.setDate(4, new Date(user.getBirthDate().getTime()));
		     else
		    	 preparedStmt.setDate(4, null);
		     preparedStmt.setString(5, user.getPictureUrl());
		     preparedStmt.setString(6, user.getPhone());
		     preparedStmt.setString(7, user.getCardNumber());
		     preparedStmt.setString(8, user.getSex());
		     preparedStmt.setInt(9,  user.getId());
		     preparedStmt.setString(10, user.getFunction());
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
	

	public ArrayList<UserModel> findAll() {
		String query = "SELECT * FROM " + table;
		ArrayList<UserModel> users = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String address = result.getString("address");
				java.util.Date birthDate = result.getDate("birth_date");
				String picture = result.getString("picture");
				String phone = result.getString("phone");
				String cardNumber = result.getString("card_number");
				String sex = result.getString("sex");
				String function = result.getString("function");
     			users.add(new UserModel(id, name,email, address, birthDate, picture, phone, cardNumber, sex, function));
			}
		     result.close();
		     preparedStmt.close();
		     		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return users;
	}
	



	public UserModel findById(int id) {

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		UserModel user = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	        			String name = result.getString("name");
	    				String email = result.getString("email");
	    				String address = result.getString("address");
	    				java.util.Date birthDate = result.getDate("birth_date");
	    				String picture = result.getString("picture");
	    				String phone = result.getString("phone");
	    				String cardNumber = result.getString("card_number");
	    				String sex = result.getString("sex");
	    				String function = result.getString("function");
	         			user = new UserModel(id, name,email, address, birthDate, picture, phone, cardNumber, sex, function);
	     		}
	             result.close();
			     preparedStmt.close();
			     	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return user;
	}
	
	public UserModel findByFirebaseId(String firebaseId) {

		String query = "SELECT * FROM " + table + " WHERE user_firebase = ?";
		UserModel user = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setString(1, firebaseId);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 					int id = result.getInt("id");
	        			String name = result.getString("name");
	    				String email = result.getString("email");
	    				String address = result.getString("address");
	    				java.util.Date birthDate = result.getDate("birth_date");
	    				String picture = result.getString("picture");
	    				String phone = result.getString("phone");
	    				String cardNumber = result.getString("card_number");
	    				String sex = result.getString("sex");
	    				String function = result.getString("function");

	         			user = new UserModel(id, name,email, address, birthDate, picture, phone, cardNumber, sex, function);
	     		}
	             result.close();
			     preparedStmt.close();
			     	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return user;
	}
	

	
}
