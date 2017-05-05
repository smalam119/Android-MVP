package com.smalam.pseudozero.androidmvp.Modules.MovieList.View;

import android.content.Intent;
import android.content.res.Resources;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

import com.smalam.pseudozero.androidmvp.Application.App;
import com.smalam.pseudozero.androidmvp.Constants;
import com.smalam.pseudozero.androidmvp.Model.Movie;
import com.smalam.pseudozero.androidmvp.Modules.MovieDetail.View.MovieDetailActivity;
import com.smalam.pseudozero.androidmvp.Dagger.Components.DaggerMovieListComponent;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.MovieListViewModule;
import com.smalam.pseudozero.androidmvp.Modules.MovieList.Interface.IMovieListContact;
import com.smalam.pseudozero.androidmvp.Modules.MovieList.Presenter.MovieListPresenter;
import com.smalam.pseudozero.androidmvp.R;

import com.wang.avi.AVLoadingIndicatorView;
import android.os.Bundle;
import javax.inject.Inject;

import android.util.TypedValue;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMovieListContact.IView {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) AVLoadingIndicatorView avLoadingIndicatorView;
    @BindView(R.id.main_content) CoordinatorLayout coordinatorLayout;

    private MoviesAdapter mMoviesAdapter;
    private List<Movie> mMovieList;

    @Inject
    MovieListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        mMovieList = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(this,mMovieList, new IMovieListContact.OnItemClickListener() {
            @Override
            public void onClick(Movie item) {
                int movieId = item.getId();
                startActivity(new Intent(getApplicationContext(), MovieDetailActivity.class).putExtra(Constants.KEY_MOVIE_ID,movieId));
            }
        });

        DaggerMovieListComponent.builder()
                .apiServiceComponent(((App) getApplicationContext()).getApiServiceComponent())
                .movieListViewModule(new MovieListViewModule(this))
                .build().inject(this);

        prepareView();
        mPresenter.getMovieData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStopAPIService();
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
        mMovieList.addAll((Collection<? extends Movie>) data);
        mMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDataFetchedError(Object data) {
        Toast.makeText(this,(String) data,Toast.LENGTH_LONG);
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
