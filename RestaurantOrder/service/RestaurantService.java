package LLDProblems.RestaurantOrder.service;

import LLDProblems.RestaurantOrder.data.UserDao;
import LLDProblems.RestaurantOrder.models.Restaurant;
import LLDProblems.RestaurantOrder.models.Review;

import java.util.List;

public class RestaurantService {
    private static RestaurantService instance = null;

    private RestaurantService(){

    }

    public static RestaurantService getInstance(){
        if (instance == null){
            instance = new RestaurantService();
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Restaurant registerRestaurant(String restaurantName, String pinCode, String item, int price, int quantity){
        if (price <= 0 || quantity < 0){
            System.out.println("Invalid input data provided");
            return null;
        }
        if (restaurantName.isEmpty()){
            System.out.println("provide the restaurant name at first");
            return null;
        }

        return userDao.registerRestaurant(restaurantName,pinCode,item, price,quantity);
    }

    public Review rateRestaurant (String restaurantName, Integer rating, String comment){
        if (rating == null || rating < 0 || rating > 5){
            System.out.println("Invalid input");
            return null;
        }
        return userDao.rateRestaurant(restaurantName, rating, comment);
    }

    public Restaurant updateQuantity(String restaurantName, int quantity){
        if(quantity <= 0){
            System.out.println("Invalid input provided");
            return null;
        }
        return userDao.updateQuantity(restaurantName,quantity);
    }

    public List<Restaurant> showRestaurant(String sortBy){
        return userDao.showRestaurant(sortBy);
    }
}
