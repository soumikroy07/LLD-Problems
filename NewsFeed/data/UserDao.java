package LLDProblems.NewsFeed.data;

import LLDProblems.NewsFeed.models.Comment;
import LLDProblems.NewsFeed.constant.Like;
import LLDProblems.NewsFeed.models.Post;
import LLDProblems.NewsFeed.models.User;
import LLDProblems.NewsFeed.util.IDGenerator;

import java.util.*;

public class UserDao {

    private static UserDao instance = null;

    public static UserDao getInstance(){
        if (instance == null){
            instance = new UserDao();
        }
        return instance;
    }

    Map<String, User> allUsersWithPhoneNoMap = new HashMap<>();
    Map<String, User> nameToUserMap = new HashMap<>();
    Map<Integer, Post> allPosts = new HashMap<>();
    Map<Post,User> postUserMap = new HashMap<>();

    public User registerUser(String name, String phoneNo) {
        if (allUsersWithPhoneNoMap.containsKey(phoneNo)){
            System.out.println("User already Exist");
            return null;
        }

        User user = new User();
        user.setUserId(IDGenerator.getId());
        user.setName(name);
        user.setPhoneNo(phoneNo);
        allUsersWithPhoneNoMap.put(phoneNo,user);

        System.out.println("User is successfully registered with the user name : "+user.getName()+
                " phone number : "+user.getPhoneNo());
        return user;
    }

    User loggedInUser = null;
    public User loginUser(String phoneNo) {
        if (! allUsersWithPhoneNoMap.containsKey(phoneNo)){
            System.out.println("Invalid phone number");
            return null;
        }

        loggedInUser = allUsersWithPhoneNoMap.get(phoneNo);
        System.out.println("User is successfully logged in with the name : "+loggedInUser.getName());
        return loggedInUser;
    }

    public User followUser(String name) {
        if (loggedInUser.getFollowing() != null){
            for (User followingUser : loggedInUser.getFollowing()){
                if (followingUser.getName().equalsIgnoreCase(name)){
                    System.out.println("Already followed");
                    return null;
                }
            }
        }

        User following = nameToUserMap.get(name);
        loggedInUser.getFollowing().add(following);
        System.out.println(loggedInUser.getName()+" followed "+following.getName());
        return following;
    }

    public Post postFeed(String name, String postType, Date date) {

        Post post = new Post();
        post.setPostId(IDGenerator.getId());
        post.setPostType(postType);
        post.setDate(date);
        post.setUser(nameToUserMap.get(name));
        allPosts.put(post.getPostId(), post);
        postUserMap.put(post, loggedInUser);

        return post;
    }

    public Comment commentsOnPost(String name, String commentValue) {
        Comment comment = new Comment();

        User user = nameToUserMap.get(name);
        comment.setComment(commentValue);
        comment.setCommentId(IDGenerator.getId());
        comment.setUser(user);

        return comment;
    }


    public void upvoteOrDownvote(String name, Like like) {
        User user = nameToUserMap.get(name);
        Post post = new Post();
        if (like.equals(Like.UPVOTE)){
            post.getUpVote().add(user);
        }else {
            post.getDownVote().add(user);
        }
    }

    public List<Post> showUserPost(String name) {
        ArrayList<Post> listOfPost = new ArrayList<>();
        User user = nameToUserMap.get(name);
        for (User followingUser : user.getFollowing()){
            for (Post post : followingUser.getUserPost()){
                listOfPost.add(post);
            }
        }
        Collections.sort(listOfPost, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getScore()> o2.getScore()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        return listOfPost;
    }
}
