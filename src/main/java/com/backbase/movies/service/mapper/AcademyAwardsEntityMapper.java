package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.AcademyAwards;
import com.backbase.movies.service.dto.AcademyAwardsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademyAwardsEntityMapper {

    AcademyAwardsDTO toDto(AcademyAwards entity);
    List<AcademyAwardsDTO> toDto(List<AcademyAwards> entities);
    AcademyAwards toEntity(AcademyAwardsDTO dto);
    List<AcademyAwards> toEntity(List<AcademyAwardsDTO> dtos);

}