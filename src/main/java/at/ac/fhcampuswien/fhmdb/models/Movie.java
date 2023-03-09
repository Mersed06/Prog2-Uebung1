package at.ac.fhcampuswien.fhmdb.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here

    //genre List
    private List<Genre> genres;

    // Front Cover IMG Object?

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
        this.genres = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres()
    {
        return genres;
    }

    public void addMovieGenre(Genre genre) {
        genres.add(genre);
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        Movie movie = new Movie(
                "The Matrix",
                "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");
        movie.addMovieGenre(Genre.ACTION);
        movie.addMovieGenre(Genre.SCIENCE_FICTION);
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.FANTASY);
        movies.add(movie);

        movie = new Movie(
                "Avatar",
                "To explore Pandora, genetically matched human scientists use Na'vi-human hybrids called \"avatars.\" Paraplegic Marine Jake Sully is sent to Pandora to replace his deceased identical twin, who had signed up to be an operator.");
        movie.addMovieGenre(Genre.ACTION);
        movie.addMovieGenre(Genre.SCIENCE_FICTION);
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.FANTASY);
        movie.addMovieGenre(Genre.MYSTERY);
        movies.add(movie);

        movie = new Movie(
                "The Dark Knight Rises",
                "Eight years after the Joker's reign of chaos, Batman is coerced out of exile with the assistance of the mysterious Selina Kyle in order to defend Gotham City from the vicious guerrilla terrorist Bane");
        movie.addMovieGenre(Genre.ACTION);
        movie.addMovieGenre(Genre.DRAMA);
        movie.addMovieGenre(Genre.THRILLER);
        movie.addMovieGenre(Genre.CRIME);
        movies.add(movie);

        movie = new Movie(
                "Spider-Man",
                "After being bitten by a genetically-modified spider, a shy teenager gains spider-like abilities that he uses to fight injustice as a masked superhero and face a vengeful enemy.");
        movie.addMovieGenre(Genre.ACTION);
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.SCIENCE_FICTION);
        movie.addMovieGenre(Genre.FANTASY);
        movies.add(movie);

        movie = new Movie(
                "Batman",
                "Batman was originally introduced as a ruthless vigilante who frequently killed or maimed criminals, but evolved into a character with a stringent moral code and strong sense of justice.");
        movie.addMovieGenre(Genre.ACTION);
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.CRIME);
        movie.addMovieGenre(Genre.MYSTERY);
        movie.addMovieGenre(Genre.DRAMA);
        movies.add(movie);

        movie = new Movie(
                "The Intouchables",
                "The Intouchables tells the true story of a wealthy, physically disabled risk taker, the picture of established French nobility, who lost his wife in an accident and whose world is turned upside down when he hires a young, good-humored, black Muslim ex-con as his caretaker.");
        movie.addMovieGenre(Genre.COMEDY);
        movie.addMovieGenre(Genre.DRAMA);
        movies.add(movie);

        movie = new Movie(
                "Up",
                "78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.");
        movie.addMovieGenre(Genre.COMEDY);
        movies.add(movie);

        movie = new Movie(
                "Chucky",
                "A single mother gives her son a much sought-after doll for his birthday, only to discover that it is possessed by the soul of a serial killer.");
        movie.addMovieGenre(Genre.DRAMA);
        movie.addMovieGenre(Genre.HORROR);
        movie.addMovieGenre(Genre.COMEDY);
        movies.add(movie);

        movie = new Movie(
                "Django Unchained",
                "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.");
        movie.addMovieGenre(Genre.ACTION);
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.DRAMA);
        movies.add(movie);

        movie = new Movie(
                "E.T. the Extra-Terrestrial",
                "A troubled child summons the courage to help a friendly alien escape from Earth and return to his home planet.");
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.SCIENCE_FICTION);
        movie.addMovieGenre(Genre.FAMILY);
        movies.add(movie);

        movie = new Movie(
                "Finding Nemo",
                "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.");
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.COMEDY);
        movie.addMovieGenre(Genre.DRAMA);
        movies.add(movie);

        movie = new Movie(
                "No Country for Old Men",
                "Violence and mayhem ensue after a hunter stumbles upon a drug deal gone wrong and more than two million dollars in cash near the Rio Grande.");
        movie.addMovieGenre(Genre.THRILLER);
        movie.addMovieGenre(Genre.CRIME);
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.DRAMA);
        movie.addMovieGenre(Genre.MYSTERY);
        movies.add(movie);

        movie = new Movie(
                "Zathura",
                "Two young brothers are drawn into an intergalactic adventure when their house is hurled through the depths of space by the magical board game they are playing.");
        movie.addMovieGenre(Genre.ADVENTURE);
        movie.addMovieGenre(Genre.SCIENCE_FICTION);
        movie.addMovieGenre(Genre.FAMILY);
        movies.add(movie);

        movie = new Movie(
                "Wargames",
                "Ultimate cold war hacker fantasy disguised as a teen movie. Computer whiz kid hacks into the US military's nuclear arsenal control site and decides that a showdown with Russia would be enormous fun. Matthew Broderick and Ally Sheedy lend Brat Pack credibility.");
        movie.addMovieGenre(Genre.SCIENCE_FICTION);
        movie.addMovieGenre(Genre.THRILLER);
        movies.add(movie);

        return movies;
    }
}
