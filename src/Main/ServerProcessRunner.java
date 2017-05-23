package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import CommandHandlers.*;
import Commands.Command;
import Commands.DeserializateCommand;
import Models.*;

public class ServerProcessRunner implements Runnable{
	private Thread t;
	private Socket client;
    
    private AddBasketCommandHandler addBasketCommandHandler;
    private AddFavoriteCommandHandler addFavoriteCommandHandler;
    private AddOrderedCommandHandler addOrderedCommandHandler;
    private AddProductCommandHandler addProductCommandHandler;
    private AddReviewCommandHandler addReviewCommandHandler;
    private AddUserCommandHandler addUserCommandHandler;
    private AddProductToBasketCommandHandler addProductToBasketCommandHandler;
    
    private GetBasketByUserCommandHandler getBasketByUserCommandHandler;
    private GetFavoriteByUserCommandHandler getFavoriteByUserCommandHandler;
    private GetOrderedByUserCommand getOrderedByUserCommand;
    private GetProductCommandHandler getProductCommandHandler;
    private GetReviewByUserCommandHandler getReviewByUserCommandHandler;
    private GetUserCommandHandler getUserCommandHandler;
    private GetProductsByCategoryCommandHandler getProductsByCategoryCommandHandler;
    private GetUserByFirebaseCommandHandler getUserByFirebaseCommandHandler;
    
    
    private RemoveFavoriteCommandHandler removeFavoriteCommandHandler;
    private RemoveProductCommandHandler removeProductCommandHandler;
    private RemoveReviewCommandHandler removeReviewCommandHandler;
    private RemoveUserCommandHandler removeUserCommandHandler;
    
    
    private UpdateBasketCommandHandler updateBasketCommandHandler;
    private UpdateFavoriteCommandHandler updateFavoriteCommandHandler;
    private UpdateProductCommandHandler updateProductCommandHandler;
    private UpdateReviewCommandHandler updateReviewCommandHandler;
    private UpdateUserCommandHandler updateUserCommandHandler;
    
    
    private ViewCategoryByParentCommandHandler viewCategoryByParentCommandHandler;
    private ViewOrderedCommandHandler viewOrderedCommandHandler;
    private ViewProductsCommandHandler viewProductsCommandHandler;
    private ViewUsersCommandHandler viewUsersCommandHandler;
   
    
	public ServerProcessRunner(Socket client){
		this.client = client;
		
		addBasketCommandHandler = new AddBasketCommandHandler();
	    addFavoriteCommandHandler = new AddFavoriteCommandHandler();
	    addOrderedCommandHandler = new AddOrderedCommandHandler();
	    addProductCommandHandler = new AddProductCommandHandler();
	    addReviewCommandHandler = new AddReviewCommandHandler();
	    addUserCommandHandler = new AddUserCommandHandler();
	    addProductToBasketCommandHandler = new AddProductToBasketCommandHandler();
	    
	    getBasketByUserCommandHandler = new GetBasketByUserCommandHandler();
	    getFavoriteByUserCommandHandler = new GetFavoriteByUserCommandHandler();
	    getOrderedByUserCommand = new GetOrderedByUserCommand();
	    getProductCommandHandler = new GetProductCommandHandler();
	    getReviewByUserCommandHandler = new GetReviewByUserCommandHandler();
	    getUserCommandHandler = new GetUserCommandHandler();
	    getProductsByCategoryCommandHandler = new GetProductsByCategoryCommandHandler();
	    getUserByFirebaseCommandHandler = new GetUserByFirebaseCommandHandler();
	    
	    
	    removeFavoriteCommandHandler = new RemoveFavoriteCommandHandler();
	    removeProductCommandHandler = new RemoveProductCommandHandler();
	    removeReviewCommandHandler = new RemoveReviewCommandHandler();
	    removeUserCommandHandler = new RemoveUserCommandHandler();
	    
	    
	    updateBasketCommandHandler = new UpdateBasketCommandHandler();
	    updateFavoriteCommandHandler = new UpdateFavoriteCommandHandler();
	    updateProductCommandHandler = new UpdateProductCommandHandler();
	    updateReviewCommandHandler = new UpdateReviewCommandHandler();
	    updateUserCommandHandler = new UpdateUserCommandHandler();
	    
	    
	    viewCategoryByParentCommandHandler = new ViewCategoryByParentCommandHandler();
	    viewOrderedCommandHandler = new ViewOrderedCommandHandler();
	    viewProductsCommandHandler = new ViewProductsCommandHandler();
	    viewUsersCommandHandler = new ViewUsersCommandHandler();
	}
	@Override
	public void run() {
		ObjectOutputStream os  = null;
		ObjectInputStream ois = null;
		BufferedReader in = null;
		try {
            os = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while (in!= null) {
            	if(in.ready()){
            	String json = (String) ois.readObject();
            
            	Command command = new DeserializateCommand(json).getCommand();
                CommandEnum operationId = command.getCommand();
                switch(operationId) {
                	case AddBasketCommand:
                		addBasketCommandHandler.addBasketCommandHandler(os, command);
                		break;
                	case AddFavoriteCommand:
                		addFavoriteCommandHandler.addFavoriteCommandHandler(os, command);
                		break;
                	case AddOrderedCommand:
                		addOrderedCommandHandler.addOrderCommandHandler(os, command);
                		break;
                	case AddProductCommand:
                		addProductCommandHandler.addProductCommandHandler(os, command);
                		break;
                	case AddReviewCommand:
                		addReviewCommandHandler.addReviewCommandHandler(os, command);
                		break;
                	case AddUserCommand:
                		addUserCommandHandler.addUserCommandHandler(os, command);
                		break;
                	case AddProductToBasketCommand:
                		addProductToBasketCommandHandler.addProductToBasketCommandHandler(os, command);
                		break;
                		
                	case GetBasketByUserCommand:
                		getBasketByUserCommandHandler.getBasketCommandHandler(os, command);
                		break;
                	case GetFavoritesByUserCommand:
                		getFavoriteByUserCommandHandler.getFavoriteCommandHandler(os, command);
                		break;
                	case GetOrderesByUserCommand:
                		getOrderedByUserCommand.getOrderCommandHandler(os, command);
                		break;
                	case GetProductCommand:
                		getProductCommandHandler.getProductCommandHandler(os, command);
                		break;
                	case GetReviewsByProductCommand:
                		getReviewByUserCommandHandler.getReviewCommandHandler(os, command);
                		break;
                	case GetUserCommand:
                		getUserCommandHandler.getUserCommandHandler(os, command);
                		break;
                	case GetProductByCategoryCommand:
                		getProductsByCategoryCommandHandler.getProductsByCategoryCommandHandler(os, command);
                		break;
                	case GetUserByFirebaseCommand:
                		getUserByFirebaseCommandHandler.getUserCommandHandler(os, command);
                		break;
                		
                	case RemoveFavoriteCommand:
                		removeFavoriteCommandHandler.removeFavoriteCommandHandler(os, command);
                		break;
                	case RemoveProductCommand:
                		removeProductCommandHandler.removeProductCommandHandler(os, command);
                		break;
                	case RemoveReviewCommand:
                		removeReviewCommandHandler.removeReviewCommandHandler(os, command);
                		break;
                	case RemoveUserCommand:
                		removeUserCommandHandler.removeUserCommandHandler(os, command);
                		break;
                	
                	case UpdateBasketCommand:
                		updateBasketCommandHandler.updateBasketCommandHandler(os, command);
                		break;
                	case UpdateFavoriteCommand:
                		updateFavoriteCommandHandler.updateFavoriteCommandHandler(os, command);
                		break;
                	case UpdateProductCommand:
                		updateProductCommandHandler.updateProductCommandHandler(os, command);
                		break;
                	case UpdateReviewCommand:
                		updateReviewCommandHandler.updateReviewCommandHandler(os, command);
                		break;
                	case UpdateUserCommand:
                		updateUserCommandHandler.updateUserCommandHandler(os, command);
                		break;
                	
                	case ViewCategoriesCommandByParent:
                		viewCategoryByParentCommandHandler.viewCategoriesCommandHandler(os, command);
                		break;
                	case ViewOrderesCommand:
                		viewOrderedCommandHandler.viewOrdersCommandHandler(os, command);
                		break;
                	case ViewProductsCommand:
                		viewProductsCommandHandler.viewProductsCommandHandler(os, command);
                		break;
                	case ViewUsersCommand:
                		viewUsersCommandHandler.viewUsersCommandHandler(os, command);
                		break;
                	default:
                }
               
                if(os != null)
					 os.close();
				 if(ois != null)
					 ois.close();
	             if(in != null)
	            	 in.close();
	             in = null;
	             client.close();
            }
            	
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
			
		
		
		
	}
	

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

}
