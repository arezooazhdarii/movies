package com.backbase.movies.controller;

import com.backbase.movies.service.MovieService;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MovieController {
    private final MovieService movieService;

    @GetMapping(value = "/movies")
    @Secured("ROLE_USER")
    public ResponseEntity<Response<MovieDto>> getMovie(@RequestParam(required = true) String title,
                                                              HttpServletRequest request) {
        MovieDto movie = movieService.getMovie(title);
        return new ResponseEntity<>(new Response<MovieDto>()
                .build()
                .setMessage(movie)
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }

    @GetMapping(value = "/getTopMovies")
    public ResponseEntity<Response<List<MovieDto>>> get10TopMovies(HttpServletRequest request) {
        List<MovieDto> movieDtos= movieService.get10TopMovies();
        return new ResponseEntity<>(new Response<List<MovieDto>>()
                .build()
                .setMessage(movieDtos)
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }

}
