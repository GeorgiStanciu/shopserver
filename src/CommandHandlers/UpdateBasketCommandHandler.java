package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;
import DataGateway.ShoppingBasketDataGateway;
import Models.Product;
import Models.ShoppingBasket;

public class UpdateBasketCommandHandler {

	public void updateBasketCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ShoppingBasketDataGateway gateway = new ShoppingBasketDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.update((ShoppingBasket) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
