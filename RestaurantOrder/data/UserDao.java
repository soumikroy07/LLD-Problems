package LLDProblems.RestaurantOrder.data;

import LLDProblems.RestaurantOrder.Constant.Gender;
import LLDProblems.RestaurantOrder.Util.IDGenerator;
import LLDProblems.RestaurantOrder.models.Order;
import LLDProblems.RestaurantOrder.models.Restaurant;
import LLDProblems.RestaurantOrder.models.Review;
import LLDProblems.RestaurantOrder.models.User;

import java.util.*;

public class UserDao {

    private static UserDao userDao = null;

    private UserDao(){

    }

    public static UserDao getInstance(){
        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }

    private HashMap<Integer , User> userHashMap = new HashMap<>();

    private HashMap<Long, Integer> phoneNoMap = new HashMap<>();

    private HashMap<String, Restaurant> restaurantHashMap = new HashMap<>();

    private User loggedInUser = null;

    public User login(Long phoneNumber) {
        if (! phoneNoMap.containsKey(phoneNumber)){
            System.out.println("No user present int the given phone Number" +phoneNumber);
            return null;
        }

        User user = userHashMap.get(phoneNumber);
        loggedInUser = user;
        System.out.println("Successfully login with the user user_id: "+loggedInUser.getId()+
                "user_name : "+loggedInUser.getName());
        return user;
    }

    public User registerUser(Long phone, String name, Long pincode, Gender gender) {
        if (phoneNoMap.containsKey(phone)){
            User user = userHashMap.get(phoneNoMap.get(phone));
            System.out.println("User already exist with the phone number "+phone);

            return user;
        }

        User user  = new User(IDGenerator.getId(), name, gender, phone, pincode);
        phoneNoMap.put(phone, user.getId());
        userHashMap.put(user.getId(),user);

        System.out.println("User successFully added with the userId" + user.getId() + " phoneNumber is "+ user.getPhone());
        return user;
    }

    public Restaurant registerRestaurant(String restaurantName,String pinCodes, String item,int price,  int quantity) {
        if (loggedInUser == null) {
            System.out.println("No logged in user found");
            return null;
        }

        if (restaurantHashMap.containsKey(restaurantName)) {
            System.out.println("Restaurant is already registered with this name" + restaurantName);
            return null;
        }

        List<String> pinCodeList = Arrays.asList(pinCodes.split(","));
        List<Long> pins = new ArrayList<>();
        if (!pinCodes.isEmpty()) {

            for (String s : pinCodeList) {
                if (!s.chars().allMatch(Character::isDigit)) {
                    System.out.println("Invalid pincode provided\n");
                    return null;
                }
                pins.add(Long.parseLong(s));
            }
        }
        //TODO: check the feasibility of Builder Pattern here
        Restaurant restaurant = new Restaurant();
        restaurant.setId(IDGenerator.getId());
        restaurant.setName(restaurantName);
        restaurant.setItem(item);
        restaurant.setQuantity(quantity);
        restaurant.setPrice(price);
        restaurant.setServiceablePinCode(pins);
        restaurant.setCreatedBy(loggedInUser.getId());

        //restaurant map update krdiya

        restaurantHashMap.put(restaurantName,restaurant);

        // adding restaurant in user's list
        // yaha kuch gadbad lag rhi h

        loggedInUser.getRestaurants().add(restaurant);
        System.out.println("Successfully registered restaurant  id" + restaurant.getId()+"\n");

        return restaurant;
    }

    public Review rateRestaurant(String restaurantName, Integer rating, String comment){
        Restaurant restaurant = restaurantHashMap.get(restaurantName);

        if (restaurant == null){
            System.out.println("No restaurant found with the given name");
            return null;
        }
        Review review = new Review();

        review.setId(IDGenerator.getId());
        review.setComment(comment);
        review.setScore(rating);

        // if it is a first review
        if (restaurant.getReviews() == null || restaurant.getReviews().size() == 0){
            restaurant.setRatting(Float.valueOf(rating));
        }else{
            float currentScore = (restaurant.getRatting() * restaurant.getReviews().size() + rating)
                    / (restaurant.getReviews().size() + 1);
            restaurant.setRatting(currentScore);


        }
        restaurant.getReviews().add(review);
        return review;
    }

    public Restaurant updateQuantity(String restaurantName, int quantity){
        Restaurant restaurant = restaurantHashMap.get(restaurantName);

        if (restaurant == null){
            System.out.println("No restaurant found with the given name");
            return null;
        }

        restaurant.setQuantity(restaurant.getQuantity()+quantity);
        return restaurant;
    }

    public List<Restaurant> showRestaurant (String sortBy){
        List<Restaurant> r = loggedInUser.getRestaurants();
        List<Restaurant> restaurants = new ArrayList<>();

        for (Restaurant restaurant : r){
            if (restaurant.getServiceablePinCode().contains(loggedInUser.getPinCode())
                    && restaurant.getQuantity()>0){
                restaurants.add(restaurant);
            }
        }

        if (sortBy.equalsIgnoreCase("rating")){
            Collections.sort(restaurants,new SortByRating());
            for (Restaurant restaurant : restaurants){
                System.out.println("Restaurant id " + restaurant.getId() + " Restaurant Name "+ restaurant.getName()
                +"price : " + restaurant.getPrice()+ " rating : "+ restaurant.getRatting());
            }
            return restaurants;
        }

        // by default price

        Collections.sort(restaurants, new SortByPrice());

        for(Restaurant restaurant: restaurants){
            System.out.println("Restaurant id :"+restaurant.getId()+ ": name-> "+
                    restaurant.getName()+": price-> "+restaurant.getPrice()+": rating -> "+restaurant.getRatting());
        }
        return restaurants;

    }

    public Order placeOrder (String restaurantName, Integer quantity){
        Restaurant restaurant = restaurantHashMap.get(restaurantName);

        if(restaurant == null){
            System.out.println("No restaurant found with the given name");
            return null;
        }

        if (restaurant.getQuantity() < quantity){
            System.out.println("this restaurant has only "+restaurant.getQuantity() + "Items, please check it6 again");
            return null;
        }

        Order order = new Order();
        order.setId(IDGenerator.getId());
        order.setCost((long) (restaurant.getPrice()*quantity));
        order.setQuantity(quantity);
        order.setUserId(loggedInUser.getId());
        order.setItem(restaurant.getItem());

        // Set quantity for restaurant

        restaurant.setQuantity(restaurant.getQuantity() - quantity);

        // add this order to the order history of the user

        loggedInUser.getOrders().add(order);
        return order;
    }



    class SortByRating implements Comparator<Restaurant> {
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            if(o1.getRatting()==null || o2.getRatting()==null) return 0;
            if(o1.getRatting()>o2.getRatting()) {
                return 1;
            }
            else if(o1.getRatting()==o2.getRatting()){
                return 0;
            }
            else {
                return -1;
            }
        }
    }
    class SortByPrice implements Comparator<Restaurant>{
        @Override
        public int compare(Restaurant o1, Restaurant o2) {
            return o1.getPrice()-o2.getPrice();
        }
    }
}
