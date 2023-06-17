package LLDProblems.NewsFeed.models;

import LLDProblems.NewsFeed.constant.Like;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int postId;
    private String postType;
    private Date date;
    private User user;
    private Like like;
    private int score;
    private List<Comment> comments;
    private List<User> upVote;
    private List<User> downVote;

    public Post(int postId, String postType, Date date, User user,Like like) {
        this.postId = postId;
        this.postType = postType;
        this.date = date;
        this.user = user;
        this.like = like;
        comments = new ArrayList<>();
        upVote = new ArrayList<>();
        downVote = new ArrayList<>();
    }

    public Post() {

    }

    public int getScore(){
        return upVote.size() - downVote.size();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Like getLike() {
        return like;
    }

    public List<User> getUpVote() {
        return upVote;
    }

    public List<User> getDownVote() {
        return downVote;
    }
}
