package LLDProblems.DatingApp.data;

import LLDProblems.DatingApp.model.Gender;
import LLDProblems.DatingApp.model.Location;
import LLDProblems.DatingApp.model.Match;
import LLDProblems.DatingApp.model.User;
import LLDProblems.DatingApp.util.IDGenerator;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    public static UserDao instance = null;

    private UserDao(){}

    public static UserDao getInstance(){
        if (instance == null){
            instance = new UserDao();
        }
        return instance;
    }

    //Map with userId , User
    public Map<Integer, User> userMap = new HashMap<Integer,User>();

    //Map with matchId , Match
    public Map<Integer, Match> matchMap = new HashMap<>();

    //Map with phoneNumber , UserId
    public HashMap<String,Integer> phoneMap = new HashMap<>();

    public User registerUser(int age, String name, String no, Gender gender, Location location){
        if (age < 18){
            System.out.println("You are not adult..!");
            return null;
        }

        if (phoneMap.containsKey(no)){
            User user = userMap.get(phoneMap.get(no));
            System.out.println("User is already present with the name "+user.getName()+
                    "no need to create another account");
            return user;
        }

        User user = new User();
        user.setAge(age);
        user.setGender(gender);
        user.setId(IDGenerator.getId());
        user.setLocation(location);
        user.setName(name);
        user.setMobile(no);
        System.out.println("New user created with "+user.getName()+ "with ID : "+ user.getId());
        userMap.put(user.getId(), user);
        phoneMap.put(no,user.getId());
        return user;
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    public Map<Integer, Match> getMatchMap() {
        return matchMap;
    }

    public void setMatchMap(Map<Integer, Match> matchMap) {
        this.matchMap = matchMap;
    }
}
