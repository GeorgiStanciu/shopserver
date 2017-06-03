package DataGateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.OrderModel;
import Models.OrderedProduct;
import Models.Product;
import Models.ProductImages;
import Models.UserModel;

public class OrderedDataGateway {


	private Connection conn;
	private final String table = "ordered";
	public OrderedDataGateway(Connection conn){
		this.conn = conn;

	}
	

	
	public int add(OrderModel order) {
		

	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (user_id, date, cost) VALUES (?,?,?)";
		 int id = -1;
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, order.getUser().getId());
		     if(order.getOrderDate() != null)
		    	 preparedStmt.setDate(2, new Date(order.getOrderDate().getTime()));
		     else
		    	 preparedStmt.setDate(2, null);
			 preparedStmt.setFloat(3, order.getCost());
		     preparedStmt.execute();
		     ResultSet result = preparedStmt.getGeneratedKeys();
		     if(result.next()){
		    	 id = result.getInt(1);
		    	 if(order.getProducts() != null && order.getProducts().size() >0){
		    		 OrderedProductDataGateway orderProductGateway = new OrderedProductDataGateway(conn);
		    		 for(int i = 0; i < order.getProducts().size(); i++){
		    			 float cost = order.getProductNumber().get(i) * (order.getProducts().get(i).getPrice() - order.getProducts().get(i).getPrice() *
		    					 		order.getProducts().get(i).getDiscount() / 100);
		    			 OrderedProduct product = new OrderedProduct(id, order.getProducts().get(i).getId(), 
		    					 order.getProductNumber().get(i), cost);
		    			 orderProductGateway.add(product);
		    		 }
		    	 }
		         result.close();
			     preparedStmt.close();
   				 

		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     return id;
		
	}



	public ArrayList<OrderModel> findAll() {
		

		String query = "SELECT * FROM " + table;
		ArrayList<OrderModel> orders = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				int userId = result.getInt("user_id");
				float cost = result.getFloat("cost");   
				java.util.Date date = result.getDate("date");

				UserModel user = new UserDataGateway(conn).findById(userId);
				ArrayList<OrderedProduct> orderedProducts = new OrderedProductDataGateway(conn).findAllByOrderId(id);
				ArrayList<Product> products = new ArrayList<>();
				ArrayList<Integer> quanities = new ArrayList<>();
				
				for(int i = 0; i < orderedProducts.size(); i++){
					products.add(orderedProducts.get(i).getProduct());
					quanities.add(orderedProducts.get(i).getQuantity());
				}
     			orders.add(new OrderModel(id, user, products, quanities, date, cost));
			}
		     result.close();
		     preparedStmt.close();
		     

		     
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return orders;
	}
	



	public OrderModel findById(int id) {
		

		String query = "SELECT * FROM " + table + " WHERE id = ?";
		OrderModel order = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	 				int userId = result.getInt("user_id");
	 				float cost = result.getFloat("cost");   
	 				java.util.Date date = result.getDate("date");

	 				UserModel user = new UserDataGateway(conn).findById(userId);
	 				ArrayList<OrderedProduct> orderedProducts = new OrderedProductDataGateway(conn).findAllByOrderId(id);
	 				ArrayList<Product> products = new ArrayList<>();
	 				ArrayList<Integer> quanities = new ArrayList<>();
	 				
	 				for(int i = 0; i < orderedProducts.size(); i++){
	 					products.add(orderedProducts.get(i).getProduct());
	 					quanities.add(orderedProducts.get(i).getQuantity());
	 				}
	      			order = new OrderModel(id, user, products, quanities, date, cost);
	 			}
	             result.close();
			     preparedStmt.close();
				

	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return order;
	}
	
	
	
	public ArrayList<OrderModel> findByUserId(int userId) {
		

		String query = "SELECT * FROM " + table + " WHERE user_id = ?";
		ArrayList<OrderModel> orders = new ArrayList();
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, userId);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 while(result.next()){
	 				int id = result.getInt("id");
	 				float cost = result.getFloat("cost");   
	 				java.util.Date date = result.getDate("date");

	 				UserModel user = new UserDataGateway(conn).findById(userId);
	 				ArrayList<OrderedProduct> orderedProducts = new OrderedProductDataGateway(conn).findAllByOrderId(id);
	 				ArrayList<Product> products = new ArrayList<>();
	 				ArrayList<Integer> quanities = new ArrayList<>();
	 				
	 				for(int i = 0; i < orderedProducts.size(); i++){
	 					products.add(orderedProducts.get(i).getProduct());
	 					quanities.add(orderedProducts.get(i).getQuantity());
	 				}
	      			orders.add(new OrderModel(id, user, products, quanities, date, cost));
	 			}
	             result.close();
			     preparedStmt.close();
				

	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return orders;
	}

	
}
