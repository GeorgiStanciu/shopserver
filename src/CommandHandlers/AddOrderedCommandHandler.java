package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.OrderedDataGateway;
import Models.OrderModel;

public class AddOrderedCommandHandler {

	
	public void addOrderCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		OrderedDataGateway gateway = new OrderedDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.add((OrderModel) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
