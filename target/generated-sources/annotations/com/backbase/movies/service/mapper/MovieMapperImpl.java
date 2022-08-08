package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.Movie;
import com.backbase.movies.domain.Rating;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.dto.RateDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T01:48:07+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDto movie(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        if ( movie.getBoxOffice() != null ) {
            movieDto.setBoxOffice( String.valueOf( movie.getBoxOffice() ) );
        }
        movieDto.setTitle( movie.getTitle() );
        movieDto.setYear( movie.getYear() );
        movieDto.setRated( movie.getRated() );
        movieDto.setReleased( movie.getReleased() );
        movieDto.setRuntime( movie.getRuntime() );
        movieDto.setGenre( movie.getGenre() );
        movieDto.setDirector( movie.getDirector() );
        movieDto.setWriter( movie.getWriter() );
        movieDto.setActors( movie.getActors() );
        movieDto.setPlot( movie.getPlot() );
        movieDto.setLanguage( movie.getLanguage() );
        movieDto.setCountry( movie.getCountry() );
        movieDto.setAwards( movie.getAwards() );
        movieDto.setPoster( movie.getPoster() );
        movieDto.setRatings( ratingListToRateDtoList( movie.getRatings() ) );
        movieDto.setImdbRating( movie.getImdbRating() );
        movieDto.setImdbVotes( movie.getImdbVotes() );
        movieDto.setImdbID( movie.getImdbID() );
        movieDto.setType( movie.getType() );
        movieDto.setDVD( movie.getDVD() );
        movieDto.setProduction( movie.getProduction() );
        movieDto.setWebsite( movie.getWebsite() );
        movieDto.setResponse( movie.getResponse() );
        movieDto.dVD = movie.dVD;

        return movieDto;
    }

    @Override
    public Movie toEntity(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.title( movieDto.getTitle() );
        movie.year( movieDto.getYear() );
        movie.rated( movieDto.getRated() );
        movie.released( movieDto.getReleased() );
        movie.runtime( movieDto.getRuntime() );
        movie.genre( movieDto.getGenre() );
        movie.director( movieDto.getDirector() );
        movie.writer( movieDto.getWriter() );
        movie.actors( movieDto.getActors() );
        movie.plot( movieDto.getPlot() );
        movie.language( movieDto.getLanguage() );
        movie.country( movieDto.getCountry() );
        movie.awards( movieDto.getAwards() );
        movie.poster( movieDto.getPoster() );
        movie.ratings( rateDtoListToRatingList( movieDto.getRatings() ) );
        movie.imdbRating( movieDto.getImdbRating() );
        movie.imdbVotes( movieDto.getImdbVotes() );
        movie.imdbID( movieDto.getImdbID() );
        movie.type( movieDto.getType() );
        movie.dVD( movieDto.dVD );
        movie.boxOffice( movieDto.getBoxOffice() );
        movie.production( movieDto.getProduction() );
        movie.website( movieDto.getWebsite() );
        movie.response( movieDto.getResponse() );

        return movie.build();
    }

    @Override
    public List<MovieDto> toDTO(List<Movie> stockEntities) {
        if ( stockEntities == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<MovieDto>( stockEntities.size() );
        for ( Movie movie : stockEntities ) {
            list.add( movie( movie ) );
        }

        return list;
    }

    protected RateDto ratingToRateDto(Rating rating) {
        if ( rating == null ) {
            return null;
        }

        RateDto rateDto = new RateDto();

        rateDto.setSource( rating.getSource() );
        rateDto.setValue( rating.getValue() );

        return rateDto;
    }

    protected List<RateDto> ratingListToRateDtoList(List<Rating> list) {
        if ( list == null ) {
            return null;
        }

        List<RateDto> list1 = new ArrayList<RateDto>( list.size() );
        for ( Rating rating : list ) {
            list1.add( ratingToRateDto( rating ) );
        }

        return list1;
    }

    protected Rating rateDtoToRating(RateDto rateDto) {
        if ( rateDto == null ) {
            return null;
        }

        Rating.RatingBuilder rating = Rating.builder();

        rating.source( rateDto.getSource() );
        rating.value( rateDto.getValue() );

        return rating.build();
    }

    protected List<Rating> rateDtoListToRatingList(List<RateDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Rating> list1 = new ArrayList<Rating>( list.size() );
        for ( RateDto rateDto : list ) {
            list1.add( rateDtoToRating( rateDto ) );
        }

        return list1;
    }
}
