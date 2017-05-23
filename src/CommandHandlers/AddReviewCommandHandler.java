package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductDataGateway;
import DataGateway.ReviewDataGateway;
import Models.Product;
import Models.ReviewModel;


public class AddReviewCommandHandler {

	public void addReviewCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ReviewDataGateway gateway = new ReviewDataGateway();
		CommandResponse response = new CommandResponse();
		ReviewModel review = (ReviewModel) command.getObject();
		response.setResponse(gateway.add(review));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        if((int) response.getResponse() != -1){
        	int count = gateway.getReviewsCountByProduct(review.getProductId());
        	ProductDataGateway productDataGateway = new ProductDataGateway();
        	Product product = productDataGateway.findById(review.getProductId());
        	float rating = ((product.getRating() * (count - 1) + review.getQualifying())) / count;
        	productDataGateway.updateRating(product.getId(), rating);
        	productDataGateway.close();
        }
        gateway.close();
    }
	
	
}
