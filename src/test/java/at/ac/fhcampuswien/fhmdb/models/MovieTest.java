package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MovieTest {
    @Test
    void test_movie_title() {
        // Given
        Movie movie = new Movie("The Matrix", "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");

        // When
        String expectedMovieTitle = "The Matrix";

        // Then
        assertEquals(expectedMovieTitle, movie.getTitle());
    }

    @Test
    void test_movie_description() {
        // Given
        Movie movie = new Movie("The Matrix", "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");

        // When
        String expectedMovieDescription = "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.";

        // Then
        assertEquals(expectedMovieDescription, movie.getDescription());
    }

    @Test
    void test_movie_list_of_genres() {
        // Given
        Movie movie = new Movie("The Matrix", "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.");

        // When
        List<Genre> expectedMovieGenres = List.of(Genre.values());

        // Then
        assertEquals(expectedMovieGenres, movie.getGenres());
    }

    @Test
    void test_movie_size() {
        // Given
        List<Movie> movies = Movie.initializeMovies();

        // When
        int expectedMovieSize = 6;

        // Then
        assertEquals(expectedMovieSize, movies.size());
    }

    @Test
    void test_if_first_movie_title_is_The_Matrix() {
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
    void test_first_movie_genre() {
        // Given
        List<Movie> movies = Movie.initializeMovies();

        // When
        String expectedMovieGenres = "ACTION";

        // Then
        assertEquals(expectedMovieGenres, movies.get(0).getGenres().get(0).toString());
    }


}