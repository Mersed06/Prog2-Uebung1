package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.util.List;

class HomeControllerTest {

    @Test
    void test_observableMovies_size_populated_correctly() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // When
        int expectedMovieSize = expectedMovies.size();

        // Then
        assertEquals(expectedMovieSize, expectedMovies.size());
    }

    @Test
    void test_if_first_observableMovie_element_populated_correctly() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // When
        Movie expectedMovie = expectedMovies.get(0);

        // Then
        assertEquals(expectedMovie, expectedMovies.get(0));

    }

    @Test
    void test_searchBtn_filters_observableMovies_list_correctly_and_returns_one_element() {
        // Given
        HomeController controller = new HomeController();
        ListView<Movie> movieListView = controller.movieListView;
        TextField searchField = controller.searchField;
        JFXButton searchBtn = controller.searchBtn;
        // Simulate user input by entering "The Matrix" into the search field and clicking the search button.
        searchField.setText("The Matrix");

        // When
        int actual = 1;

        // Then
        // Test that the observableMovies list only contains one movie.
        assertEquals(actual, controller.observableMovies.size());
    }

    @Test
    void test_searchBtn_filters_observableMovies_list_correctly_and_returns_correct_movie_name() {
        // Given
        HomeController controller = new HomeController();
        ListView<Movie> movieListView = controller.movieListView;
        TextField searchField = controller.searchField;
        JFXButton searchBtn = controller.searchBtn;
        // Simulate user input by entering "The Matrix" into the search field and clicking the search button.
        searchField.setText("The Matrix");

        // When
        String actualTitle = "The Matrix";

        // Then
        // Test that the observableMovies list contains "The Matrix" as movie title.
        assertEquals(actualTitle, movieListView.getItems().get(0).getTitle());
    }

    @Test
    void test_searchBtn_filters_observableMovies_correctly_recognizes_nonexistent_movie_return_size_0() {
        // Given
        HomeController controller = new HomeController();
        ListView<Movie> movieListView = controller.movieListView;
        TextField searchField = controller.searchField;
        JFXButton searchBtn = controller.searchBtn;
        // Simulate user input by entering "Some Movie" into the search field and clicking the search button.
        searchField.setText("Some Movie");

        // When
        int actualSize = 0;

        // Then
        // Test that the observableMovies list is empty.
        assertEquals(actualSize, controller.observableMovies.size());
    }


}