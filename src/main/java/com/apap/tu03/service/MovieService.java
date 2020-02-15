package com.apap.tu03.service;
import com.apap.tu03.model.MovieModel;
import java.util.List;

public interface MovieService {
    void addMovie(MovieModel movie);
    List<MovieModel> getMovieList();
    MovieModel getMovieDetail(String id);
}
