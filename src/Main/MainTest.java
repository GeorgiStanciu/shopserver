package Main;

import java.util.ArrayList;
import java.util.Date;

import DataGateway.*;
import Models.*;

public class MainTest {

	public static void main(String[] args){
		/*UserModel user = new UserModel("Daniela", "daniela@a.com", "bucium", null, "", "", "", "Feminin");
		UserDataGateway userGateway = new UserDataGateway();
		
		//userGateway.add(user);
		//userGateway.update(user);
		
		UserModel u = userGateway.findById(1);
		System.out.println(u.getId() + " " + u.getName() + " " + u.getAddress() + " " + u.getBirthDate().toString() + " " +
							u.getPictureUrl() + " " + u.getPhone() + " " + u.getCardNumber() + u.getSex());
		
		ArrayList<UserModel> users = userGateway.findAll();
		for(UserModel us: users){
			System.out.println(us.getId() + " " + us.getName() + " " + us.getAddress() + " " + us.getBirthDate() + " " +
					us.getPictureUrl() + " " + us.getPhone() + " " + us.getCardNumber() + us.getSex());
		}*/
		
		
		/*CategoryDataGateway categoryGateway = new CategoryDataGateway();
		
		//userGateway.add(user);
		//userGateway.update(user);
		
		Category c = categoryGateway.findByName("Console");
		System.out.println(c.getId() + " " + c.getName() + " " + c.getParent() + " " + c.getIcon());
		
		ArrayList<Category> categoies = categoryGateway.findAll();
		for(Category ct: categoies){
			//System.out.println(ct.getId() + " " + ct.getName() + " " + ct.getParent() + " " + ct.getIcon());

		}
*/
//		UserDataGateway userGateway = new UserDataGateway();
//		UserModel user = userGateway.findById(2);
//		ProductDataGateway productGateway = new ProductDataGateway();
		
		
//		FavoriteProductsDataGateway favoriteGateway = new FavoriteProductsDataGateway();
//		ArrayList<FavoriteProduct> favorites = favoriteGateway.findAllByUserId(1);
//		for(FavoriteProduct favorite: favorites)
//			System.out.println(favorite.getId() + " " + favorite.getUser().getName() + " " + favorite.getProduct().getName());
	
//		ShoppingBasketDataGateway basketGateway = new ShoppingBasketDataGateway();
//		//ShoppingBasket basket = new ShoppingBasket(user, null, null);
//		//basketGateway.add(basket);
//		
//		ProductBasketDataGateway productBasketGetway = new ProductBasketDataGateway();
//		
//		ProductBasket productBasket = new ProductBasket(1, 26, 1);
//		//productBasketGetway.add(productBasket);
//		
//		ShoppingBasket basket = basketGateway.findById(1);
//		for(int i = 0; i < basket.getProducts().size(); i++){
//			System.out.println(basket.getProducts().get(i).getId() + " " + basket.getProductsNumber().get(i));
//		}
		
//		OrderedDataGateway orderedGateway = new OrderedDataGateway();
//		OrderModel order = new OrderModel(user, null, null, new Date(), 0);
//		//orderedGateway.add(order);
//		
//		Product product = productGateway.findById(72);
//
//		OrderedProductDataGateway ordedProducGateway =  new OrderedProductDataGateway();
//		float price = product.getPrice() - product.getPrice() * product.getDiscount() / 100;
//		OrderedProduct orderedProduct = new OrderedProduct(1, product.getId(), 1, price);
//		//ordedProducGateway.add(orderedProduct);
//		
//		
//		order = orderedGateway.findById(1);
//		for(int i = 0; i < order.getProducts().size(); i++){
//			System.out.println(order.getProducts().get(i).getId() + " " + 
//								order.getProducts().get(i).getPrice() + " " + order.getProductNumber().get(i) + " " +
//								order.getCost());
//		}
		
		
		//ProductDataGateway productGateway = new ProductDataGateway();
		ArrayList<Product> products = new ArrayList();
		ArrayList<String> images;

	}
}
