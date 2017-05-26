package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ShoppingBasketDataGateway;

public class GetBasketByUserCommandHandler {

	public void getBasketCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		ShoppingBasketDataGateway gateway = new ShoppingBasketDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findByUserId((int) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
