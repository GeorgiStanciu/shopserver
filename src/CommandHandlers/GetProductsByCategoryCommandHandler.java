package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;

public class GetProductsByCategoryCommandHandler {

	
	public void getProductsByCategoryCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		ProductDataGateway gateway = new ProductDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findAllByCategory((String)command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
