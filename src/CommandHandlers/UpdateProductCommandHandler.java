package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;
import Models.Product;

public class UpdateProductCommandHandler {

	public void updateProductCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		ProductDataGateway gateway = new ProductDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.update((Product) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
