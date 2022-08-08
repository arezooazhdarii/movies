package com.backbase.movies.repository;

import com.backbase.movies.service.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Repository
public class CustomOmdbRepository {
    @Value("${omdb.base.url}")
    String baseUrl;

    @Value("${omdb.apikey}")
    String apikey;

    @Autowired
    RestTemplate restTemplate;

    public MovieDto getMovieByTitle(String title, String year) {
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("apikey", apikey)
                .queryParam("t", title)
                .queryParam("y", year)
                .queryParam("type","movie")
                .build().toUri();
        return restTemplate.getForEntity(uri, MovieDto.class).getBody();
    }
}
