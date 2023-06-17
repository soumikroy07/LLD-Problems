package LLDProblems.NewsFeed.service;

import LLDProblems.NewsFeed.data.UserDao;
import LLDProblems.NewsFeed.models.Comment;
import LLDProblems.NewsFeed.constant.Like;
import LLDProblems.NewsFeed.models.Post;

import java.util.Date;
import java.util.List;

public class PostService {
    private static PostService instance = null;

    private PostService(){}

    public PostService getInstance(){
        if (instance == null){
            instance = new PostService();
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Post postFeed(String name, String postType, Date date){
        if (name == null || name.length() == 0){
            System.out.println("Invalid user name");
            return null;
        }

        if (postType == null){
            System.out.println("Invalid post Type");
            return null;
        }

        if (date == null){
            System.out.println("Invalid Date given");
        }

        return userDao.postFeed(name,postType,date);
    }

    public Comment commentsOnPost(String name, String comment){
        if (name == null || name.length() == 0){
            System.out.println("Invalid user name");
            return null;
        }

        if (comment == null || comment.length() == 0){
            System.out.println("Invalid user comment");
            return null;
        }

        return userDao.commentsOnPost(name,comment);
    }

    public void upvoteOrDownVote(String name, Like like){
        if (name == null || name.length() == 0){
            System.out.println("Invalid user name");
            return;
        }

        userDao.upvoteOrDownvote(name, like);
    }

    public List<Post> showUserPost(String name){
        if (name == null || name.length() == 0){
            System.out.println("Invalid user name");
            return null;
        }

        return userDao.showUserPost(name);
    }

}
