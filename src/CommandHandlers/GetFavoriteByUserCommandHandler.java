package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.FavoriteProductsDataGateway;

public class GetFavoriteByUserCommandHandler {

	
	public void getFavoriteCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		FavoriteProductsDataGateway gateway = new FavoriteProductsDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findAllByUserId( (int) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
