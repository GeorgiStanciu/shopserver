package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductBasketDataGateway;
import Models.ProductBasket;

public class AddProductToBasketCommandHandler {

	
	public void addProductToBasketCommandHandler(ObjectOutputStream os, Command command) throws IOException{
		
		ProductBasketDataGateway gateway = new ProductBasketDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.add((ProductBasket) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
	}
}
