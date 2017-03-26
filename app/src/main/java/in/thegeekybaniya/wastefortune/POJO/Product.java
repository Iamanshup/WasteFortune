package in.thegeekybaniya.wastefortune.POJO;

/**
 * Created by Kabir on 21/03/2017.
 */

public class Product {


    static final String TYPE_LAPTOP="laptop";
    static final String TYPE_TV="tv";
    static final String TYPE_MOBILE="mobile";


    String name;
    String description;
    float price;
    String imgRef;
    String sellerEnEmail;
    String type;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Product(String name, String description, float price, String sellerEnEmail, String type) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.sellerEnEmail = sellerEnEmail;
        this.type = type;
    }

    public Product(String name, String description, float price, String type) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {
    }

    public String getSellerEnEmail() {
        return sellerEnEmail;
    }

    public void setSellerEnEmail(String sellerEnEmail) {
        this.sellerEnEmail = sellerEnEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
