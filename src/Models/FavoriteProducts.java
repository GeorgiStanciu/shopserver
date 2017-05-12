package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class FavoriteProducts implements Serializable{

    private int id;
    private UserModel user;
    private ArrayList<Product> products;

    public FavoriteProducts(int id, UserModel user, ArrayList<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public FavoriteProducts(){

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
}
