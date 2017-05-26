package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.FavoriteProductsDataGateway;
import Models.FavoriteProduct;

public class RemoveFavoriteCommandHandler {

	
	public void removeFavoriteCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		FavoriteProductsDataGateway gateway = new FavoriteProductsDataGateway(conn);
		CommandResponse response = new CommandResponse();
		FavoriteProduct favoriteProduct = (FavoriteProduct) command.getObject();
		int id = gateway.findIdByUserAndProduct(favoriteProduct.getUser().getId(), favoriteProduct.getProduct().getId());
		response.setResponse(gateway.delete(id));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
