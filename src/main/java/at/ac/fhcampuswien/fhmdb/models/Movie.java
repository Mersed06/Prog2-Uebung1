package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Movie {
    private String title;
    private String description;
    private List<Genre> genreList;
    private int releaseYear;
    private double rating;

    private JSONArray mainCast;

    private List<String> directors;



    public Movie(String title, String description, List<Genre> genre , int releaseYear, double rating, JSONArray cast, List<String> movieDirectors) {
        this.title = title;
        this.description = description;
        this.genreList = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.mainCast = cast;
        this.directors = movieDirectors;
        MovieAPI movie = new MovieAPI();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenreList() { return genreList; }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void addMovieGenre(Genre genre) {
        genreList.add(genre);
    }

    public static List<Movie> initializeMovies(String url) throws IOException {

       List<Movie> movies = new ArrayList<>();

       JSONArray json = new JSONArray(MovieAPI.getMovies(url));

        for (int i = 0; i < json.length(); i++) {
            JSONObject movie = json.getJSONObject(i);
            movies.add(new Movie(movie.getString("title"),movie.getString("description"),List.of(mapGenres(movie.getJSONArray("genres"))),movie.getInt("releaseYear"),movie.getDouble("rating"), movie.getJSONArray("mainCast"), List.of(movie.getString("directors"))));
        }
        return movies;
    }

    public static List<Movie> initializeMoviesWithoutParameter() throws IOException {

        return initializeMovies("https://prog2.fh-campuswien.ac.at/movies");
    }



    public static Genre[] mapGenres(JSONArray genresArray) {
        Genre[] genres = new Genre[genresArray.length()];
        for (int i = 0; i < genresArray.length(); i++) {
            String genreString = genresArray.getString(i).toUpperCase();
            genres[i] = Genre.valueOf(genreString);
        }
        return genres;
    }

    public JSONArray getMainCast() {
        return mainCast;
    }

    public List<String> getDirectors() {
        return directors;
    }
}
