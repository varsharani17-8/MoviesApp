package mymovies.com.mytestapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mymovies.com.mytestapplication.moviespojo.Genre;
import mymovies.com.mytestapplication.moviespojo.Result;

@Database(entities = {Result.class}, version = 1)
public  abstract class MyDB extends RoomDatabase {

    public abstract DaoInterface daoAccess();
    }




