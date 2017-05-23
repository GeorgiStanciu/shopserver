package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.OrderedDataGateway;

public class ViewOrderedCommandHandler {

	
	public void viewOrdersCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		OrderedDataGateway gateway = new OrderedDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findAll());
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
