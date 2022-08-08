package com.backbase.movies.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;



@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="academy_awards")
public class AcademyAwards {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    private String year;
    @Column(name = "category")
    private String category;
    @Column(name = "nominee", length = 1024)
    private String nominee;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "won")
    private String won;
    @Column(name = "date")
    private LocalDate date;
}
