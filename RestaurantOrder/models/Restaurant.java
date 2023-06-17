package LLDProblems.RestaurantOrder.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private String item;
    private int price;
    private List<Long> serviceablePinCode = new ArrayList<>();
    private int quantity;
    private Float ratting;
    private List<Review> reviews = new ArrayList<>();
    private Integer createdBy;

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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Long> getServiceablePinCode() {
        return serviceablePinCode;
    }

    public void setServiceablePinCode(List<Long> serviceablePinCode) {
        this.serviceablePinCode = serviceablePinCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getRatting() {
        return ratting;
    }

    public void setRatting(Float ratting) {
        this.ratting = ratting;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

}
