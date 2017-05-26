package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;

public class RemoveProductCommandHandler {

	public void removeProductCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		ProductDataGateway gateway = new ProductDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.delete(( (int) command.getObject())));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
