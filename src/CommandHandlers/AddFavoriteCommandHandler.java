package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.FavoriteProductsDataGateway;
import Models.FavoriteProduct;

public class AddFavoriteCommandHandler {

	
	public void addFavoriteCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		FavoriteProductsDataGateway gateway = new FavoriteProductsDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.add((FavoriteProduct) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
