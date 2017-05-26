package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.FavoriteProductsDataGateway;
import Models.FavoriteProduct;

public class AddFavoriteCommandHandler {

	
	public void addFavoriteCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		FavoriteProductsDataGateway gateway = new FavoriteProductsDataGateway(conn);
		CommandResponse response = new CommandResponse();
		FavoriteProduct favoriteProduct = (FavoriteProduct) command.getObject();
		if(gateway.findByUserIdAndProduct(favoriteProduct.getUser().getId(), favoriteProduct.getProduct().getId()) == false){
			response.setResponse(gateway.add((FavoriteProduct) command.getObject()));
		}
		else{
			response.setResponse(true);
		}
		String gson = new Gson().toJson(response);
		os.writeObject(gson);
    }
}
