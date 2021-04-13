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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submisi2made.DetailActivity;
import com.example.submisi2made.R;
import com.example.submisi2made.adapter.MoviesAdapter;
import com.example.submisi2made.listener.ItemClickSupport;
import com.example.submisi2made.model.Movies;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;
    private ArrayList<Movies> listMovies = new ArrayList<>();

    private String[] dataTitle, dataRelease, dataDirector, dataOverview, dataPhoto;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvMovies = view.findViewById(R.id.rv_category);
        moviesAdapter = new MoviesAdapter(this);
        rvMovies.setAdapter(moviesAdapter);

        rvMovies.setHasFixedSize(true);

        listMovies.addAll(getListMovies());
        showRecyclerList();

        preapare();
        addItem();

        ItemClickSupport.addTo(rvMovies).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View v) {
                showSelectedMovie(listMovies.get(i));
                Movies moviesParcelable = new Movies();
                if (moviesParcelable != null) {
                    moviesParcelable.setTitle(dataTitle[i]);
                    moviesParcelable.setYear(dataRelease[i]);
                    moviesParcelable.setDirector(dataDirector[i]);
                    moviesParcelable.setOverview(dataOverview[i]);
                    moviesParcelable.setPhoto(dataPhoto[i]);
                }

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.MOVIE_INDEX, moviesParcelable);
                startActivity(intent);
            }
        });


    }

    private void addItem() {
        ArrayList<Movies> movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++) {
            Movies movie = new Movies();
            movie.setTitle(dataTitle[i]);
            movie.setYear(dataRelease[i]);
            movie.setDirector(dataDirector[i]);
            movie.setOverview(dataOverview[i]);
            movie.setPhoto(dataPhoto[i]);

            movies.add(movie);
        }
    }

    private void preapare() {
        dataTitle = getResources().getStringArray(R.array.title_movies);
        dataRelease = getResources().getStringArray(R.array.release_movies);
        dataDirector = getResources().getStringArray(R.array.director_movies);
        dataOverview = getResources().getStringArray(R.array.description_movies);
        dataPhoto = getResources().getStringArray(R.array.photo_movies);
    }

    private ArrayList<Movies> getListMovies() {
        String[] dataTitle = getResources().getStringArray(R.array.title_movies);
        String[] dataDirector = getResources().getStringArray(R.array.director_movies);
        String[] dataYear = getResources().getStringArray(R.array.release_movies);
        String[] dataOverview = getResources().getStringArray(R.array.description_movies);
        String[] dataPhoto = getResources().getStringArray(R.array.photo_movies);

        ArrayList<Movies> listMovies = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movies movies = new Movies();
            movies.setTitle(dataTitle[i]);
            movies.setYear(dataYear[i]);
            movies.setDirector(dataDirector[i]);
            movies.setOverview(dataOverview[i]);
            movies.setPhoto(dataPhoto[i]);
            listMovies.add(movies);
        }
        return listMovies;
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        moviesAdapter.setListMovies(listMovies);
        rvMovies.setAdapter(moviesAdapter);
    }

    private void showSelectedMovie(Movies movies) {
        Toast.makeText(getContext(), "Kamu memilih " + movies.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
