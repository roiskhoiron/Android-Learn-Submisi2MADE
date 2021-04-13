package com.example.submisi2made;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submisi2made.model.Movies;
import com.example.submisi2made.model.TvShows;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_INDEX = "parcel movie";
    public static final String TVSHOW_INDEX = "parcel tvShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvRelease = findViewById(R.id.tv_release);
        TextView tvDirector = findViewById(R.id.tv_director);
        TextView tvOverview = findViewById(R.id.tv_overview);
        ImageView imgvPhoto = findViewById(R.id.imgv_photo);


        Movies movieParcel = getIntent().getParcelableExtra(MOVIE_INDEX);
        TvShows tvShowsParcel = getIntent().getParcelableExtra(TVSHOW_INDEX);

        if (movieParcel != null) {
            getSupportActionBar().setTitle("Movie");
            tvTitle.setText(movieParcel.getTitle());
            tvRelease.setText(movieParcel.getYear());
            tvDirector.setText(movieParcel.getDirector());
            tvOverview.setText(movieParcel.getOverview());
            Glide.with(this)
                    .load(movieParcel.getPhoto())
                    .apply(new RequestOptions().override(100, 140))
                    .into(imgvPhoto);

        } else if (tvShowsParcel != null) {
            getSupportActionBar().setTitle("Tv Show");
            tvTitle.setText(tvShowsParcel.getTitle());
            tvRelease.setText(tvShowsParcel.getYear());
            tvDirector.setText(tvShowsParcel.getDirector());
            tvOverview.setText(tvShowsParcel.getOverview());
            Glide.with(this)
                    .load(tvShowsParcel.getPhoto())
                    .apply(new RequestOptions().override(100, 140))
                    .into(imgvPhoto);

        } else {
            System.out.println("Tidak menerima Parcel");
        }


    }
}
