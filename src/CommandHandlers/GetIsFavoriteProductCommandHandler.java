package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.FavoriteProductsDataGateway;
import Models.FavoriteProduct;

public class GetIsFavoriteProductCommandHandler {

	
	public void getIsFavoriteProductCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		FavoriteProductsDataGateway gateway = new FavoriteProductsDataGateway(conn);
		CommandResponse response = new CommandResponse();
		FavoriteProduct favorite = (FavoriteProduct) command.getObject();
		response.setResponse(gateway.findByUserIdAndProduct(favorite.getUser().getId(), favorite.getProduct().getId()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
