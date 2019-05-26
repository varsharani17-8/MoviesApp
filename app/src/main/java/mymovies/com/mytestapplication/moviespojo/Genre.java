
package mymovies.com.mytestapplication.moviespojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "Genre")
public class Genre {
   // @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int id;

   // @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
