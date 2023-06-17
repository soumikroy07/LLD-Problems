package LLDProblems.DatingApp.service;

import LLDProblems.DatingApp.data.UserDao;
import LLDProblems.DatingApp.model.Gender;
import LLDProblems.DatingApp.model.Location;
import LLDProblems.DatingApp.model.Match;
import LLDProblems.DatingApp.model.User;


public class UserService {
    public static UserService instance = null;
    private UserService(){}

    public static UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public User registerUser(int age, String userName, String phoneNumber, Gender gender, int x, int y){
        if (age < 18){
            System.out.println("You are not adult..! ");
        }
        if (userName.isEmpty()){
            System.out.println("Enter a valid User Name ");
        }
        if (phoneNumber.length() != 10){
            System.out.println("Invalid phone number");
        }

        return userDao.registerUser(age,userName,phoneNumber,gender,new Location(x,y));
    }

    public User deactivateUser(User user){
        if (! user.getUserActionMap().containsKey(user)){
            System.out.println("Invalid user provide");
        }

        user.getUserActionMap().clear();

        //TODO: this logic should shift in Dao
        for (Match match : user.getMatches()){
            if (match.getUser1().equals(user) || match.getUser2().equals(user)){
                userDao.getMatchMap().remove(match.getId());
            }
        }

        user.getMatches().clear();

        //if someone has LIKED or IGNORED, remove that from data too.
        for(User user1: user.getUserWhoLikedYou()){
            user1.getUserWhoLikedYou().remove(user);
        }

        //Remove from overall Match map too
        //TODO: this part also need to shift in Dao
        for(Match match : userDao.getMatchMap().values()){
            if(match.getUser1().equals(user) || match.getUser2().equals(user)){
                userDao.getMatchMap().remove(match.getId());
            }
        }
        return user;
    }
}
