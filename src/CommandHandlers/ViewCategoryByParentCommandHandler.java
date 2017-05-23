package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.CategoryDataGateway;

public class ViewCategoryByParentCommandHandler {

	
	public void viewCategoriesCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		CategoryDataGateway gateway = new CategoryDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findByParent((String) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
