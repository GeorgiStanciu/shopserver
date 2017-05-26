package DataGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.OrderedProduct;
import Models.Product;

public class OrderedProductDataGateway {
	
	
	private Connection conn;
	private final String table = "ordered_products";
	public OrderedProductDataGateway(Connection conn){
		this.conn = conn;

	}
	

	public int add(OrderedProduct orderedProduct) {
		
	    String[] returnId = {"id" };
		 String query = "INSERT INTO " + table + " (order_id, product_id, product_quantity, cost) VALUES (?,?,?,?)";
		 int id = -1;
	     try {
		     PreparedStatement preparedStmt = conn.prepareStatement(query, returnId);
		     preparedStmt.setInt(1, orderedProduct.getOrderId());
			 preparedStmt.setInt(2, orderedProduct.getProductId());
			 preparedStmt.setInt(3, orderedProduct.getQuantity());
			 preparedStmt.setFloat(4, orderedProduct.getCost());
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



	public ArrayList<OrderedProduct> findAll() {
		

		String query = "SELECT * FROM " + table;
		ArrayList<OrderedProduct> orderedProducts = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result = preparedStmt.executeQuery(query);
			while(result.next()){
				int id = result.getInt("id");
				int productId = result.getInt("product_id");
				int orderedId = result.getInt("order_id");
				int quantity = result.getInt("product_quantity");
				float cost = result.getFloat("cost");   
     			
				Product product = new ProductDataGateway(conn).findById(productId);
				
     			orderedProducts.add(new OrderedProduct(id, orderedId, productId, quantity, cost, product));
			}
		     result.close();
		     preparedStmt.close();
		     
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return orderedProducts;
	}
	
	public ArrayList<OrderedProduct> findAllByOrderId(int orderId) {
		

		String query = "SELECT * FROM " + table + " WHERE order_id = ?";
		ArrayList<OrderedProduct> orderedProducts = new ArrayList();
		try{
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, orderId);
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()){
				int id = result.getInt("id");
				int productId = result.getInt("product_id");
				int quantity = result.getInt("product_quantity");
				float cost = result.getFloat("cost");   
     			
				Product product = new ProductDataGateway(conn).findById(productId);
     			orderedProducts.add(new OrderedProduct(id, orderId, productId, quantity, cost, product));
     			}
		     result.close();
		     preparedStmt.close();
		     
		     
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return orderedProducts;
	}
	



	public OrderedProduct findById(int id) {

		
		String query = "SELECT * FROM " + table + " WHERE id = ?";
		OrderedProduct orderedProduct = null;
		   try  {
	        	 PreparedStatement preparedStmt = conn.prepareStatement(query);
	        	 preparedStmt.setInt(1, id);
	        	 ResultSet result = preparedStmt.executeQuery();
	        	 if(result.next()){
	 			
	 				int productId = result.getInt("product_id");
	 				int orderedId = result.getInt("order_id");
	 				int quantity = result.getInt("product_quantity");
	 				float cost = result.getFloat("cost");   
	      			
	 				Product product = new ProductDataGateway(conn).findById(productId);
	 				
	      			orderedProduct = new OrderedProduct(id, orderedId, productId, quantity, cost, product);

	 			}
	             result.close();
			     preparedStmt.close();
			     
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return orderedProduct;
	}
	
	
	
}
