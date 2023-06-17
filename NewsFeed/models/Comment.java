package LLDProblems.NewsFeed.models;

public class Comment {
    private int CommentId;
    private User user;
    private String comment;

    public Comment(int commentId, User user, String comment) {
        CommentId = commentId;
        this.user = user;
        this.comment = comment;
    }

    public Comment() {

    }

    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
