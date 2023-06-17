package LLDProblems.NewsFeed;

import LLDProblems.NewsFeed.service.UserService;

public class Main {
    public static void main(String[] args) {

        // register user
        UserService soumik = UserService.getInstance();
        soumik.registerUser("Soumik", "7980673313");
        soumik.registerUser("Adrija", "7595859189");

        //log in with user
        soumik.loginUer("7980673313");

        //follow other user
        soumik.followUser("Soumik");
    }
}
