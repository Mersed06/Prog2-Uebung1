package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void test_observableMovies_size_populated_correctly() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // When
        Movie expectedMovieSize = expectedMovies.size()

        // Then
        assertEquals(expectedMovieSize, controller.observableMovies.size());
    }

    @Test
    void test_if_first_observableMovie_element_populated_correctly() {
        // Given
        HomeController controller = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // When
        Movie expectedMovie = expectedMovies.get(0);

        // Then
        assertEquals(expectedMovie, controller.observableMovies.get(0));

    }

    @Test
    void test_searchBtn_filters_observableMovies_list_correctly_and_returns_one_element() {
        // Given
        HomeController controller = new HomeController();
        ListView<Movie> movieListView = controller.movieListView;
        TextField searchField = controller.searchField;
        JFXButton searchBtn = controller.searchBtn;
        // Simulate user input by entering "The Matrix" into the search field and clicking the search button.
        clickOn(searchField).write("The Matrix");
        clickOn(searchBtn);

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
        clickOn(searchField).write("The Matrix");
        clickOn(searchBtn);

        // When
        String actualTitle = "The Matrix";

        // Then
        // Test that the observableMovies list contains "The MAtrix" as movie title.
        assertEquals(actualTitle, controller.observableMovies.get(0).getTitle());
    }

    @Test
    void test_searchBtn_filters_observableMovies_correctly_recognozes_nonexistent_movie_return_size_0() {
        // Given
        HomeController controller = new HomeController();
        ListView<Movie> movieListView = controller.movieListView;
        TextField searchField = controller.searchField;
        JFXButton searchBtn = controller.searchBtn;
        // Simulate user input by entering "Some Movie" into the search field and clicking the search button.
        clickOn(searchField).write("Some Movie");
        clickOn(searchBtn);

        // When
        int actualSize = 0;

        // Then
        // Test that the observableMovies list is empty.
        assertEquals(actualSize, controller.observableMovies.size());
    }


}