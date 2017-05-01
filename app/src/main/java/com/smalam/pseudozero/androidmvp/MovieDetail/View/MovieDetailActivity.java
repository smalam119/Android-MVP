package com.smalam.pseudozero.androidmvp.MovieDetail.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import android.widget.ImageView;
import android.widget.TextView;

import com.smalam.pseudozero.androidmvp.Application.App;
import com.smalam.pseudozero.androidmvp.Constants;
import com.smalam.pseudozero.androidmvp.Dagger.Components.DaggerMovieDetailComponent;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.MovieDetailViewModule;
import com.smalam.pseudozero.androidmvp.Model.MovieDetail;
import com.smalam.pseudozero.androidmvp.MovieDetail.Interface.IMovieDetailContract;
import com.smalam.pseudozero.androidmvp.MovieDetail.Presenter.MovieDetailPresenter;
import com.smalam.pseudozero.androidmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailContract.IView {
    @BindView(R.id.movie_detail_title) TextView titleTextView;
    @BindView(R.id.movie_detail_tagline_text_view) TextView taglineTextView;
    @BindView(R.id.movie_detail_overview_text_view) TextView overviewTextView;
    @BindView(R.id.movie_detail_runtime_text_view) TextView runtimeTextView;
    @BindView(R.id.movie_detail_genre_text_view) TextView genreTextView;
    @BindView(R.id.movie_detail_vote_avg_text_view) TextView voteAverageTextView;
    @BindView(R.id.movie_detail_progress_bar) AVLoadingIndicatorView avLoadingIndicatorView;
    @BindView(R.id.movie_detail_poster_image_view) ImageView posterImageView;

    private int mMovieID = 0;
    @Inject
    MovieDetailPresenter presenter;
    private static String BASE_URL_POSTER =  "http://image.tmdb.org/t/p/w300/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mMovieID = intent.getIntExtra(Constants.KEY_MOVIE_ID,0);

        DaggerMovieDetailComponent.builder()
                .apiServiceComponent(((App) getApplicationContext()).getApiServiceComponent())
                .movieDetailViewModule(new MovieDetailViewModule(this))
                .build().inject(this);

        presenter.getMovieDetailData(mMovieID);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStopAPIService();
    }

    @Override
    public void showLoader() {
        avLoadingIndicatorView.show();
    }

    @Override
    public void hideLoader() {
        avLoadingIndicatorView.hide();
    }

    @Override
    public void onDataFetchedSuccess(Object data) {
        bindData((MovieDetail) data);
    }

    @Override
    public void onDataFetchedError(Object data) {
    }

    private void bindData(MovieDetail movieDetail) {
        titleTextView.setText(movieDetail.getTitle());
        taglineTextView.setText(movieDetail.getTagline());
        overviewTextView.setText(movieDetail.getOverview());
        runtimeTextView.setText(movieDetail.getRuntime() + " " + "min.");
        voteAverageTextView.setText(movieDetail.getVoteAverage());
        Glide
                .with(this)
                .load(BASE_URL_POSTER+movieDetail.getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .crossFade()
                .into(posterImageView);
    }

}
