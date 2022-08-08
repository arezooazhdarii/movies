package com.backbase.movies.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}
