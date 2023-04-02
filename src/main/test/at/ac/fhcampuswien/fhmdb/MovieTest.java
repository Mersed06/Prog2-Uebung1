package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void test_getTitle_returns_The_Matrix() {
        // Given
        Movie movie = new Movie("The Matrix", "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");

        // When
        String expectedMovieTitle = "The Matrix";

        // Then
        assertEquals(expectedMovieTitle, movie.getTitle());
    }

    @Test
    void test_getDescription() {
        // Given
        Movie movie = new Movie("The Matrix", "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");

        // When
        String expectedMovieDescription = "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.";

        // Then
        assertEquals(expectedMovieDescription, movie.getDescription());
    }

    @Test
    void test_getGenreList_returns_ACTION() {
        // Given
        Movie movie = new Movie("The Matrix", "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");
        movie.addMovieGenre(Genre.ACTION);

        // When
        String expectedMovieGenre = "[" + Genre.ACTION.toString() + "]";

        // Then
        assertEquals(expectedMovieGenre, movie.getGenreList().toString());
    }

    @Test
    void test_movie_size_is_18() {
        // Given
        List<Movie> movies = Movie.initializeMovies();

        // When
        int expectedMovieSize = 18;

        // Then
        assertEquals(expectedMovieSize, movies.size());
    }

    @Test
    void test_first_movie_title_is_The_Matrix() {
        // Given
        List<Movie> movies = Movie.initializeMovies();

        // When
        String expectedMovieTitle = "The Matrix";

        // Then
        assertEquals(expectedMovieTitle, movies.get(0).getTitle());
    }

    @Test
    void test_first_movie_description() {
        // Given
        List<Movie> movies = Movie.initializeMovies();

        // When
        String expectedMovieDescription = "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.";

        // Then
        assertEquals(expectedMovieDescription, movies.get(0).getDescription());
    }

    @Test
    void test_first_movie_genre_is_ACTION() {
        // Given
        List<Movie> movies = Movie.initializeMovies();

        // When
        String expectedMovieGenres = "ACTION";

        // Then
        assertEquals(expectedMovieGenres, movies.get(0).getGenreList().get(0).toString());
    }

    @Test
    void test_if_added_movie_is_in_list() {
        // Given
        List<Movie> movies = Movie.initializeMovies();
        movies.clear();

        movies.add(0,new Movie("Transformer","An ancient struggle between two Cybertronian races, the heroic Autobots and the evil Decepticons, comes to Earth, with a clue to the ultimate power held by a teenager."));
        // When
        int expectedMoviesInList = 1;

        // Then
        assertEquals(expectedMoviesInList, movies.size());
    }
}