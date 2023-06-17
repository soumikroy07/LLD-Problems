package LLDProblems.NewsFeed.service;

import LLDProblems.NewsFeed.data.UserDao;
import LLDProblems.NewsFeed.models.User;
import LLDProblems.NewsFeed.util.IDGenerator;

public class UserService {
    private static UserService instance = null;

    private UserService(){}

    public static UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }
    UserDao userDao = UserDao.getInstance();

    public User registerUser(String name, String phoneNo){
        if (name == null || name.length() == 0){
            System.out.println("Invalid user name");
            return null;
        }

        if (phoneNo == null || phoneNo.length() != 10){
            System.out.println("Invalid phone Number");
            return null;
        }
;
        return userDao.registerUser(name, phoneNo);
    }

    public User loginUer(String phoneNo){
        if (phoneNo == null || phoneNo.length() != 10){
            System.out.println("Invalid phone Number");
            return null;
        }

        return userDao.loginUser(phoneNo);
    }

    public User followUser (String name){
        if (name == null || name.length() == 0){
            System.out.println("Invalid user name");
            return null;
        }

        return userDao.followUser(name);
    }
}
