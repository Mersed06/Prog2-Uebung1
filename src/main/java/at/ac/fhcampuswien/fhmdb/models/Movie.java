package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here

    //genre List
    List<String> genreList=new ArrayList<>(List.of("ACTION", "ADVENTURE",
            "ANIMATION", "BIOGRAPHY", "COMEDY", "CRIME", "DRAMA", "DOCUMENTARY", "FAMILY",
            "FANTASY", "HISTORY", "HORROR", "MUSICAL", "MYSTERY", "ROMANCE", "SCIENCE_FICTION",
            "SPORT", "THRILLER", "WAR", "WESTERN"));

    // Front Cover IMG Object?

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here

        return movies;
    }
}
