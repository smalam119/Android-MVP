package com.smalam.pseudozero.androidmvp.MovieDetail.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.smalam.pseudozero.androidmvp.R;

import butterknife.BindView;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.movie_detail_title) TextView titleTextView;
    @BindView(R.id.movie_detail_tagline_text_view) TextView taglineTextView;
    @BindView(R.id.movie_detail_overview_text_view) TextView overviewTextView;
    @BindView(R.id.movie_detail_runtime_text_view) TextView runtimeTextView;
    @BindView(R.id.movie_detail_genre_text_view) TextView genreTextView;
    @BindView(R.id.movie_detail_vote_avg_text_view) TextView voteAverageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }
}
