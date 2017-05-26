package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ShoppingBasketDataGateway;
import DataGateway.UserDataGateway;
import Models.ShoppingBasket;
import Models.UserModel;

public class AddUserCommandHandler {

	
	public void addUserCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		UserDataGateway gateway = new UserDataGateway(conn);
		CommandResponse response = new CommandResponse();
		UserModel user = (UserModel) command.getObject();
		UserModel isUser = gateway.findByFirebaseId(user.getFirebaseId());
		if(isUser == null){
			response.setResponse(gateway.add(user));
			String gson = new Gson().toJson(response);
	        os.writeObject(gson);
	        
	        ShoppingBasketDataGateway basketGateway = new ShoppingBasketDataGateway(conn);
	        ShoppingBasket basket = new ShoppingBasket(isUser);
	        basketGateway.add(basket);
		}
		else{
		response.setResponse(isUser.getId());
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
		}
    }
}
