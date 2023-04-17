package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private static HomeController homeController;
    @BeforeAll
    static void init() {
        homeController = new HomeController();
    }

    @Test
    void at_initialization_allMovies_and_observableMovies_should_be_filled_and_equal() {
        homeController.initializeState();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void query_filter_with_null_movie_list_throws_exception() {
        // given
        homeController.initializeState();
        String query = "IfE";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> homeController.filterByQuery(null, query));
    }

    @Test
    void query_filter_with_null_value_returns_unfiltered_list() {
        // given
        homeController.initializeState();
        String query = null;

        // when
        List<Movie> actual = homeController.filterByQuery(homeController.observableMovies, query);

        // then
        assertEquals(homeController.observableMovies, actual);
    }

    @Test
    void genre_filter_with_null_value_returns_unfiltered_list() {
        // given
        homeController.initializeState();
        Genre genre = null;

        // when
        List<Movie> actual = homeController.filterByGenre(homeController.observableMovies, genre);

        // then
        assertEquals(homeController.observableMovies, actual);
    }

    @Test
    void genre_filter_returns_all_movies_containing_given_genre() {
        // given
        homeController.initializeState();
        Genre genre = Genre.DRAMA;

        // when
        List<Movie> actual = homeController.filterByGenre(homeController.observableMovies, genre);

        // then
        assertEquals(24, actual.size());
    }

    @Test
    void no_filtering_ui_if_empty_query_or_no_genre_or_no_year_or_no_rating() {
        // given
        homeController.initializeState();

        // when
        homeController.applyAllFilters("", null, null,null);

        // then
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void result_of_movies_by_christopher_nolan_should_be_two() {
        //given
        HomeController homeController = new HomeController();
        MovieAPI movieAPI = new MovieAPI();
        List<Movie> movieList = movieAPI.getAllMovies();

        //when
        long moviesCount = homeController.countMoviesFrom(movieList, "Christopher Nolan");

        //then
        assertEquals(2,moviesCount);
    }

    @Test
    void result_of_most_popular_actor_is_tom_hanks() {
        //given
        HomeController homeController = new HomeController();
        MovieAPI movieAPI = new MovieAPI();
        List<Movie> movieList = movieAPI.getAllMovies();

        //when
        String mostPopularActor = homeController.getMostPopularActor(movieList);

        //then
        assertEquals("tom hanks",mostPopularActor);
    }

    @Test
    void result_of_get_longest_Movie_Title_is_46() {
        //given
        HomeController homeController = new HomeController();
        MovieAPI movieAPI = new MovieAPI();
        List<Movie> movieList = movieAPI.getAllMovies();

        //when
        int longestMovieTitle = homeController.getLongestMovieTitle(movieList);

        //then
        assertEquals(46,longestMovieTitle);
    }

    @Test
    void result_of_get_Movies_Between_Years_2012_And_2019_Is_Four() {
        //given
        HomeController homeController = new HomeController();
        MovieAPI movieAPI = new MovieAPI();
        List<Movie> movieList = movieAPI.getAllMovies();

        //when
        List<Movie> getMoviesBetweenYears = homeController.getMoviesBetweenYears(movieList,2012,2019);

        //then
        assertEquals(4,getMoviesBetweenYears.stream().count());
    }
}