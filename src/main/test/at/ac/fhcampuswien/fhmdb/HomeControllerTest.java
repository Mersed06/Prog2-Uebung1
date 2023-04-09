package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

class HomeControllerTest {
    private ObservableList<Movie> movieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private HomeController controller;

    @BeforeEach
    public void setUp() {
        movieList = FXCollections.observableArrayList();
        movie1 = new Movie("The Matrix", "A hacker learns about the true nature of reality.", 1999, 8.7);
        movie1.addMovieGenre(Genre.ACTION);
        movie1.addMovieGenre(Genre.SCIENCE_FICTION);
        movie2 = new Movie("The Terminator", "A cyborg assassin is sent back in time to kill a woman.", 1984, 8.1);
        movie2.addMovieGenre(Genre.ACTION);
        movie2.addMovieGenre(Genre.SCIENCE_FICTION);
        movie2.addMovieGenre(Genre.HORROR);
        movie3 = new Movie("The Lion King", "A young lion prince runs away after his father's murder.", 2019, 6.8);
        movie3.addMovieGenre(Genre.ADVENTURE);
        movie3.addMovieGenre(Genre.DRAMA);
        movieList.addAll(movie1, movie2, movie3);

        controller = new HomeController();
    }

    @Test
    void test_searchMovies_number_of_titles_is_1() {
        // Given
        ObservableList<Movie> searchResults = HomeController.searchMovies(movieList, "Matrix");

        // When
        int expectedSize = 1;

        // Then
        assertEquals(expectedSize, searchResults.size());
    }

    @Test
    void test_searchMovie_returns_correct_movie() {
        // Given
        ObservableList<Movie> searchResults = HomeController.searchMovies(movieList, "Matrix");

        // When
        Movie expectedMovie = movie1;

        // Then
        assertEquals(expectedMovie, searchResults.get(0));
    }

    @Test
    void test_searchMovie_by_title() {
        // Given
        ObservableList<Movie> searchResults = HomeController.searchMovies(movieList, "Terminator");

        // When
        String expectedTitle = "The Terminator";

        // Then
        assertEquals(expectedTitle, searchResults.get(0).getTitle());
    }

    @Test
    void test_searchMovie_by_description() {
        // Given
        ObservableList<Movie> searchResults = HomeController.searchMovies(movieList, "cyborg");

        // When
        Movie expectedMovie = movie2;

        // Then
        assertEquals(expectedMovie, searchResults.get(0));
    }

    @Test
    void test_searchMovie_recognizes_nonexistent_movie_and_returns_size_0() {
        // Given
        ObservableList<Movie> searchResults = HomeController.searchMovies(movieList, "Star Wars");

        // When
        int expectedSize = 0;

        // Then
        assertEquals(expectedSize, searchResults.size());
    }

    @Test
    void test_resetMovies_size_is_0() {
        // Given
        List<Movie> allMovies = new ArrayList<>();
        HomeController.resetMovies(movieList, allMovies);

        // When
        int expectedMovieSize = 0;

        // Then
        assertEquals(expectedMovieSize, movieList.size());
    }

    @Test
    void test_resetMovies_size_is_not_empty() {
        // Given
        List<Movie> allMovies = Movie.initializeMovies();
        movieList = FXCollections.observableArrayList(allMovies);
        HomeController.resetMovies(movieList, allMovies);

        // When
        int allMoviesSize = allMovies.size();

        // Then
        assertEquals(allMoviesSize, movieList.size());
    }

    @Test
    void test_resetMovies_throws_NullPointerException_if_movieList_is_null() {
        // Given
        List<Movie> allMovies = new ArrayList<>();

        // When
        movieList = null;

        // Then
        assertThrows(NullPointerException.class, () -> {
            HomeController.resetMovies(movieList, allMovies);
        });
    }

    @Test
    void test_resetMovies_returns_exception_message_is_movieList_is_null() {
        // Given
        List<Movie> allMovies = Movie.initializeMovies();
        ObservableList<Movie> movieList = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            HomeController.resetMovies(movieList, allMovies);
        });

        // When
        String expectedMessage = "\"movieList\" is null";
        String actualMessage = exception.getMessage();

        // Then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_resetMovies_throws_NullPointerException_if_allMovies_is_null() {
        // Given
        List<Movie> allMovies = null;

        // Then
        assertThrows(NullPointerException.class, () -> {
            HomeController.resetMovies(movieList, allMovies);
        });
    }

    @Test
    void test_observableMovies_size_is_1() {
        // Given
        controller = new HomeController();
        controller.observableMovies.add(0, new Movie("abc","test", 2005, 8.2));

        // When
        int expected = 1;

        // Then
        assertEquals(expected, controller.observableMovies.size());  // Test that the observableMovies list only contains one movie.
    }

    @Test
    void test_observableMovies_first_movie_title_is_The_Matrix() {
        // Given
        controller = new HomeController();
        controller.observableMovies.add(0, new Movie("The Matrix", "Test", 1999, 8.7));

        // When
        String expectedTitle = "The Matrix";

        // Then
        assertEquals(expectedTitle, controller.observableMovies.get(0).getTitle());
    }

    @Test
    void test_empty_observableMovies_returns_size_0() {
        // Given
        controller = new HomeController();

        // When
        int expectedSize = 0;
        // Then
        assertEquals(expectedSize, controller.observableMovies.size());
    }
}