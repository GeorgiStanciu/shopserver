package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ReviewDataGateway;
import Models.ReviewModel;

public class UpdateReviewCommandHandler {

	public void updateReviewCommandHandler(ObjectOutputStream os, Command command, Connection conn) throws IOException {

		ReviewDataGateway gateway = new ReviewDataGateway(conn);
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.update((ReviewModel) command.getObject()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
    }
}
