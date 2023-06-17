package LLDProblems.RestaurantOrder.service;

import LLDProblems.RestaurantOrder.data.UserDao;
import LLDProblems.RestaurantOrder.models.Order;

import java.util.List;

public class OrderService {
    private static OrderService instance = null;
    private OrderService(){}
    public static OrderService getInstance(){
        if (instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Order placeOrder(String orderName, Integer quantity){
        if (quantity == 0){
            System.out.println("Invalid input for mandatory field");
            return null;
        }

        return userDao.placeOrder(orderName,quantity);
    }

}
