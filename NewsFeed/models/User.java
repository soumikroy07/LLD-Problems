package LLDProblems.NewsFeed.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int userId;
    private String name;
    private String phoneNo;

    private List<User> followers;
    private List<User> following;
    private Map<Integer , Comment> userComment;
    private List<Post> userPost;

    public User(int userId, String name, String phoneNo) {
        this.userId = userId;
        this.name = name;
        this.phoneNo = phoneNo;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        userComment = new HashMap<>();
        userPost = new ArrayList<>();
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public Map<Integer, Comment> getUserComment() {
        return userComment;
    }

    public void setUserComment(Map<Integer, Comment> userComment) {
        this.userComment = userComment;
    }

    public List<Post> getUserPost() {
        return userPost;
    }
}
