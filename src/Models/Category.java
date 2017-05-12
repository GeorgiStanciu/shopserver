package Models;

import java.io.Serializable;

public class Category implements Serializable{

    private int id;
    private String name;
    private String parent;
    private int icon;
    private String image;
    public Category(int id, String name, String parent, String image) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    
}
