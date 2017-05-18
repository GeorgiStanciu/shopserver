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
import Models.*;

public class ServerProcessRunner implements Runnable{
	private Thread t;
	private ServerSocket server;
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
   
    
	public ServerProcessRunner(ServerSocket server,Socket client){
		this.client = client;
		this.server = server;
		
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
		try {
            os = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(
            		client.getInputStream()));

            System.out.println("Server started");
            while (in!= null) {
            	if(in.ready()){
            	String json = (String) ois.readObject();
            	JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
            	JsonPrimitive jsonCommand = (JsonPrimitive) jsonObject.get("command");
            	Gson gson = new GsonBuilder().create();
            	CommandEnum operationId = gson.fromJson(jsonCommand, CommandEnum.class);
            	
            	
                //Command command =  new Gson().fromJson(json, Command.class);
            	Command command = new Command(operationId);
               // CommandEnum operationId = command.getCommand();
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
                    	JsonObject jsonObj = (JsonObject) jsonObject.get("object");
                		Object obj = gson.fromJson(jsonObj, Product.class);
                		command.setObject(obj);
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
                	case GetFavoriteByUserCommand:
                		getFavoriteByUserCommandHandler.getFavoriteCommandHandler(os, command);
                		break;
                	case GetOrderedByUserCommand:
                		getOrderedByUserCommand.getOrderCommandHandler(os, command);
                		break;
                	case GetProductCommand:
                		getProductCommandHandler.getProductCommandHandler(os, command);
                		break;
                	case GetReviewByProductCommand:
                		getReviewByUserCommandHandler.getReviewCommandHandler(os, command);
                		break;
                	case GetUserCommand:
                		getUserCommandHandler.getUserCommandHandler(os, command);
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
                		//updateFavoriteCommandHandler;
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
                	
                	case ViewCategoryCommandByParent:
                		viewCategoryByParentCommandHandler.viewCategoriesCommandHandler(os, command);
                		break;
                	case ViewOrderedCommand:
                		viewOrderedCommandHandler.viewOrdersCommandHandler(os, command);
                		break;
                	case ViewProductCommand:
                		viewProductsCommandHandler.viewProductsCommandHandler(os, command);
                		break;
                	case ViewUserCommand:
                		viewUsersCommandHandler.viewUsersCommandHandler(os, command);
                		break;
                	default:
                }
                
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
