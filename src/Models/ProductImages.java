package Models;

import java.io.Serializable;

public class ProductImages implements Serializable{

	private int id;
	private int productId;
	private String picture;
	
	public ProductImages(int id, int productId, String picture) {
		super();
		this.id = id;
		this.productId = productId;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
