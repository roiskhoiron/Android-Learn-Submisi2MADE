package com.example.submisi2made.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submisi2made.DetailActivity;
import com.example.submisi2made.R;
import com.example.submisi2made.adapter.TvShowsAdapter;
import com.example.submisi2made.listener.ItemClickSupport;
import com.example.submisi2made.model.TvShows;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView rvTvShows;
    private TvShowsAdapter tvShowsAdapter;
    private ArrayList<TvShows> listTvShows = new ArrayList<>();

    private String[] dataTitle, dataRelease, dataDirector, dataOverview, dataPhoto;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTvShows = view.findViewById(R.id.rv_category);
        tvShowsAdapter = new TvShowsAdapter(this);
        rvTvShows.setAdapter(tvShowsAdapter);

        rvTvShows.setHasFixedSize(true);

        listTvShows.addAll(getListTvShows());
        showRecyclerGrid();

        preapare();
        addItem();
        ItemClickSupport.addTo(rvTvShows).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedTvShows(listTvShows.get(position));
                TvShows tvShowsParcelable = new TvShows();
                if (tvShowsParcelable != null) {
                    tvShowsParcelable.setTitle(dataTitle[position]);
                    tvShowsParcelable.setYear(dataRelease[position]);
                    tvShowsParcelable.setDirector(dataDirector[position]);
                    tvShowsParcelable.setOverview(dataOverview[position]);
                    tvShowsParcelable.setPhoto(dataPhoto[position]);
                }

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.TVSHOW_INDEX, tvShowsParcelable);
                startActivity(intent);
            }
        });

    }

    private void addItem() {
        ArrayList<TvShows> tvShows = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++) {
            TvShows tvShow = new TvShows();
            tvShow.setTitle(dataTitle[i]);
            tvShow.setYear(dataRelease[i]);
            tvShow.setDirector(dataDirector[i]);
            tvShow.setOverview(dataOverview[i]);
            tvShow.setPhoto(dataPhoto[i]);

            tvShows.add(tvShow);
        }
    }

    private void preapare() {
        dataTitle = getResources().getStringArray(R.array.title_tvshows);
        dataRelease = getResources().getStringArray(R.array.release_tvshows);
        dataDirector = getResources().getStringArray(R.array.director_tvshows);
        dataOverview = getResources().getStringArray(R.array.description_tvshows);
        dataPhoto = getResources().getStringArray(R.array.photo_tvshows);
    }

    private ArrayList<TvShows> getListTvShows() {
        String[] dataTitle = getResources().getStringArray(R.array.title_tvshows);
        String[] dataDirector = getResources().getStringArray(R.array.director_tvshows);
        String[] dataYear = getResources().getStringArray(R.array.release_tvshows);
        String[] dataOverview = getResources().getStringArray(R.array.description_tvshows);
        String[] dataPhoto = getResources().getStringArray(R.array.photo_tvshows);

        ArrayList<TvShows> listTvShows = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            TvShows tvShows = new TvShows();
            tvShows.setTitle(dataTitle[i]);
            tvShows.setYear(dataYear[i]);
            tvShows.setDirector(dataDirector[i]);
            tvShows.setOverview(dataOverview[i]);
            tvShows.setPhoto(dataPhoto[i]);
            listTvShows.add(tvShows);
        }
        return listTvShows;
    }

    private void showRecyclerGrid() {
        rvTvShows.setLayoutManager(new GridLayoutManager(getContext(), 2));
        TvShowsAdapter tvShowsAdapter = new TvShowsAdapter(this);
        tvShowsAdapter.setListTvShows(listTvShows);
        rvTvShows.setAdapter(tvShowsAdapter);

    }

    private void showSelectedTvShows(TvShows tvShows) {
        Toast.makeText(getContext(), "Kamu memilih " + tvShows.getTitle(), Toast.LENGTH_SHORT).show();
    }

}
