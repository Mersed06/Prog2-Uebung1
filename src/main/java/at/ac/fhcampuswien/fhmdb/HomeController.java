package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
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

    public List<Movie> allMovies = Movie.initializeMovies(); // creates an ArrayList and fills it with the dummy data

    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList(); // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // DUMMY DATA -> OBSERVABLE LIST
        observableMovies.addAll(allMovies); // inserts all movies into the observableMovies list

        // UI INITIALIZE
        movieListView.setItems(observableMovies); // sets data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // GENRE COMBOBOX
        genreComboBox.setPromptText("Filter by Genre"); // sets the default value (which is not an ordinary combobox option)
        genreComboBox.getItems().addAll(Genre.values()); // adds all enums from the genre class

        // SORT BUTTON
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) { // sorts observableMovies ascending
                sortBtn.setText("Sort (desc)"); // changes the sort button text
                movieListView.setItems(observableMovies.sorted(Comparator.comparing(Movie::getTitle))); // sorts the list by applying the "naturalComparator" comparator
            } else { // sorts observableMovies descending
                sortBtn.setText("Sort (asc)"); // changes the sort button text
                movieListView.setItems(observableMovies.sorted(Collections.reverseOrder(Comparator.comparing(Movie::getTitle)))); // reverses the list order by applying the "reversedComparator" comparator
            }
        });

        // SEARCH FIELD
        searchField.setOnAction(actionEvent -> {
            String searchTerm = searchField.getText(); // gets the text from the search field
            movieListView.setItems(searchMovies(observableMovies,searchTerm)); // uses the search method to query trough strings
        });

        // GENRE FILTER BUTTON
        searchBtn.setOnAction(actionEvent -> {
            movieListView.setItems(filterMovies(observableMovies, genreComboBox)); // filters the observable list to the set combobox value
        });

        // RESET BUTTON
        resetBtn.setOnAction(actionEvent -> {
            observableMovies.clear(); // deletes every entry from the observableMovies list
            observableMovies.addAll(allMovies); // inserts all movies onto the observableMovies list
            movieListView.setItems(observableMovies); // sets data of observable list to list view
            searchField.clear(); // deletes text (if written any) from the search field
            genreComboBox.setValue(null); // sets the combobox to default value
        });
    }

    // SEARCH METHOD
    public static ObservableList<Movie> searchMovies(ObservableList<Movie> movieList,String searchTerm) {
        ObservableList<Movie> searchResultList = FXCollections.observableArrayList(); // creates a new ObservableList
        for (Movie movie: movieList) {
            if (movie.getTitle().contains(searchTerm) || // searches in the movie titles case-sensitive
                    movie.getTitle().contains(searchTerm.toLowerCase()) || // searches in the movie titles in lower case
                    movie.getDescription().contains(searchTerm) || // searches in the movie description case-sensitive
                    movie.getDescription().contains(searchTerm.toLowerCase())) { // searches in the movie description in lower case
                searchResultList.add(movie); // adds movies to the searchResults list that fulfill the if criteria
            }
        }
        return searchResultList;
    }

    // FILTER METHOD
    public static ObservableList<Movie> filterMovies(ObservableList<Movie>movieList, ComboBox genreBox) {
        ObservableList<Movie> filterResultList = FXCollections.observableArrayList(); // creates a new ObservableList
        for (Movie movie : movieList) {
            if (movie.getGenreList().contains(genreBox.getValue())) { // checks if the genre list from the movie contains the current set genreBox genre
                filterResultList.add(movie); // adds movies to the filterResultList that fulfill the if criteria
            }
        }
        return filterResultList;
    }
}