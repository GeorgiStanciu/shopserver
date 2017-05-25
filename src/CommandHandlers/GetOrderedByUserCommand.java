package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.OrderedDataGateway;

public class GetOrderedByUserCommand {

	public void getOrderCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		OrderedDataGateway gateway = new OrderedDataGateway();
		CommandResponse response = new CommandResponse();
		int userId = (int) command.getObject();
		response.setResponse(gateway.findByUserId(userId));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
