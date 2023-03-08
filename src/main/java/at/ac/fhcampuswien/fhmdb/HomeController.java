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
        observableMovies.addAll(allMovies); // adds dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);// set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // GENRE FILTER
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        searchBtn.setOnAction(actionEvent -> { // TODO: DOESN'T WORK, PLEASE FIX!!!
            observableMovies.clear();
            ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(); // creates an ObservableList
            for (Movie movie : allMovies) { // loops trough the list and adds movies with the same genre as set in the combobox
                if (movie.getGenre() == genreComboBox.getValue()) {
                    filteredMovies.add(movie);
                }
            }
            observableMovies = filteredMovies; // copies the filtered list into the main list
            movieListView.setItems(observableMovies); // set data of observable list to list view
        });

        // SEARCH FILTER
        searchField.setOnAction(actionEvent -> { // defines what happens on click
            String searchTerm = searchField.getText();
            movieListView.setItems(searchMovies(observableMovies,searchTerm));
        });

        // SORT BUTTON
        sortBtn.setOnAction(actionEvent -> { // defines what happens on click
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

        //RESET BUTTON
        resetBtn.setOnAction(actionEvent -> { // defines what happens on click
            observableMovies.clear(); // deletes every entry in the list
            observableMovies.addAll(allMovies); // adds dummy data to observable list
            movieListView.setItems(observableMovies); // set data of observable list to list view
        });

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
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