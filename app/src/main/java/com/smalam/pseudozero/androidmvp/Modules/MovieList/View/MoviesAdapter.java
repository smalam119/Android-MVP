package com.smalam.pseudozero.androidmvp.Modules.MovieList.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smalam.pseudozero.androidmvp.Model.Movie;
import com.smalam.pseudozero.androidmvp.Modules.MovieList.Interface.IMovieListContact;
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
    private final IMovieListContact.OnItemClickListener mListener;
    private static String BASE_URL_POSTER =  "http://image.tmdb.org/t/p/w160/";

    public MoviesAdapter(Context mContext, List<Movie> mMovieList,IMovieListContact.OnItemClickListener listener) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        this.mListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Movie movie = mMovieList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.click(mMovieList.get(position), mListener);

        Glide
                .with(mContext)
                .load(BASE_URL_POSTER+movie.getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .crossFade()
                .into(holder.imageViewThumbNail);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_title_text_view) TextView titleTextView;
        @BindView(R.id.thumbnail_image_view) ImageView imageViewThumbNail;
        //@BindView(R.id.info_button) Button infoButton;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void click(final Movie response, final IMovieListContact.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(response);
                }
            });
        }

    }
}
