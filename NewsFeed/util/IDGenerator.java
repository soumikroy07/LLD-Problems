package LLDProblems.NewsFeed.util;

public class IDGenerator {
    private static int id = 0;
    public static int getId(){
        id++;
        return id;
    }
}
