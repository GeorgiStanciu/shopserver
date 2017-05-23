package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.UserDataGateway;
import Models.UserModel;

public class AddUserCommandHandler {

	
	public void addUserCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		UserDataGateway gateway = new UserDataGateway();
		CommandResponse response = new CommandResponse();
		UserModel user = (UserModel) command.getObject();
		UserModel isUser = gateway.findByFirebaseId(user.getFirebaseId());
		if(isUser == null){
			response.setResponse(gateway.add(user));
			String gson = new Gson().toJson(response);
	        os.writeObject(gson);
		}
		else{
		response.setResponse(isUser.getId());
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
		}
		gateway.close();
    }
}
