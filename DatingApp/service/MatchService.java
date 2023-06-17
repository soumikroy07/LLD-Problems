package LLDProblems.DatingApp.service;

import LLDProblems.DatingApp.Exception.NoUserFoundException;
import LLDProblems.DatingApp.data.UserDao;
import LLDProblems.DatingApp.model.Action;
import LLDProblems.DatingApp.model.Location;
import LLDProblems.DatingApp.model.Match;
import LLDProblems.DatingApp.model.User;
import LLDProblems.DatingApp.util.IDGenerator;
import jdk.javadoc.doclet.Taglet;

import java.util.*;

public class MatchService {

    private static MatchService instance = null;
    private MatchService(){

    }

    public static MatchService getInstance(){
        if (instance == null){
            instance = new MatchService();
        }
        return instance;
    }

    private static Integer MAX_DISTANCE =  3;

    UserDao userDao = UserDao.getInstance();

    // this should be in util class
    public int distance(Location user1, Location user2){
        return (int)Math.sqrt((user1.getX() - user2.getX())*(user1.getX() - user2.getX()) +
                (user1.getY() - user2.getY())*(user1.getY() - user2.getY()));
    }

    // this function should be in  Dao layer

    public List<User> findPotentialMatches(User user) throws NoUserFoundException {
        //firstly fetch all users
        List<User> potentialUser = fetchAllUsers(user);
        //Validation
        if(potentialUser==null || potentialUser.size()==0){
            throw new NoUserFoundException();
        }
        //sort them basis custom comparator logic and Print them.
        Collections.sort(potentialUser,new SortbyAge());
        System.out.println("Following are the potential matches for user "+user.getName()+"\n");
        int count=0;
        for (User user1:potentialUser){
            count++;
            System.out.println(count+": User Id: "+user1.getId()+": User Name: "+user1.getName()+" User Age: "+user1.getAge());
        }
        System.out.println("\n");
        return potentialUser;
    }


    public List<User> fetchAllUsers(User user){
        //if user is ACTIVE and Gender is Opposite, & distance is within range then its a potential user
        List<User> userList = new ArrayList<>();
        for(User potentialUser : userDao.getUserMap().values()){
            if(!user.getGender().equals(potentialUser.getGender()) &&
                    distance(potentialUser.getLocation(),user.getLocation()) < MAX_DISTANCE ){
                userList.add(potentialUser);
            }
        }
        return userList;
    }

    //LIKE or IGNORE
    public Match actOnPotentialMatch(Integer id, Integer id2, Action actionType) throws NoUserFoundException {

        //If User does not exist
        if(!userDao.getUserMap().containsKey(id) || !userDao.getUserMap().containsKey(id2)){
            throw new NoUserFoundException();
        }

        //fetch the users
        User user1 = userDao.getUserMap().get(id);
        User user2 = userDao.getUserMap().get(id2);
        System.out.println("User "+user1.getName()+" has "+actionType+" User "+user2.getName()+"\n");

        //if u have LIKED a user, who already liked you, then its a match
        if(user1.getUserWhoLikedYou().contains(user2) && actionType.equals(Action.RIGHT)){
            System.out.println("Match found between "+user1.getName()+" and "+user2.getName()+"\n");
            Match match = new Match();
            match.setId(IDGenerator.getId());
            match.setUser1(user1);
            match.setUser2(user2);

            user1.getMatches().add(match);
            user2.getMatches().add(match);
            //put in match map
            userDao.getMatchMap().put(match.getId(),match);
            return match;
        }

        //update user specific data for both users i.e. User1 and User2
        user1.getUserActionMap().put(user2,actionType);
        if(actionType.equals(Action.RIGHT)) {
            user2.getUserWhoLikedYou().add(user1);
        }

        return null;
    }

    public void showMatchesOfUser(User user){
        if (! user.getUserActionMap().containsKey(user)){
            System.out.println("Invalid user provide");
        }

        if (user.getMatches().size() == 0){
            System.out.println("You have no likes yet");
            return;
        }

        System.out.println("List of all matches of "+ user.getName());
        int count = 1;
        for (Match match : user.getMatches()){
            System.out.println(count + " : " + match.getUser1().getName() + " : " + match.getUser2().getName());
            count++;
        }
        System.out.println();

    }

    public void showAllMatches(){
        //Just Print System Specific data
        if(userDao.getMatchMap().values().size()==0){
            System.out.println("There are no matches in system \n" );
            return;
        }
        System.out.println("List of all the matches present in system \n" );
        int count=1;
        for(Match match : userDao.getMatchMap().values()){

            System.out.println("Match number :"+count+" { "+match.getUser1().getName()+","+match.getUser2().getName()+"} ");
            count++;
        }
        System.out.println("\n");
    }

    //this also should be in Dao
    class SortbyAge implements Comparator<User>
    {
        public int compare(User a, User b)
        {
            return a.getAge()-b.getAge();
        }
    }
}
