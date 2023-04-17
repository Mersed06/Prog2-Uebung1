package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
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
    @FXML
    public JFXButton filterBtn;
    @FXML
    public JFXComboBox releaseYearComboBox;
    @FXML
    public JFXComboBox ratingComboBox;
    public List<Movie> allMovies;
    protected ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    protected SortedState sortedState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();

        //SORT BUTTON
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
                movieListView.setItems(observableMovies.sorted(Comparator.comparing(Movie::getTitle)));
            } else {
                sortBtn.setText("Sort (asc)");
                movieListView.setItems(observableMovies.sorted(Collections.reverseOrder(Comparator.comparing(Movie::getTitle))));
            }
        });

        // RESET BUTTON
        resetBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
            movieListView.setItems(observableMovies);
            searchField.clear();
            releaseYearComboBox.setValue(null);
            genreComboBox.setValue(null);
            ratingComboBox.setValue(null);
        });

        // FILTER BUTTON
        filterBtn.setOnAction(actionEvent -> {
            String searchQuery = searchField.getText().trim().toLowerCase();
            Object genre = genreComboBox.getSelectionModel().getSelectedItem();
            Integer releaseYear = (Integer) releaseYearComboBox.getSelectionModel().getSelectedItem();
            Double rating = (Double) ratingComboBox.getSelectionModel().getSelectedItem();

            applyAllFilters(searchQuery, genre, releaseYear,rating);

            sortMovies(sortedState);

        });
    }

    public void initializeState() {
        allMovies = MovieAPI.getAllMovies();
        observableMovies.clear();
        observableMovies.addAll(allMovies);
        sortedState = SortedState.NONE;
    }

    public void initializeLayout() {
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell()); // apply custom cells to the listview

        Object[] genres = Genre.values();
        genreComboBox.getItems().add("No filter");
        genreComboBox.getItems().addAll(genres);
        genreComboBox.setPromptText("Filter by Genre");

        releaseYearComboBox.setPromptText("Filter by Release Year");
        releaseYearComboBox.getItems().addAll(MovieAPI.getReleaseYears());

        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.getItems().addAll(MovieAPI.getRating());
    }

    public List<Movie> filterByYear(List<Movie> movies, Integer releaseYear) {
        if (releaseYear == null) {
            return movies;
        }
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() == releaseYear)
                .toList();
    }

    public List<Movie> filterByRating(List<Movie> movies, Double rating) {
        if (rating == null) {
            return movies;
        }
        return movies.stream()
                .filter(movie -> movie.getRating() == rating)
                .toList();
    }

    public void sortMovies() {
        if (sortedState == SortedState.NONE || sortedState == SortedState.DESCENDING) {
            sortMovies(SortedState.ASCENDING);
        } else if (sortedState == SortedState.ASCENDING) {
            sortMovies(SortedState.DESCENDING);
        }
    }

    // sort movies based on sortedState
    // by default sorted state is NONE
    // afterwards it switches between ascending and descending
    public void sortMovies(SortedState sortDirection) {
        if (sortDirection == SortedState.ASCENDING) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
            sortedState = SortedState.ASCENDING;
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortedState = SortedState.DESCENDING;
        }
    }

    public List<Movie> filterByQuery(List<Movie> movies, String query) {
        if (query == null || query.isEmpty()) return movies;
        if (movies == null) {
            throw new IllegalArgumentException("Movies must not be null");
        }

        return movies.stream()
                // :: is the method reference operator, used to call a method by referring to it with the help of its class directly
                .filter(Objects::nonNull)
                .filter(movie ->
                        movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                                movie.getDescription().toLowerCase().contains(query.toLowerCase())
                )
                .toList();
    }

    public List<Movie> filterByGenre(List<Movie> movies, Genre genre) {
        if (genre == null) return movies;
        if (movies == null) {
            throw new IllegalArgumentException("Movies must not be null");
        }
        return movies.stream()
                .filter(Objects::nonNull)
                .filter(movie -> movie.getGenreList().contains(genre))
                .toList();
    }

    //method, where all filterMethods are included
    public void applyAllFilters(String searchQuery, Object genre,Integer releaseYear, Double ratings) {
        List<Movie> filteredMovies = allMovies;
        if (!searchQuery.isEmpty()) {
            filteredMovies = filterByQuery(filteredMovies, searchQuery);
        }
        if (genre != null && !genre.toString().equals("No filter")) {
            filteredMovies = filterByGenre(filteredMovies, Genre.valueOf(genre.toString()));
        }
        if (releaseYear != null) {
            filteredMovies = filterByYear(filteredMovies,releaseYear);
        }
        if (ratings != null) {
            filteredMovies = filterByRating(filteredMovies, ratings);
        }

        if ((searchQuery != null && !searchQuery.isEmpty()) && genre != null && releaseYear != null && ratings != null) {
            String url = MovieAPI.buildUrl(searchQuery, (Genre) genre, releaseYear.toString(), ratings.toString());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre != null && releaseYear != null && ratings == null) {
            String url = MovieAPI.buildUrl(searchQuery, (Genre) genre, releaseYear.toString(), null);
            String newUrl = url.substring(url.indexOf("ratingFrom=") + "ratingFrom=".length());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre != null && releaseYear == null && ratings != null) {
            String url = MovieAPI.buildUrl(searchQuery, (Genre) genre, null, ratings.toString());
            String newUrl = url.substring(url.indexOf("releaseYear=") + "releaseYear=".length());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre == null && releaseYear != null && ratings != null) {
            String url = MovieAPI.buildUrl(searchQuery, null, releaseYear.toString(), ratings.toString());
            String newUrl = url.substring(url.indexOf("genre=") + "genre=".length());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre != null && releaseYear == null && ratings == null) {
            String url = MovieAPI.buildUrl(searchQuery, (Genre) genre, null, null);
            String newUrl = url.substring(url.indexOf("releaseYear=") + "releaseYear=".length());
            String anotherUrl = url.substring(url.indexOf("ratingFrom=") + "ratingFrom=".length());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre == null && releaseYear != null && ratings == null) {
            String url = MovieAPI.buildUrl(searchQuery, null, releaseYear.toString(), null);
            String newUrl = url.substring(url.indexOf("genre=") + "genre=".length());
            String anotherUrl = url.substring(url.indexOf("ratingFrom=") + "ratingFrom=".length());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre == null && releaseYear == null && ratings != null) {
            String url = MovieAPI.buildUrl(searchQuery, null, null, ratings.toString());
            String newUrl = url.substring(url.indexOf("genre=") + "genre=".length());
            String anotherUrl = url.substring(url.indexOf("releaseYear=") + "releaseYear=".length());
            System.out.println(url);
        } else if ((searchQuery != null && !searchQuery.isEmpty()) && genre == null && releaseYear == null && ratings == null) {
            String url = MovieAPI.buildUrl(searchQuery, null, null, null);
            String newUrl = url.substring(url.indexOf("genre=") + "genre=".length());
            String anotherUrl = url.substring(url.indexOf("releaseYear=") + "releaseYear=".length());
            String thirdUrl = url.substring(url.indexOf("ratingFrom=") + "ratingFrom=".length());
            System.out.println(url);
        }

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
    }

    //returns the count of films of a certain author
    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    //returns the person, which is the most often in the main cast role
    public String getMostPopularActor(List<Movie> movies) {
        String mostPopularActor = movies.stream()
                .flatMap(s->s.getMainCast().stream())
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        return mostPopularActor;
    }

    //filters the longest movie title and returns the length of the title
    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .map(Movie::getTitle)
                .mapToInt(String::length)
                .max().orElse(0);
    }

    //returns movie in a certain time period
    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }
}
