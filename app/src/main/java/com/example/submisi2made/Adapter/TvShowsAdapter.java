package com.example.submisi2made.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submisi2made.R;
import com.example.submisi2made.fragment.TvShowFragment;
import com.example.submisi2made.model.TvShows;

import java.util.ArrayList;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.GridViewHolder> {
    private TvShowFragment context;
    private ArrayList<TvShows> listTvShows;

    public ArrayList<TvShows> getListTvShows() {
        return listTvShows;
    }

    public void setListTvShows(ArrayList<TvShows> listTvShows) {
        this.listTvShows = listTvShows;
    }

    public TvShowsAdapter(TvShowFragment context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowsAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemGrid = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_film, parent, false);
        return new GridViewHolder(itemGrid);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsAdapter.GridViewHolder holder, int position) {

        Glide.with(context)
                .load(getListTvShows().get(position).getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListTvShows().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
