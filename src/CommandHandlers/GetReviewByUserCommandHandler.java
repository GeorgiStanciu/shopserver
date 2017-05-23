package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ReviewDataGateway;

public class GetReviewByUserCommandHandler {

	public void getReviewCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ReviewDataGateway gateway= new ReviewDataGateway();
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.findById(( (int) command.getObject())));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
