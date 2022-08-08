package com.backbase.movies.service.impl;

import com.backbase.movies.domain.AcademyAwards;
import com.backbase.movies.domain.Movie;
import com.backbase.movies.domain.Rating;
import com.backbase.movies.exception.NotFoundException;
import com.backbase.movies.repository.MovieRepository;
import com.backbase.movies.service.AcademyAwardsService;
import com.backbase.movies.service.MovieService;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final AcademyAwardsService academyAwardsService;
    private final MovieApiService movieApiService;
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    public MovieDto getMovie(String title) {
        Optional<AcademyAwards> academyAward = academyAwardsService.getAcademyAward(title);
        if (academyAward.isPresent()) {
            MovieDto movieDto = movieApiService.getMovieByTitle(academyAward.get().getNominee(),
                    String.valueOf(academyAward.get().getYear()));
            Movie movie = movieMapper.toEntity(movieDto);
            List<Rating> ratings = movie.getRatings();
            if (!ratings.isEmpty())
                ratings.forEach(list -> list.setMovie(movie));
            movieRepository.save(movie);
            return movieDto;
        } else
            throw new NotFoundException("not found movie");
    }

    public List<MovieDto> get10TopMovies() {
        List<Movie> movies = movieRepository.findTop10ByOrderByBoxOfficeDesc();
        if (movies.isEmpty()) {
            throw new NotFoundException("not found movie");
        } else {
            return movieMapper.toDTO(movies);
        }
    }

    @Override
    public Movie getMovie(Long id) {
        Optional<Movie> byId = movieRepository.findById(id);
        if (byId.isPresent()) return byId.get();
        else throw new NotFoundException("not found movie");
    }

    @Override
    public MovieDto saveMovie(Movie movie) {
        Movie returnMovie = movieRepository.save(movie);
        return movieMapper.movie(returnMovie);
    }

}
