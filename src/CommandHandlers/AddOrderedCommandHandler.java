package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.OrderedDataGateway;
import Models.OrderModel;

public class AddOrderedCommandHandler {

	
	public void addOrderCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		OrderedDataGateway gateway = new OrderedDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.add((OrderModel) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
