package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        //Funktioniert nicht, bitte ansehen
        searchBtn.setOnAction(actionEvent -> {
            ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
            for (Movie movie : observableMovies) {
                if (movie.getGenre() == genreComboBox.getValue()) {
                    filteredMovies.add(movie);
                }
            }
            observableMovies = filteredMovies;
            movieListView.setItems(observableMovies);
        });
        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // TODO add search filter
        searchField.setOnAction(actionEvent -> {
            String searchTerm = searchField.getText();
            movieListView.setItems(searchMovies(observableMovies,searchTerm));
        });

        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // sorts observableMovies ascending
                sortBtn.setText("Sort (desc)"); // changes the sort button text
                Comparator<Movie> naturalComparator = Comparator.comparing(Movie::getTitle); // creates a comparator that sorts lists in natural order
                movieListView.setItems(observableMovies.sorted(naturalComparator)); // sorts the list by applying the "naturalComparator" comparator
            } else {
                // sorts observableMovies descending
                sortBtn.setText("Sort (asc)"); // changes the sort button text
                Comparator<Movie> reversedComparator = Collections.reverseOrder(Comparator.comparing(Movie::getTitle)); // creates a comparator that sorts lists in reverse order
                movieListView.setItems(observableMovies.sorted(reversedComparator)); // reverses the list order by applying the "reversedComparator" comparator
            }
        });

        resetBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
            movieListView.setItems(observableMovies);
        });
    }

    public static ObservableList<Movie> searchMovies(ObservableList<Movie> movies,String searchTerm)
    {
        ObservableList<Movie> searchResults = FXCollections.observableArrayList();
        for (Movie mov: movies)
        {
            if (mov.getTitle().contains(searchTerm) || mov.getDescription().contains(searchTerm))
            {
                searchResults.add(mov);
            }
        }
        return searchResults;
    }
}