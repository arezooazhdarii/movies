package com.backbase.movies.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
public class RatingDTO {
    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;
}
