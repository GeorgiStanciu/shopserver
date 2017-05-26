package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.UserDataGateway;

public class GetUserByFirebaseCommandHandler {

	
	public void getUserCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		UserDataGateway gateway = new UserDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findByFirebaseId(( (String) command.getObject())));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
