package com.backbase.movies.service.impl;

import com.backbase.movies.domain.Movie;
import com.backbase.movies.domain.Rating;
import com.backbase.movies.repository.RatingRepository;
import com.backbase.movies.service.MovieService;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.dto.RateDto;
import com.backbase.movies.service.dto.RatingDTO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ratingMovie() {
        RatingDTO ratingDTO=new RatingDTO("100");
        Long movieId = 1L;
        Movie movie = createMovie();
        MovieDto movieDTO = createMovieDTO();

        Rating rating=new Rating(1L,"user",ratingDTO.getScore(),movie);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("arezoo");

        Mockito.when(movieService.getMovie(movieId)).thenReturn(movie);

        Mockito.doReturn(rating).when(this.ratingRepository).save(Mockito.any());

        Mockito.when(movieService.saveMovie(movie)).thenReturn(movieDTO);

        ratingService.ratingMovie(movieId,ratingDTO);
    }

    Movie createMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Slumdog Millionaire");
        movie.setYear("2008");
        movie.setDirector("Danny Boyle, Loveleen Tandan");
        movie.setActors("Dev Patel, Freida Pinto, Saurabh Shukla");
        movie.setBoxOffice(141319928L);

        List<Rating> ratings = new ArrayList<>();
        Rating rating = new Rating(1L,"Internet Movie Database","8.0/10",movie);
        Rating rating1 = new Rating(1L,"IRotten Tomatoes","8.0/10",movie);
        ratings.add(rating);
        ratings.add(rating1);

        movie.setRatings(ratings);
        return movie;
    }

    MovieDto createMovieDTO() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Slumdog Millionaire");
        movieDto.setYear("2008");
        movieDto.setDirector("Danny Boyle, Loveleen Tandan");
        movieDto.setActors("Dev Patel, Freida Pinto, Saurabh Shukla");
        movieDto.setBoxOffice("141319928");

        List<RateDto> rateDtos = new ArrayList<>();
        RateDto rateDto = new RateDto("Internet Movie Database","8.0/10");
        RateDto rateDto1 = new RateDto("Rotten Tomatoes","91%");
        rateDtos.add(rateDto);
        rateDtos.add(rateDto1);

        movieDto.setRatings(rateDtos);
        return movieDto;
    }
}