package com.apap.tu03.service;

import com.apap.tu03.model.MovieModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InMemoryMovieService implements MovieService {
    private List<MovieModel> archiveMovie;
    public  InMemoryMovieService(){
        archiveMovie = new ArrayList<>();
    }

    @Override
    public void addMovie(MovieModel movie) {
        archiveMovie.add(movie);
    }

    @Override
    public List<MovieModel> getMovieList() {
        return archiveMovie;
    }

    @Override
    public MovieModel getMovieDetail(String id) {
        for(MovieModel movie : archiveMovie){
            if(movie.getId().equalsIgnoreCase(id)){
                 return movie;
            }
        }
        return null;
    }
}
