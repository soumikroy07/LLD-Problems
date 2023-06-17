package LLDProblems.BookMyShow.Skeleton;

import java.util.ArrayList;

public class RegisteredUser extends User{

    private ArrayList<Ticket> bookingHistory;

    public RegisteredUser(int id, String name) {
        super(id, name);
        this.bookingHistory = new ArrayList<Ticket>();
    }
}
