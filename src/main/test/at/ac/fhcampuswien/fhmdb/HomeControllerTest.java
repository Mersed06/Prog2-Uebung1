package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;


class HomeControllerTest {

    @Test
    void test_observableMovies_size_is_1() {
        // Given
        HomeController controller = new HomeController();
        controller.observableMovies.add(0,new Movie("abc","test"));

        // When
        int expected = 1;

        // Then
        assertEquals(expected, controller.observableMovies.size());  // Test that the observableMovies list only contains one movie.
    }

    @Test
    void test_observableMovies_first_movie_title_is_The_Matrix() {
        // Given
        HomeController controller = new HomeController();
        controller.observableMovies.add(0,new Movie("The Matrix", "Test"));

        // When
        String expectedTitle = "The Matrix";

        // Then
        assertEquals(expectedTitle, controller.observableMovies.get(0).getTitle());
    }

    @Test
    void test_empty_observableMovies_returns_size_0() {
        // Given
        HomeController controller = new HomeController();

        // When
        int expectedSize = 0;

        // Then
        assertEquals(expectedSize, controller.observableMovies.size());
    }
}