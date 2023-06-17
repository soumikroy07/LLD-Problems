package LLDProblems.BookMyShow.Skeleton;

import java.util.Date;

public class Ticket {
    private int id;
    private Date bookingTime;
    private String ownerName;
    private int noOfSeats;
    private Show bookedShow;

    public Ticket(int id, Date bookingTime, String ownerName, int noOfSeats, Show bookedShow) {
        this.id = id;
        this.bookingTime = bookingTime;
        this.ownerName = ownerName;
        this.noOfSeats = noOfSeats;
        this.bookedShow = bookedShow;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Show getBookedShow() {
        return bookedShow;
    }

    public void setBookedShow(Show bookedShow) {
        this.bookedShow = bookedShow;
    }
}
