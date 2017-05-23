package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.UserDataGateway;
import Models.UserModel;
public class UpdateUserCommandHandler {

	public void updateUserCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		UserDataGateway gateway = new UserDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.update((UserModel) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
