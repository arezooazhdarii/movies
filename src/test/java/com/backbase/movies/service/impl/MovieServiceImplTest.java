package com.backbase.movies.service.impl;

import com.backbase.movies.domain.AcademyAwards;
import com.backbase.movies.domain.Movie;
import com.backbase.movies.domain.Rating;
import com.backbase.movies.exception.NotFoundException;
import com.backbase.movies.repository.MovieRepository;
import com.backbase.movies.service.AcademyAwardsService;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.dto.RateDto;
import com.backbase.movies.service.mapper.MovieMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Spy
    private MovieMapper movieMapper;

    @Mock
    private AcademyAwardsService academyAwardsService;

    @Mock
    private MovieApiService movieApiService;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getMovie() {
    }

    @Test
    public void getMovie_shouldSave_thenStatus200(){
        String title= "Slumdog Millionaire";

        Optional<AcademyAwards> academyAwards = createAcademyAwards();
        Mockito.when(academyAwardsService.getAcademyAward(title)).thenReturn(academyAwards);

        MovieDto movieDTO = createMovieDTO();

        Mockito.doReturn(movieDTO).when(this.movieApiService).getMovieByTitle(academyAwards.get().getNominee(),
                academyAwards.get().getYear());

        Movie movie = createMovie();

        Mockito.doReturn(movie).when(this.movieMapper).toEntity(movieDTO);

        Mockito.doReturn(movie).when(this.movieRepository).save(Mockito.any());

//        MovieDto returnMovie = movieService.getMovie(title);
//
//        Assertions.assertNotNull(returnMovie);
//        Assertions.assertEquals("Slumdog Millionaire", returnMovie.getTitle());
//        Assertions.assertEquals("2008", returnMovie.getYear());

    }

    @Test
    public void get10TopMovies_return200() {

        List<Movie> listMovie = new ArrayList<>();
        Movie movie= createMovie();
        listMovie.add(movie);

        List<MovieDto> movieDTOs = new ArrayList<>();
        MovieDto movieDto= createMovieDTO();
        movieDTOs.add(movieDto);

        Mockito.when(movieRepository.findTop10ByOrderByBoxOfficeDesc()).thenReturn(listMovie);
        Mockito.when(movieMapper.toDTO(listMovie)).thenReturn(movieDTOs);

        List<MovieDto> topMovies = movieService.get10TopMovies();

        Assertions.assertNotNull(topMovies);
        Assertions.assertEquals("Slumdog Millionaire", topMovies.get(0).getTitle());
        Assertions.assertEquals("2008", topMovies.get(0).getYear());
    }

    @Test
    public void get10TopMovies_return404() {

        List<Movie> listMovie = new ArrayList<>();

        Mockito.doReturn(listMovie).when(this.movieRepository).findTop10ByOrderByBoxOfficeDesc();

        Assertions.assertThrows(NotFoundException.class, () -> this.movieService.get10TopMovies());
    }

    @Test
    public void testGetMovie_returnMovie() {

        Movie movie= createMovie();

        Mockito.doReturn(Optional.of(movie)).when(this.movieRepository).findById(1L);
        Movie returnMovie = movieService.getMovie(1L);

        Assertions.assertNotNull(returnMovie);
        Assertions.assertEquals("Slumdog Millionaire", returnMovie.getTitle());
        Assertions.assertEquals("2008", returnMovie.getYear());

    }

    @Test
    public void testGetMovie_returnNotFound() {

        Mockito.doReturn(Optional.empty()).when(this.movieRepository).findById(1L);

        Assertions.assertThrows(NotFoundException.class, () -> this.movieService.getMovie(1L));
    }

    @Test
    public void saveMovie() {
        Movie movie = createMovie();
        MovieDto movieDTO = createMovieDTO();

        Mockito.doReturn(movie).when(this.movieRepository).save(Mockito.any());
        Mockito.when(movieMapper.movie(movie)).thenReturn(movieDTO);
        MovieDto returnMovie = movieService.saveMovie(movie);

        Assertions.assertNotNull(returnMovie);
        Assertions.assertEquals("Slumdog Millionaire", returnMovie.getTitle());
        Assertions.assertEquals("2008", returnMovie.getYear());
    }

    Optional<AcademyAwards> createAcademyAwards() {
        AcademyAwards academyAwards = new AcademyAwards();
        academyAwards.setId(1L);
        academyAwards.setCategory("Best Picture");
        academyAwards.setAdditionalInfo("Christian Colson, Producer");
        academyAwards.setWon("Yes");
        academyAwards.setNominee("Slumdog Millionaire");
        academyAwards.setDate(LocalDate.parse("2008-01-01"));
        return Optional.of(academyAwards);
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

}