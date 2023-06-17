package LLDProblems.BookMyShow.Skeleton;

import LLDProblems.BookMyShow.Skeleton.Enums.Genre;
import LLDProblems.BookMyShow.Skeleton.Enums.Language;

public class Movie {
    private String name;
    private float ratings = 0.0f;

    private Genre genre;
    private Language language;

    public Movie(String name, Genre genre, Language language) {
        this.name = name;
        this.genre = genre;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
