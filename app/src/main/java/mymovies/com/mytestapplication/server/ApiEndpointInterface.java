package mymovies.com.mytestapplication.server;

import java.util.List;

import mymovies.com.mytestapplication.moviespojo.MoviesResponse;
import mymovies.com.mytestapplication.moviespojo.Result;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpointInterface {
    @FormUrlEncoded
    @GET("movie/now_playing")
    Call<MoviesResponse> getMovies();

    @GET("movie/now_playing")
      Call<MoviesResponse> moviesList(@Query("api_key") String apiKey);

   /* @GET(“movie/top_rated”)
    Call<MovieResponse> getTopRatedMovies(@Query(“api_key”) String apiKey)*/;
   // Call<List<Repo>> listRepos(@Path("user") String user);
//7d748006b65fc56a4285829d1343cc88
//@GET(“group/{id}/users”)
//Call<List<User>> groupList(@Path(“id”) int groupId, @Query(“sort”) String sort);
}