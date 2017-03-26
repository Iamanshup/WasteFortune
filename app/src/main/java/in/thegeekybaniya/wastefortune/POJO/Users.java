package in.thegeekybaniya.wastefortune.POJO;

import java.util.ArrayList;

/**
 * Created by Kabir on 09/03/2017.
 */

public class Users {

    ArrayList<String> cart, orders;
    String name;
    String email;
    String uid;
    String imgUrl;
    String password;

    public Users(ArrayList<String> cart, ArrayList<String> orders, String name, String email, String uid) {
        this.cart = cart;
        this.orders = orders;
        this.name = name;
        this.email = email;
        this.uid = uid;
    }

    public Users(String name, String email, String imgUrl) {
        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
    }

    public Users() {

    }

    public void AddCart(String s) {

        if (this.cart==null){
            this.cart=new ArrayList<>();

        }

        this.cart.add(s);
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void AddOrder(String s) {

        if (this.orders==null){
            this.orders=new ArrayList<>();

        }


        this.orders.add(s);


    }

    public ArrayList<String> getCart() {
        return cart;
    }

    public void setCart(ArrayList<String> cart) {
        this.cart = cart;
    }

    public ArrayList<String> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<String> orders) {
        this.orders = orders;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEncodedMail() {
        return this.email.replace(".", ",");

    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
