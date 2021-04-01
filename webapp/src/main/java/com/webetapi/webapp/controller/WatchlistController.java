package com.webetapi.webapp.controller;

import com.webetapi.webapp.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WatchlistController {

    @Autowired
    private MovieRatingService movieRatingService;

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        String viewName = "watchlist";
        Map<String, String> model = new HashMap<String, String>();
        model.put("numberOfMovies", movieRatingService.getMovieRating("Rambo"));
        return new ModelAndView(viewName , model);
    }
}
