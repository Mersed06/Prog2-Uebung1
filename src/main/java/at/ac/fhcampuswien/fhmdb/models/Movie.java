package at.ac.fhcampuswien.fhmdb.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here

    private List<Genre> movieGenre;

    //genre List
    private List<Genre> genres = List.of(Genre.values());

    // Front Cover IMG Object?

    public Movie(String title, String description, List<Genre> genre) {
        this.title = title;
        this.description = description;
        this.movieGenre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenre()
    {
        return movieGenre;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        movies.add(new Movie(
                "The Matrix",
                "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.",
                List.of(Genre.ACTION,Genre.ADVENTURE)));
       movies.add(new Movie(
                "Avatar",
                "To explore Pandora, genetically matched human scientists use Na'vi-human hybrids called \"avatars.\" Paraplegic Marine Jake Sully is sent to Pandora to replace his deceased identical twin, who had signed up to be an operator.",
                List.of(Genre.SCIENCE_FICTION)));
        movies.add(new Movie(
                "The Dark Knight Rises",
                "Eight years after the Joker's reign of chaos, Batman is coerced out of exile with the assistance of the mysterious Selina Kyle in order to defend Gotham City from the vicious guerrilla terrorist Bane",
                List.of(Genre.CRIME)));
        movies.add(new Movie(
                "Spider-Man",
                "After being bitten by a genetically-modified spider, a shy teenager gains spider-like abilities that he uses to fight injustice as a masked superhero and face a vengeful enemy.",
                List.of(Genre.ACTION)));
        movies.add(new Movie(
                "Batman",
                "Batman was originally introduced as a ruthless vigilante who frequently killed or maimed criminals, but evolved into a character with a stringent moral code and strong sense of justice.",
                List.of(Genre.ACTION)));
        movies.add(new Movie(
                "The Intouchables",
                "The Intouchables tells the true story of a wealthy, physically disabled risk taker, the picture of established French nobility, who lost his wife in an accident and whose world is turned upside down when he hires a young, good-humored, black Muslim ex-con as his caretaker.",
                List.of(Genre.ACTION)));






        return movies;
    }
}
