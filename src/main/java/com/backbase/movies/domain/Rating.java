package com.backbase.movies.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source")
    public String source;

    @Column(name = "value")
    public String value;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
}
