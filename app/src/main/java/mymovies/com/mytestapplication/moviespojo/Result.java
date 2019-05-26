
package mymovies.com.mytestapplication.moviespojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Result")
public class Result implements Serializable {
    @PrimaryKey
    @ColumnInfo(name="id")
    @SerializedName("id")
    @Expose
    private int id;

    @ColumnInfo(name="vote_count")
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    @ColumnInfo(name="video")
    @SerializedName("video")
    @Expose
    private boolean video;
    @ColumnInfo(name="vote_average")
    @SerializedName("vote_average")
    @Expose
    private float voteAverage;
    @ColumnInfo(name="title")
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo(name="popularity")
    @SerializedName("popularity")
    @Expose
    private float popularity;
    @ColumnInfo(name="poster_path")
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @ColumnInfo(name="original_language")
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @ColumnInfo(name="original_title")
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
     @Ignore
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @ColumnInfo(name="backdrop_path")
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @ColumnInfo(name="adult")
    @SerializedName("adult")
    @Expose
    private boolean adult;

    @ColumnInfo(name="overview")
    @SerializedName("overview")
    @Expose
    private String overview;

    @ColumnInfo(name="release_date")
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
