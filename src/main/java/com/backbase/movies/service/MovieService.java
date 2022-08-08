package com.backbase.movies.service;

import com.backbase.movies.domain.Movie;
import com.backbase.movies.service.dto.MovieDto;
import java.util.List;

public interface MovieService {
    MovieDto getMovie(String title);
    List<MovieDto> get10TopMovies();
    Movie getMovie(Long id);
    MovieDto saveMovie(Movie movie);
}
