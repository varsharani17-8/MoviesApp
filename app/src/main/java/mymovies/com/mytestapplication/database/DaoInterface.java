package mymovies.com.mytestapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mymovies.com.mytestapplication.moviespojo.Result;


@Dao
public interface DaoInterface {
    @Query("SELECT * FROM Result")
    List<Result> getAll();
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Result>results);

    @Query("SELECT * FROM Result ORDER BY vote_average DESC")
    List<Result> getAllByRating();

    @Query("SELECT * FROM Result ORDER BY release_date DESC")
    List<Result> getAllByDate();
}