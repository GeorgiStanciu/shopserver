package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ShoppingBasketDataGateway;
import Models.ShoppingBasket;



public class AddBasketCommandHandler {
	
	public void addBasketCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ShoppingBasketDataGateway gateway = new ShoppingBasketDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.add((ShoppingBasket) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
