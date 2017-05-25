package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductBasketDataGateway;
import Models.ProductBasket;

public class AddProductToBasketCommandHandler {

	
	public void addProductToBasketCommandHandler(ObjectOutputStream os, Command command) throws IOException{
		
		ProductBasketDataGateway gateway = new ProductBasketDataGateway();
		CommandResponse response = new CommandResponse();
		ProductBasket productBasket = (ProductBasket) command.getObject();
		ProductBasket existProduct = gateway.findAllByShoppingBasketAndProduct(productBasket.getBasketId(), productBasket.getProductId());
		if(existProduct == null){
			response.setResponse(gateway.add((ProductBasket) command.getObject()));
			
		}
		else{
			
			existProduct.setQuantity(existProduct.getQuantity() + productBasket.getQuantity());
			response.setResponse(gateway.update(existProduct));
		}
		String gson = new Gson().toJson(response);
		os.writeObject(gson);
        gateway.close();
	}
}
