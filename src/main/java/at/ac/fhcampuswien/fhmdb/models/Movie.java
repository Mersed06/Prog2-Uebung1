package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    private int releaseYear;
    private double rating;
    private final String id;
    private final String imgUrl;

    private final int lengthInMinutes;

    private final List<String> directors = new ArrayList<>();

    private final List<String> writers = new ArrayList<>();

    private final List<String> mainCast = new ArrayList<>();

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = 0;
        this.imgUrl = null;
        this.lengthInMinutes = 0;
        this.rating = 0;
        this.id = "";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Movie other)) {
            return false;
        }
        return this.title.equals(other.title) && this.description.equals(other.description) && this.genres.equals(other.genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenreList() { return genres; }

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }



    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(
                "The Matrix",
                "The Matrix is a computer-generated dream world designed to keep these humans under control. Humans are kept sedated, effectively living a virtual life. Neo awakens in a bed back on Morpheus's ship, and Morpheus further explains that one man was born into the Matrix with the power to change anything in it.",
                Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.FANTASY)
        ));

        movies.add(new Movie(
                "Avatar",
                "To explore Pandora, genetically matched human scientists use Na'vi-human hybrids called \"avatars.\" Paraplegic Marine Jake Sully is sent to Pandora to replace his deceased identical twin, who had signed up to be an operator.",
                Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.FANTASY, Genre.MYSTERY)
        ));

        movies.add(new Movie(
                "The Dark Knight Rises",
                "Eight years after the Joker's reign of chaos, Batman is coerced out of exile with the assistance of the mysterious Selina Kyle in order to defend Gotham City from the vicious guerrilla terrorist Bane",
                Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.THRILLER, Genre.CRIME)
        ));

        movies.add(new Movie(
                "Spider-Man",
                "After being bitten by a genetically-modified spider, a shy teenager gains spider-like abilities that he uses to fight injustice as a masked superhero and face a vengeful enemy.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION, Genre.FANTASY)
        ));

        movies.add(new Movie(
                "Batman",
                "Batman was originally introduced as a ruthless vigilante who frequently killed or maimed criminals, but evolved into a character with a stringent moral code and strong sense of justice.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME, Genre.MYSTERY, Genre.DRAMA)
        ));

        movies.add(new Movie(
                "The Intouchables",
                "The Intouchables tells the true story of a wealthy, physically disabled risk taker, the picture of established French nobility, who lost his wife in an accident and whose world is turned upside down when he hires a young, good-humored, black Muslim ex-con as his caretaker.",
                Arrays.asList(Genre.COMEDY, Genre.DRAMA)
        ));

        movies.add(new Movie(
                "Up",
                "78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.",
                Arrays.asList(Genre.COMEDY)
        ));

        movies.add(new Movie(
                "Chucky",
                "A single mother gives her son a much sought-after doll for his birthday, only to discover that it is possessed by the soul of a serial killer.",
                Arrays.asList(Genre.DRAMA, Genre.HORROR, Genre.COMEDY)
        ));

        movies.add(new Movie(
                "Django Unchained",
                "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.DRAMA)
        ));

        movies.add(new Movie(
                "E.T. the Extra-Terrestrial",
                "A troubled child summons the courage to help a friendly alien escape from Earth and return to his home planet.",
                Arrays.asList(Genre.ADVENTURE, Genre.SCIENCE_FICTION, Genre.FAMILY)
        ));

        movies.add(new Movie(
                "Finding Nemo",
                "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.",
                Arrays.asList(Genre.ADVENTURE, Genre.COMEDY, Genre.DRAMA)
        ));

        movies.add(new Movie(
                "No Country for Old Men",
                "Violence and mayhem ensue after a hunter stumbles upon a drug deal gone wrong and more than two million dollars in cash near the Rio Grande.",
                Arrays.asList(Genre.THRILLER, Genre.CRIME, Genre.ADVENTURE, Genre.DRAMA, Genre.MYSTERY)
        ));

        movies.add(new Movie(
                "Zathura",
                "Two young brothers are drawn into an intergalactic adventure when their house is hurled through the depths of space by the magical board game they are playing.",
                Arrays.asList(Genre.ADVENTURE, Genre.SCIENCE_FICTION, Genre.FAMILY)
        ));

        movies.add(new Movie(
                "Wargames",
                "Ultimate cold war hacker fantasy disguised as a teen movie. Computer whiz kid hacks into the US military's nuclear arsenal control site and decides that a showdown with Russia would be enormous fun. Matthew Broderick and Ally Sheedy lend Brat Pack credibility.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER)
        ));

        movies.add(new Movie(
                "Rheingold",
                "Born as Giwar Hajabi in Iran, rapper and music producer Xatar can look back on an eventful life. His parents are Iranian Kurds and fled with him to Germany via Iraq in the mid-1980s. His father and mother are musicians. They want Giwar to learn to play the piano. He grew up in poor conditions in a social housing estate in Bonn. After his father gets a job as a conductor, he leaves his wife and children. In order to get money and fame, Giwar drifts into petty crime. From copying porn onto VHS tapes to selling narcotics",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE)
        ));

        movies.add(new Movie(
                "Despicable Me",
                "The revolutionary minions: Kevin- the leader, Stuart- the rebel and Bob- their lovable little brother left the \"Minion Tribes\"- which have served their ex-masters, from the famous dinosaur T-Rex to Napoleon the Great; and went seeking for a new \"master\". Finally, their journey ends in London after being recruited by a female super villain named Scarlet Overkill and her husband Herb in Orlando, who wanted to rule the world. Now, the three minions must face a new challenge: saving all of the Minion tribes from a huge plan of Minion annihilation.",
                Arrays.asList(Genre.FAMILY)
                ));

        movies.add(new Movie(
                "The Fast and the Furious: Tokyo Drift",
                "THE FAST AND THE FURIOUS: TOKYO DRIFT is the story of Sean, a high schooler with a Texas drawl, who pulls one illegal racing stunt too many. His mom ships Sean off to live with his estranged father in Tokyo, where he’s immediately caught up in the Tokyo underworld of drift racing. Sean’s entanglement in mobster affairs sets up numerous opportunities for him to race his way out of peril, but the plot and characters are really just excuses to hear tires shriek, see sleek cars go fast and watch macho boys test their prowess and manhood behind the wheel.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE)
        ));

        movies.add(new Movie(
                "American Sniper",
                "Navy S.E.A.L. sniper Chris Kyle's pinpoint accuracy saves countless lives on the battlefield and turns him into a legend. Back home with his family after four tours of duty, however, Chris finds that it is the war he can't leave behind.",
                Arrays.asList(Genre.ACTION, Genre.CRIME)
                ));

        return movies;
    }
}
