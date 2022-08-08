package com.backbase.movies.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    public String title;
    @Column(name = "year")
    public String year;
    @Column(name = "rated")
    public String rated;
    @Column(name = "released")
    public String released;
    @Column(name = "runtime")
    public String runtime;
    @Column(name = "genre")
    public String genre;
    @Column(name = "director")
    public String director;
    @Column(name = "writer")
    public String writer;
    @Column(name = "actors")
    public String actors;
    @Column(name = "plot")
    public String plot;
    @Column(name = "language")
    public String language;
    @Column(name = "country")
    public String country;
    @Column(name = "awards")
    public String awards;
    @Column(name = "poster")
    public String poster;
//    @Column(name = "ratings")
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    public List<Rating> ratings=new ArrayList<>();
    @Column(name = "metaScore")
    public String metaScore;
    @Column(name = "imdbRating")
    public String imdbRating;
    @Column(name = "imdbVotes")
    public String imdbVotes;
    @Column(name = "imdbID")
    public String imdbID;
    @Column(name = "type")
    public String type;
    @Column(name = "dVD")
    public String dVD;
    @Column(name = "boxOffice")
    public Long boxOffice;
    @Column(name = "production")
    public String production;
    @Column(name = "website")
    public String website;
    @Column(name = "response")
    public String response;

    public void setRatings(List<Rating> ratings) {
        ratings.forEach(rating -> rating.setMovie(this));
        this.ratings = ratings;
    }
}
