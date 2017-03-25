package com.smalam.pseudozero.androidmvp.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smalam.pseudozero.androidmvp.Model.Movie;
import com.smalam.pseudozero.androidmvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sayed Mahmudul Alam on 3/25/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private Context mContext;
    private List<Movie> mMovieList;

    public MoviesAdapter(Context mContext, List<Movie> mMovieList ) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.genreTextView.setText("");
        Glide.with(mContext).load(movie.getPosterPath()).into(holder.imageViewThumbNail);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_text_view) TextView titleTextView;
        @BindView(R.id.thumbnail_image_view) ImageView imageViewThumbNail;
        @BindView(R.id.genre_text_view) TextView genreTextView;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
