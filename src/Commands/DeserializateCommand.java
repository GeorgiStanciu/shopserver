package Commands;


import com.google.gson.*;
import com.google.gson.internal.bind.*;

import CommandHandlers.ViewCategoryByParentCommandHandler;
import CommandHandlers.ViewOrderedCommandHandler;
import CommandHandlers.ViewProductsCommandHandler;
import CommandHandlers.ViewUsersCommandHandler;
import Models.CommandEnum;
import Models.*;

public class DeserializateCommand {
	
	private String json; 
	public DeserializateCommand(String json){
		
		this.json = json;
	}

	
	public Command getCommand(){
		
		
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
    	JsonPrimitive jsonCommand = (JsonPrimitive) jsonObject.get("command");
    	SqlDateTypeAdapter adapter = new SqlDateTypeAdapter();
    	Gson gson = new GsonBuilder().registerTypeAdapter(java.sql.Date.class, adapter).setDateFormat("MMM dd, yyyy").create();
   	
    	//Gson gson = new GsonBuilder().registerTypeAdapter(java.sql.Date.class, new JsonDateDeserializer()).create();
    	CommandEnum operationId = gson.fromJson(jsonCommand, CommandEnum.class);
		
		Command command = new Command(operationId);
		
		if(operationId == CommandEnum.AddProductCommand || operationId == CommandEnum.UpdateProductCommand){
			
			JsonObject jsonObj = (JsonObject) jsonObject.get("object");
    		Object obj = gson.fromJson(jsonObj, Product.class);
    		command.setObject(obj);
		}
		
		else if(operationId == CommandEnum.AddFavoriteCommand || operationId == CommandEnum.UpdateFavoriteCommand || operationId == CommandEnum.GetIsFavoriteProductCommand || operationId == CommandEnum.RemoveFavoriteCommand){
					
					JsonObject jsonObj = (JsonObject) jsonObject.get("object");
		    		Object obj = gson.fromJson(jsonObj, FavoriteProduct.class);
		    		command.setObject(obj);
				}
		
		else if(operationId == CommandEnum.AddUserCommand || operationId == CommandEnum.UpdateUserCommand){
					
					JsonObject jsonObj = (JsonObject) jsonObject.get("object");
		    		Object obj = gson.fromJson(jsonObj, UserModel.class);
		    		command.setObject(obj);
				}
		
		else if(operationId == CommandEnum.AddBasketCommand || operationId == CommandEnum.UpdateBasketCommand){
					
					JsonObject jsonObj = (JsonObject) jsonObject.get("object");
		    		Object obj = gson.fromJson(jsonObj, ShoppingBasket.class);
		    		command.setObject(obj);
				}
		
		else if(operationId == CommandEnum.AddOrderedCommand ){
			
			JsonObject jsonObj = (JsonObject) jsonObject.get("object");
			Object obj = gson.fromJson(jsonObj, OrderModel.class);
			command.setObject(obj);
		}
		
		else if(operationId == CommandEnum.AddProductToBasketCommand || operationId == CommandEnum.RemoveProductFromBasketCommand){
			
			JsonObject jsonObj = (JsonObject) jsonObject.get("object");
			Object obj = gson.fromJson(jsonObj, ProductBasket.class);
			command.setObject(obj);
		}
		
		else if(operationId == CommandEnum.AddReviewCommand || operationId == CommandEnum.UpdateReviewCommand){
					
				JsonObject jsonObj = (JsonObject) jsonObject.get("object");
				Object obj = gson.fromJson(jsonObj, ReviewModel.class);
				command.setObject(obj);
		}
		
		else if(operationId == CommandEnum.ViewCategoriesCommandByParent || operationId == CommandEnum.GetProductByCategoryCommand || operationId == CommandEnum.GetUserByFirebaseCommand
				|| operationId == CommandEnum.GetProductsByString){
			
			JsonPrimitive jsonObj = (JsonPrimitive) jsonObject.get("object");
			Object obj = gson.fromJson(jsonObj, String.class);
			command.setObject(obj);
		}
		else if(operationId != CommandEnum.ViewOrderesCommand && operationId != CommandEnum.ViewProductsCommand &&
				operationId != CommandEnum.ViewUsersCommand){
			
			JsonPrimitive jsonObj = (JsonPrimitive) jsonObject.get("object");
			Object obj = gson.fromJson(jsonObj, Integer.class);
			command.setObject(obj);
		}
		

		return command;
	}
}
