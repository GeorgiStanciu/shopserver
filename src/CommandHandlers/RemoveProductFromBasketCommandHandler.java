package CommandHandlers;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

import CommandResponses.CommandResponse;
import Commands.Command;
import DataGateway.ProductBasketDataGateway;
import Models.FavoriteProduct;
import Models.ProductBasket;

public class RemoveProductFromBasketCommandHandler {

	
	public void removeProductFromBasketCommandHandler(ObjectOutputStream os, Command command) throws IOException {

		ProductBasketDataGateway gateway = new ProductBasketDataGateway();
		ProductBasket productBasket = (ProductBasket) command.getObject();
		ProductBasket product = gateway.findAllByShoppingBasketAndProduct(productBasket.getBasketId(), productBasket.getProductId());
		CommandResponse response = new CommandResponse();
		response.setResponse(gateway.delete(product.getId()));
		String gson = new Gson().toJson(response);
        os.writeObject(gson);
        gateway.close();
    }
}
