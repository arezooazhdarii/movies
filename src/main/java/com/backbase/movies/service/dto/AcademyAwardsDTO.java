package com.backbase.movies.service.dto;

import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@ToString
public class AcademyAwardsDTO {
    private Long id;
    private String year;
    private String category;
    private String nominee;
    private String additionalInfo;
    private String won;
    private LocalDate date;
}
