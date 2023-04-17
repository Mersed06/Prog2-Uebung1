package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.*;
import com.google.gson.*;

public class MovieAPI {
    private static final String URL = "https://prog2.fh-campuswien.ac.at/movies";
    private static final String queryURL = "query=";
    private static final String genreURL = "genre=";
    private static final String releaseYearURL = "releaseYear=";
    private static final String ratingFromURL = "ratingFrom=";

    public static String buildUrl(String query, Genre genre, String releaseYear, String ratingFrom) {
        StringBuilder urlBuild = new StringBuilder(URL);

        if ((query != null && !query.isEmpty()) && genre != null && releaseYear != null && ratingFrom != null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(genreURL).append(genre).append("&");
            urlBuild.append(releaseYearURL).append(releaseYear).append("&");
            urlBuild.append(ratingFromURL).append(ratingFrom);
        } else if ((query != null && !query.isEmpty()) && genre != null && releaseYear != null && ratingFrom == null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(genreURL).append(genre).append("&");
            urlBuild.append(releaseYearURL).append(releaseYear);
        } else if ((query != null && !query.isEmpty()) && genre != null && releaseYear == null && ratingFrom != null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(genreURL).append(genre).append("&");
            urlBuild.append(ratingFromURL).append(ratingFrom);
        } else if ((query != null && !query.isEmpty()) && genre == null && releaseYear != null && ratingFrom != null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(releaseYearURL).append(releaseYear).append("&");
            urlBuild.append(ratingFromURL).append(ratingFrom);
        } else if ((query != null && !query.isEmpty()) && genre != null && releaseYear == null && ratingFrom == null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(genreURL).append(genre);
        } else if ((query != null && !query.isEmpty()) && genre == null && releaseYear != null && ratingFrom == null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(releaseYearURL).append(releaseYear);
        } else if ((query != null && !query.isEmpty()) && genre == null && releaseYear == null && ratingFrom != null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query).append("&");
            urlBuild.append(ratingFromURL).append(ratingFrom);
        } else if ((query != null && !query.isEmpty()) && genre == null && releaseYear == null && ratingFrom == null) {
            urlBuild.append("?");
            urlBuild.append(queryURL).append(query);
        }
        return urlBuild.toString();
    }

    public static List<Movie> getAllMovies(String query, Genre genre, String releaseYear, String ratingFrom) {
        String urlBuild = buildUrl(query, genre, releaseYear, ratingFrom);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlBuild)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(responseBody, Movie[].class);

            return Arrays.asList(movies);
        }
        catch (Exception e) {
            System.err.println("exception");
            e.printStackTrace(System.err);
        }
        return new ArrayList<>();
    }

    public static List<Movie> getAllMovies(){
        return getAllMovies(null, null, null, null);
    }

    public static List<Integer> getReleaseYears() {
        List<Movie> allMovies = getAllMovies();

        return allMovies.stream()
                .map(Movie::getReleaseYear)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Double> getRating() {
        List<Movie> allMovies = getAllMovies();

        return allMovies.stream()
                .map(Movie::getRating)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
