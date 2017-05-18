package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;


public class ViewProductsCommandHandler {

	public void viewProductsCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ProductDataGateway gateway = new ProductDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findAll());
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
