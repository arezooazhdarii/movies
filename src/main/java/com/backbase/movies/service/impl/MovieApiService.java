package com.backbase.movies.service.impl;

import com.backbase.movies.repository.CustomOmdbRepository;
import com.backbase.movies.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieApiService {
    private final CustomOmdbRepository customOmdbRepository;

    public MovieDto getMovieByTitle(String title, String year) {
        return customOmdbRepository.getMovieByTitle(title, year);
    }
}
