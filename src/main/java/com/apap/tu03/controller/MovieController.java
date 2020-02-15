package com.apap.tu03.controller;

import com.apap.tu03.model.MovieModel;
import com.apap.tu03.service.MovieService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping("/movie/add")
    public String add(@RequestParam(value = "id", required = true)String id,
    @RequestParam(value = "title", required = true) String title,
    @RequestParam(value = "genre", required = true) String genre,
    @RequestParam(value = "budget", required = true) Long budget,
    @RequestParam(value= "duration", required = true) Integer duration, Model model){
        if(movieService.getMovieDetail(id) == null){
            MovieModel movie = new MovieModel(id, title, genre, budget, duration);
            movieService.addMovie(movie);
            return "add";
        }
        String message = "Sudah terdapat movie dengan id " + id + " didalam sistem!";
        model.addAttribute("message", message);
        return "confirmation-page";
    }

    @RequestMapping("/movie/view")
    public String view1(@RequestParam("id") String id, Model model){
        if(movieService.getMovieDetail(id) != null){
            MovieModel archive = movieService.getMovieDetail(id);
            model.addAttribute("movie", archive);
            return "view-movie";
        }
        String message = "Movie dengan id " + id + " tidak ditemukan";
        model.addAttribute("message", message);
        return "confirmation-page";

    }

    @RequestMapping("/movie/viewall")
    public String viewAll(Model model){
        List<MovieModel> archive = movieService.getMovieList();
        model.addAttribute("movies",archive);
        return "viewall-movie";
    }
    @RequestMapping(value="movie/view/{id}")
    public String view2(@PathVariable String id, Model model){
        if(movieService.getMovieDetail(id) != null){
            MovieModel archive = movieService.getMovieDetail(id);
            model.addAttribute("movie", archive);
            return "view-movie";
        }
        String message = "Movie dengan id " + id + " tidak ditemukan";
        model.addAttribute("message", message);
        return "confirmation-page";
    }
    @RequestMapping(value = "movie/update/{id}/duration/{time}")
    public String updateDuration(@PathVariable String id, @PathVariable Integer time, Model model){
        String message;
        if(movieService.getMovieDetail(id) != null) {
            movieService.getMovieDetail(id).setDuration(time);
            message = "Durasi movie dengan id " + id + " berhasil ditambahkan!";
        }else{
            message = "Id Movie " + id +" tidak ditemukan";
        }
        model.addAttribute("message", message);
        return "confirmation-page";
    }
    @RequestMapping(value = "movie/delete/{id}")
    public String delete(@PathVariable String id, Model model){
        String message;
        if(movieService.getMovieDetail(id) != null){
            movieService.getMovieList().remove(movieService.getMovieDetail(id));
            message = "Movie dengan id " + id + " berhasil dihapus!";
        }else{
            message = "Id Movie " + id +" tidak ditemukan";
        }
        model.addAttribute("message", message);
        return "confirmation-page";
    }

}
