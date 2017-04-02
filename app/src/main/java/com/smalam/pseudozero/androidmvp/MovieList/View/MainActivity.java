package com.smalam.pseudozero.androidmvp.MovieList.View;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.smalam.pseudozero.androidmvp.Model.Movie;
import com.smalam.pseudozero.androidmvp.Model.MovieResponse;
import com.smalam.pseudozero.androidmvp.MovieList.Interface.MovieListContact;
import com.smalam.pseudozero.androidmvp.MovieList.Presenter.MovieListPresenter;
import com.smalam.pseudozero.androidmvp.R;

import com.wang.avi.AVLoadingIndicatorView;
import android.os.Bundle;
import android.util.TypedValue;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieListContact.View {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) AVLoadingIndicatorView avLoadingIndicatorView;
    @BindView(R.id.main_content) CoordinatorLayout coordinatorLayout;

    private MoviesAdapter mMoviesAdapter;
    private List<Movie> mMovieList;
    private MovieListContact.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        mMovieList = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(this,mMovieList, new MovieListContact.OnItemClickListener() {
            @Override
            public void onClick(Movie item) {
                Snackbar.make(coordinatorLayout,item.getTitle(),Snackbar.LENGTH_LONG).show();
            }
        });

        prepareView();

        presenter = new MovieListPresenter(this,this);
        presenter.fetchMovieData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }

    @Override
    public void onMovieDataFetchedSuccess(Response<MovieResponse> response) {
        mMovieList.addAll(response.body().getResults());
        mMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieDataFetchedSuccess(MovieResponse response) {
        mMovieList.addAll(response.getResults());
        mMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieDataFetchedError(String errorMessage) {
        Snackbar.make(coordinatorLayout,"There is a problem fetching data"+" "+errorMessage,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoader() {
        avLoadingIndicatorView.show();
    }

    @Override
    public void removeLoader() {
        avLoadingIndicatorView.hide();
    }

    private void prepareView(){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mMoviesAdapter);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
