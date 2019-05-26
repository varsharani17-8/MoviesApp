package mymovies.com.mytestapplication.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mymovies.com.mytestapplication.R;
import mymovies.com.mytestapplication.moviespojo.Result;

public class MovieDetailsActivity extends AppCompatActivity {
     Result result;
     @BindView(R.id.img_poster)
    ImageView img_poster;
     @BindView(R.id.date)
    TextView date;
    @BindView(R.id.ratings)
    TextView ratings;
    @BindView(R.id.txt_description)
    TextView txt_description;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_release_date)
    TextView txt_release_date;
    @BindView(R.id.org_title)
    TextView org_title;
    @BindView(R.id.org_lng)
    TextView org_lng;
    @BindView(R.id.vote)
    TextView vote;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.rate)
    TextView rate;
    @BindView(R.id.adult)
    TextView adult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        init();
    }
    void init(){
        if(getIntent().getSerializableExtra("movieObj")!=null);{
        result= (Result) getIntent().getSerializableExtra("movieObj");
        if(result!=null){
            setDataToUI();
        }
        }

    }
    void setDataToUI(){
        if(result.isAdult()){
            adult.setText("Yes");
        }else {
            adult.setText("No");
        }
        rate.setText(result.getVoteAverage()+"");
        popularity.setText(result.getPopularity()+"");
        vote.setText(result.getVoteCount()+"");
        org_lng.setText(result.getOriginalLanguage());
        org_title.setText(result.getOriginalTitle());
        txt_release_date.setText(result.getReleaseDate());
        txt_title.setText(result.getTitle());
        txt_description.setText(result.getOverview());
        date.setText(result.getReleaseDate());
        ratings.setText(result.getVoteAverage()+"");
        Glide.with(getApplicationContext()).load(MoviesAdapter.IMAGE_URL_BASE_PATH+result.getPosterPath()).into(img_poster);
    }
}
