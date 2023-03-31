package com.backbase.movies.service.mapper;

import com.backbase.movies.domain.AcademyAwards;
import com.backbase.movies.service.dto.AcademyAwardsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-31T15:07:56+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 19.0.2 (Homebrew)"
)
@Component
public class AcademyAwardsEntityMapperImpl implements AcademyAwardsEntityMapper {

    @Override
    public AcademyAwardsDTO toDto(AcademyAwards entity) {
        if ( entity == null ) {
            return null;
        }

        AcademyAwardsDTO academyAwardsDTO = new AcademyAwardsDTO();

        academyAwardsDTO.setId( entity.getId() );
        academyAwardsDTO.setYear( entity.getYear() );
        academyAwardsDTO.setCategory( entity.getCategory() );
        academyAwardsDTO.setNominee( entity.getNominee() );
        academyAwardsDTO.setAdditionalInfo( entity.getAdditionalInfo() );
        academyAwardsDTO.setWon( entity.getWon() );
        academyAwardsDTO.setDate( entity.getDate() );

        return academyAwardsDTO;
    }

    @Override
    public List<AcademyAwardsDTO> toDto(List<AcademyAwards> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AcademyAwardsDTO> list = new ArrayList<AcademyAwardsDTO>( entities.size() );
        for ( AcademyAwards academyAwards : entities ) {
            list.add( toDto( academyAwards ) );
        }

        return list;
    }

    @Override
    public AcademyAwards toEntity(AcademyAwardsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AcademyAwards.AcademyAwardsBuilder academyAwards = AcademyAwards.builder();

        academyAwards.id( dto.getId() );
        academyAwards.year( dto.getYear() );
        academyAwards.category( dto.getCategory() );
        academyAwards.nominee( dto.getNominee() );
        academyAwards.additionalInfo( dto.getAdditionalInfo() );
        academyAwards.won( dto.getWon() );
        academyAwards.date( dto.getDate() );

        return academyAwards.build();
    }

    @Override
    public List<AcademyAwards> toEntity(List<AcademyAwardsDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<AcademyAwards> list = new ArrayList<AcademyAwards>( dtos.size() );
        for ( AcademyAwardsDTO academyAwardsDTO : dtos ) {
            list.add( toEntity( academyAwardsDTO ) );
        }

        return list;
    }
}
