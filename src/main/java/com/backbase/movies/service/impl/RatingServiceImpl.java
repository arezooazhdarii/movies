package com.backbase.movies.service.impl;

import com.backbase.movies.domain.Movie;
import com.backbase.movies.domain.Rating;
import com.backbase.movies.repository.RatingRepository;
import com.backbase.movies.service.MovieService;
import com.backbase.movies.service.RatingService;
import com.backbase.movies.service.dto.RatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final MovieService movieService;
    private final RatingRepository ratingRepository;

    @Override
    public void ratingMovie(Long movieId, RatingDTO ratingDTO) {
        Movie movie = movieService.getMovie(movieId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Rating rating = new Rating();
        rating.setSource(String.format("User: %s", username));
        rating.setValue(ratingDTO.getScore());
        rating.setMovie(movie);
        ratingRepository.save(rating);
        movieService.saveMovie(movie);
    }
}
