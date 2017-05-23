package Models;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {

    private int id;
    private String firebaseId;
    private String name;
    private String email;
    private String address;
    private Date birthDate;
    private String pictureUrl;
    private String phone;
    private String cardNumber;
    private String sex;

    public UserModel(){
        this.name = "";
        this.email = "";
        this.id = 0;
    }
    public UserModel(String email, String firebaseId, String name){
        this.email = email;
        this.firebaseId = firebaseId;
        this.name = name;
        this.address = "";
		this.birthDate = null;
		this.pictureUrl = "";
		this.phone = "";
		this.cardNumber = "";
		this.sex = "Masculine";
    }

    public UserModel( int id, String email, String name, String address, Date birthDate){
        this.email = email;
        this.id  = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.pictureUrl = "";
    }
    public UserModel( int id, String email, String name, String address, Date birthDate, String url){
        this.email = email;
        this.id  = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.pictureUrl = url;
    }

    
    public UserModel(int id, String name, String email, String address, Date birthDate, String pictureUrl, String phone,
			String cardNumber, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.birthDate = birthDate;
		this.pictureUrl = pictureUrl;
		this.phone = phone;
		this.cardNumber = cardNumber;
		this.sex = sex;
	}
    
    public UserModel( String name, String email, String address, Date birthDate, String pictureUrl, String phone,
			String cardNumber, String sex) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.birthDate = birthDate;
		this.pictureUrl = pictureUrl;
		this.phone = phone;
		this.cardNumber = cardNumber;
		this.sex = sex;
	}
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
	public String getFirebaseId() {
		return firebaseId;
	}
	public void setFirebaseId(String firebaseId) {
		this.firebaseId = firebaseId;
	}
    
    
}
