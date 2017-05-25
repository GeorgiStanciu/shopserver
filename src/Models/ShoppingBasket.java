package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingBasket implements Serializable{

    private int id;
    private UserModel user;
    private ArrayList<Product> products;
    private ArrayList<Integer> productsNumber;

    public ShoppingBasket(int id, UserModel user, ArrayList<Product> products, ArrayList<Integer> productsNumber) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.productsNumber = productsNumber;
    }

    public ShoppingBasket(UserModel user, ArrayList<Product> products, ArrayList<Integer> productsNumber) {
        this.user = user;
        this.products = products;
        this.productsNumber = productsNumber;
    }
    
    public ShoppingBasket(UserModel user) {
        this.user = user;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(ArrayList<Integer> productsNumber) {
        this.productsNumber = productsNumber;
    }
    
	public float calcSum(){
        float sum = 0.0f;
        for(int i = 0; i < products.size(); i++){
            float newSum = products.get(i).getPrice() - products.get(i).getPrice() * products.get(i).getDiscount() / 100;
            sum += newSum * productsNumber.get(i);
        }
        return sum;
    }
}
