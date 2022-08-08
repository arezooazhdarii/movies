package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.Movie;
import com.backbase.movies.domain.User;
import com.backbase.movies.service.dto.MovieDto;
import com.backbase.movies.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto movie (Movie movie);
    Movie toEntity(MovieDto movieDto);
    List<MovieDto> toDTO(List<Movie> stockEntities);
}
