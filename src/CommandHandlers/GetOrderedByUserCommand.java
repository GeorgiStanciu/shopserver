package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.OrderedDataGateway;

public class GetOrderedByUserCommand {

	public void getOrderCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		OrderedDataGateway gateway = new OrderedDataGateway(conn);
		CommandResponse response = new CommandResponse();
		int userId = (int) command.getObject();
		response.setResponse(gateway.findByUserId(userId));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
