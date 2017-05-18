package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ReviewDataGateway;
import Models.ReviewModel;


public class AddReviewCommandHandler {

	public void addReviewCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ReviewDataGateway gateway = new ReviewDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.add((ReviewModel) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
	
	
}