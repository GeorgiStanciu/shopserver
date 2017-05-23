package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.FavoriteProductsDataGateway;

public class RemoveFavoriteCommandHandler {

	
	public void removeFavoriteCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		FavoriteProductsDataGateway gateway = new FavoriteProductsDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.delete(( (int) command.getObject())));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
