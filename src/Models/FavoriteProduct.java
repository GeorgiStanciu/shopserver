package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class FavoriteProduct implements Serializable{

    private int id;
    private UserModel user;
    private Product product;

    public FavoriteProduct(int id, UserModel user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }
    
 
    public FavoriteProduct(UserModel user, Product product) {
        this.user = user;
        this.product = product;
    }
    

    public FavoriteProduct(){

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
