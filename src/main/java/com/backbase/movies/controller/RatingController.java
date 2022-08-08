package com.backbase.movies.controller;

import com.backbase.movies.service.RatingService;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.dto.RateDto;
import com.backbase.movies.service.dto.RatingDTO;
import com.backbase.movies.service.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RatingController {
    private final RatingService ratingService;

    @PostMapping(value = "/rating/{id}")
    public ResponseEntity<Response<String>> getMovie(@PathVariable("id") Long movieId,
                                                     @RequestBody RatingDTO ratingDTO,
                                                     HttpServletRequest request) {
        ratingService.ratingMovie(movieId,ratingDTO);
        return new ResponseEntity<>(new Response<String>()
                .build()
                .setMessage("Your rating successful")
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }


}
