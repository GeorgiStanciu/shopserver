package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;

public class GetProductCommandHandler {

	public void getProductCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ProductDataGateway gateway = new ProductDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findById(( (int) command.getObject())));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
