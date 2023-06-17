package LLDProblems.DatingApp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private Location location;
    private String mobile;

    private Map<User , Action> userActionMap = new HashMap<User, Action>();
    private List<Match> matches = new ArrayList<>();
    private List<User> userWhoLikedYou = new ArrayList<>();

    public User(int id, String name, int age, Gender gender, Location location, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.mobile = phone;
    }

    public User() {

    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String phone) {
        this.mobile = phone;
    }

    public Map<User, Action> getUserActionMap() {
        return userActionMap;
    }

    public void setUserActionMap(Map<User, Action> userActionMap) {
        this.userActionMap = userActionMap;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<User> getUserWhoLikedYou() {
        return userWhoLikedYou;
    }

    public void setUserWhoLikedYou(List<User> userWhoLikedYou) {
        this.userWhoLikedYou = userWhoLikedYou;
    }
}
