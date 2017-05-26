package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.CategoryDataGateway;

public class ViewCategoryByParentCommandHandler {

	
	public void viewCategoriesCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		CategoryDataGateway gateway = new CategoryDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findByParent((String) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
