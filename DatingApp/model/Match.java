package LLDProblems.DatingApp.model;

import java.util.Date;

public class Match {
    private int id;
    private User user1;
    private User user2;
    private Date matchAt;


    public Match(int id, User user1, User user2, Date matchAt) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.matchAt = matchAt;
    }

    public Match() {

    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMatchAt() {
        return matchAt;
    }

    public void setMatchAt(Date matchAt) {
        this.matchAt = matchAt;
    }
}
