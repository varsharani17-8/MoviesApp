package mymovies.com.mytestapplication.movies;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mymovies.com.mytestapplication.R;
import mymovies.com.mytestapplication.moviespojo.Result;
/**
 * Created by infedis3 on 25/9/18.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.DirViewHolder> {
    Context context;
    List<Result> item;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    ProgressDialog dialog;
    public MoviesAdapter(@NonNull Context context, List<Result> item) {
        super();
        this.item=item;
        this.context=context;
        dialog=new ProgressDialog(context);
        dialog.setCancelable(false);
    }
    @NonNull
    @Override
    public DirViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_movies, viewGroup, false);
        int height = viewGroup.getMeasuredHeight() / 4;
        view.setMinimumHeight(height);
        return new DirViewHolder(view);
    }
    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd MMM";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
    @Override
    public void onBindViewHolder(@NonNull DirViewHolder holder, final  int position) {
        Glide.with(context).load(IMAGE_URL_BASE_PATH+item.get(position).getPosterPath()).into(holder.movie_img);
        holder.txt_Title.setText(item.get(position).getTitle());
        holder.ratings.setText(item.get(position).getVoteAverage()+"");
        holder.date.setText(item.get(position).getReleaseDate());
        holder.row_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,MovieDetailsActivity.class);
                intent.putExtra("movieObj",item.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public void onBindViewHolder(@NonNull DirViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
    class  DirViewHolder extends RecyclerView.ViewHolder {
         RelativeLayout row_movie;
         TextView txt_Title,ratings,date;
         ImageView movie_img,img_record_pdf;
         ProgressBar progressBar;

        public DirViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            row_movie= itemView.findViewById(R.id.row_movie);
            ratings= itemView.findViewById(R.id.ratings);
            txt_Title= itemView.findViewById(R.id.txt_Title);
            movie_img= itemView.findViewById(R.id.movie_img);
        }
    }


}
