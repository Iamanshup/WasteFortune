package in.thegeekybaniya.wastefortune.POJO;

/**
 * Created by Kabir on 21/03/2017.
 */

public class Order {
    String from, to, products;

    public Order() {
    }

    float cost;
    long orderDate, deliveryDate;


    public Order(String from, String to, String products) {
        this.from = from;
        this.to = to;
        this.products = products;
        this.deliveryDate = System.currentTimeMillis()+(4*90000000);


    }


    public String getFrom() {

        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public long getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(long deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
