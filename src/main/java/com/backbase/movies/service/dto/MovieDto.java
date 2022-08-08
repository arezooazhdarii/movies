package com.backbase.movies.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties
public class MovieDto implements Comparator<MovieDto> {
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;
    @JsonProperty("Rated")
    public String rated;
    @JsonProperty("Released")
    public String released;
    @JsonProperty("Runtime")
    public String runtime;
    @JsonProperty("Genre")
    public String genre;
    @JsonProperty("Director")
    public String director;
    @JsonProperty("Writer")
    public String writer;
    @JsonProperty("Actors")
    public String actors;
    @JsonProperty("Plot")
    public String plot;
    @JsonProperty("Language")
    public String language;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("Awards")
    public String awards;
    @JsonProperty("Poster")
    public String poster;
    @JsonProperty("Ratings")
    public List<RateDto> ratings = new ArrayList<>();
    @JsonProperty("Metascore")
    public String metascore;
    public String imdbRating;
    public String imdbVotes;
    public String imdbID;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("DVD")
    public String dVD;
    @JsonProperty("BoxOffice")
    public Long boxOffice;
    @JsonProperty("Production")
    public String production;
    @JsonProperty("Website")
    public String website;
    @JsonProperty("Response")
    public String response;

    public void setBoxOffice(String boxOffice) {
        if (boxOffice.matches("(.)*(\\d)(.)*")) this.boxOffice = Long.valueOf(boxOffice.replaceAll("[^-?0-9]+", ""));
        else this.boxOffice=1L;
    }

    @Override
    public int compare(MovieDto o1, MovieDto o2) {
        return o1.getBoxOffice().compareTo(o2.getBoxOffice());
    }

}
