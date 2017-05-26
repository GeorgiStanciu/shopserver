package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;
import DataGateway.UserDataGateway;

public class ViewUsersCommandHandler {

	
	public void viewUsersCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		UserDataGateway gateway = new UserDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findAll());
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
