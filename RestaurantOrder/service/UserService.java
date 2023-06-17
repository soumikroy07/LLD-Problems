package LLDProblems.RestaurantOrder.service;

import LLDProblems.RestaurantOrder.Constant.Gender;
import LLDProblems.RestaurantOrder.data.UserDao;
import LLDProblems.RestaurantOrder.models.User;

public class UserService {
    private static  UserService instance = null;

    private UserService(){

    }

    public static UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public User registerUser(Long phone , String name, Long pinCode, Gender gender){
        if(phone == null || phone <= 0){
            System.out.println("Invalid phone number");
            return null;
        }
        else if(name == null){
            System.out.println("Invalid name");
        }
        else if(pinCode == null || pinCode<=0){
            System.out.println("Invalid pincode");
        }

        return userDao.registerUser(phone,name,phone,gender);
    }

    public User login(Long phoneNumber){
        return userDao.login(phoneNumber);
    }
}
