package com.smalam.pseudozero.androidmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sayed Mahmudul Alam on 4/3/2017.
 */

public class MovieDetail {
    @SerializedName("genres")
    private List<Genre> genres = new ArrayList<Genre>();
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("runtime")
    private String runtime;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("vote_average")
    private String voteAverage;

    public MovieDetail(List<Genre> genres, String title, String overview, String posterPath, String runtime, String tagline, String voteAverage) {
        this.genres = genres;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.runtime = runtime;
        this.tagline = tagline;
        this.voteAverage = voteAverage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }
}
