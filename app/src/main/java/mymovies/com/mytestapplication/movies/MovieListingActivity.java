package mymovies.com.mytestapplication.movies;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mymovies.com.mytestapplication.R;
import mymovies.com.mytestapplication.database.MyDB;
import mymovies.com.mytestapplication.moviespojo.MoviesResponse;
import mymovies.com.mytestapplication.moviespojo.Result;
import mymovies.com.mytestapplication.server.ApiManager;
import mymovies.com.mytestapplication.utility.Constants;
import mymovies.com.mytestapplication.utility.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListingActivity extends AppCompatActivity {
    @BindView(R.id.txt_sort)
    TextView txt_sort;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sort_by)
    ImageButton sort_by;
    @BindView(R.id.recy)
    RecyclerView recy;
   // private String DB_NAME = "db_task";
    Call<MoviesResponse> moviesCall;
    MoviesAdapter moviesAdapter;
    List<Result> moviesList;
      MyDB myDB;
      boolean sortedByDate=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_listing);
        ButterKnife.bind(this);
        init();
    }
      void initDB(){
          myDB = Room.databaseBuilder(getApplicationContext(), MyDB.class, Constants.DB_NAME).build();
      }
    void init() {
        initDB();
        toolbar.setTitleMargin(14,14,14,14);
        toolbar.setTitle("Movies");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
       // getSupportActionBar().setIcon(R.drawable.star_white);

        recy.setLayoutManager(new LinearLayoutManager(MovieListingActivity.this));
        recy.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(MovieListingActivity.this, moviesList);
        if(NetworkUtils.isNetworkAvailable(MovieListingActivity.this)) {
            getMoviesFromServer();
        }else {
           getdataFromDB();
        }
    }

    void getdataFromDB(){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    moviesList=  myDB.daoAccess().getAllByRating();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    moviesAdapter = new MoviesAdapter(MovieListingActivity.this, moviesList);
                    recy.setAdapter(moviesAdapter);
                }
            }.execute();
        }
        void saveDataToDB(final List<Result> moviesList){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    myDB.daoAccess().insertAll(moviesList);

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    sortByRating();
                }
            }.execute();
        }
    void getMoviesFromServer() {
        moviesCall = ApiManager.getService().moviesList(Constants.API_KEY);
        moviesCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    moviesList = response.body().getResults();
                   // moviesAdapter = new MoviesAdapter(MovieListingActivity.this, moviesList);
                   // recy.setAdapter(moviesAdapter);
                    if(moviesList!=null) {
                        saveDataToDB(moviesList);
                    }
                }else{
                    Toast.makeText(MovieListingActivity.this,"Oops! Something went wrong", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(MovieListingActivity.this,"Oops! Something went wrong", Toast.LENGTH_SHORT);
            }
        });
    }
    void sortByRating(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                moviesList=  myDB.daoAccess().getAllByRating();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                sortedByDate=false;
                txt_sort.setText("Sorted by ratings");
                sort_by.setImageResource(R.drawable.star_white);
                moviesAdapter = new MoviesAdapter(MovieListingActivity.this, moviesList);
                recy.setAdapter(moviesAdapter);
            }
        }.execute();
    }
       void sortbyDate(){
           new AsyncTask<Void, Void, Void>() {
               @Override
               protected Void doInBackground(Void... voids) {
                   moviesList=  myDB.daoAccess().getAllByDate();
                   return null;
               }

               @Override
               protected void onPostExecute(Void aVoid) {
                   super.onPostExecute(aVoid);
                   sortedByDate=true;
                   txt_sort.setText("Sorted by release date");
                   sort_by.setImageResource(R.drawable.date_white);
                   moviesAdapter = new MoviesAdapter(MovieListingActivity.this, moviesList);
                   recy.setAdapter(moviesAdapter);
               }
           }.execute();
       }
    @OnClick(R.id.sort_by)
    void sort_byClick(){
        if(!sortedByDate) {
            sortbyDate();

        }else {
            sortByRating();
        }
    }

}
