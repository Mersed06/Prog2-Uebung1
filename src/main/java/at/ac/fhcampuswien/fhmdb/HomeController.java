package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static at.ac.fhcampuswien.fhmdb.models.Movie.initializeMovies;
import static java.lang.Long.parseLong;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton resetBtn;

    public List<Movie> allMovies = Movie.initializeMoviesWithoutParameter();

    private final MovieAPI movieAPI = new MovieAPI();
    public JFXComboBox ComboBoxReleaseYear;
    public JFXComboBox ComboBoxRating;

    ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    public HomeController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // DUMMY DATA -> OBSERVABLE LIST
        observableMovies.addAll(allMovies);

        // UI INITIALIZE
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell());

        // GENRE COMBOBOX
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        // FILTER BY RELEASE YEAR
        ComboBoxRating.setPromptText("Filter by Rating");
        for (int i = 0; i <= 10 ; i += 0.1) {
            ComboBoxRating.getItems().add(i);
        }

        // FILTER BY RATING
        ComboBoxReleaseYear.setPromptText("Filter by Release Year");
        for (int i = 2023; i > 1915 ; i++) {
            ComboBoxReleaseYear.getItems().add(i);
        }


        // SORT BUTTON
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
                movieListView.setItems(observableMovies.sorted(Comparator.comparing(Movie::getTitle)));
            } else {
                sortBtn.setText("Sort (asc)");
                movieListView.setItems(observableMovies.sorted(Collections.reverseOrder(Comparator.comparing(Movie::getTitle))));
            }
        });

        // SEARCH FIELD
        searchField.setOnAction(actionEvent -> {
            String searchTerm = searchField.getText();
            if (searchTerm != "")
            {
                MovieAPI.deleteURLParameter("query");
                MovieAPI.addURLParameter("query",searchField.getText());
                System.out.println(MovieAPI.getUrlBuilder());
            }
            if (searchTerm == null)
            {
                MovieAPI.deleteURLParameter("query");
                System.out.println(MovieAPI.getUrlBuilder());
            }
        });

        // FILTER BUTTON
        searchBtn.setOnAction(actionEvent -> {
            resetMovies(observableMovies, allMovies);
            String searchTerm = searchField.getText();

            observableMovies = searchMovies(observableMovies,searchTerm);
            observableMovies = filterMovies(observableMovies, genreComboBox);
            movieListView.setItems(observableMovies);
        });

        // RESET BUTTON
        resetBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
            movieListView.setItems(observableMovies);
            searchField.clear();
            genreComboBox.setValue(null);
        });
    }

    // SEARCH METHOD
    public static ObservableList<Movie> searchMovies(ObservableList<Movie> movieList, String searchTerm) {
        ObservableList<Movie> searchResultList = FXCollections.observableArrayList();
        for (Movie movie: movieList) {
            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
            movie.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResultList.add(movie);
            }
        }
        return searchResultList;
    }

    // FILTER METHOD
    public static ObservableList<Movie> filterMovies(ObservableList<Movie> movieList, ComboBox genreBox) {
        ObservableList<Movie> filterResultList = FXCollections.observableArrayList();
        for (Movie movie :movieList ) {
            if (movie.getGenreList().contains(genreBox.getValue())) {
                filterResultList.add(movie);
            }  else if (genreBox.getValue() == null) {
                return movieList;
            }
        }
        return filterResultList;
    }

    public static void resetMovies(ObservableList<Movie> movieList, List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
    }


    String getMostPopularActor(List<Movie> movies) {

        Map<Object, Long> actorCounts = movies.stream()
                .flatMap(movie -> movie.getMainCast().toList().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional<Long> maxCount = actorCounts.values().stream().max(Long::compare);

        String mostPopularActor = actorCounts.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxCount.orElse(null)))
                .map(entry -> (String) entry.getKey())
                .collect(Collectors.joining(", "));

        return mostPopularActor;
    }

    int getLongestMovieTitle(List<Movie> movies) {
        OptionalInt longestTitleLength = movies.stream()
                .mapToInt(movie -> movie.getTitle().replaceAll("\s", "").length())
                .max();

        return longestTitleLength.orElse(0);
    }

    // needs to be fixed
    public long countMoviesFrom(List<Movie> movies, String director) {
    return movies.stream()
            .filter(movie -> movie.getDirectors().contains(director))
            .count();
}
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }

}