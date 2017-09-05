package exampleprilognew.ru.films.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable {

    private List<Film> results = new ArrayList<>();

    public List<Film> getResults() {
        return results;
    }

    public Cinema(List<Film> results) {
        this.results = results;
    }

public static class Film implements Serializable {

    private String poster_path;
    private String title;
    private String release_date;
    private String overview;
    private Double vote_average;
    private String backdrop_path;

    public Film(String poster_path, String title, String release_date, String overview, Double vote_average,String backdrop_path) {
        this.poster_path = poster_path;
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.vote_average = vote_average;
        this.backdrop_path=backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}

}
