package com.backbase.movies.service;

import com.backbase.movies.service.dto.RatingDTO;

public interface RatingService {
    void ratingMovie(Long movieId, RatingDTO ratingDTO);
}
