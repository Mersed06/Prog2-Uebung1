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

    public List<Movie> allMovies = Movie.initializeMovies();

    private final MovieAPI movieAPI = new MovieAPI();

    ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

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
}