package com.backbase.movies.service.mapper;

import com.backbase.movies.service.dto.AcademyAwardsDTO;

import java.time.LocalDate;

public class CsvMapper {

    public static AcademyAwardsDTO rowRecordToDto(String[] row) {
        AcademyAwardsDTO dto = new AcademyAwardsDTO();
        dto.setYear(isEmpty(row[0]));
        dto.setCategory(isEmpty(row[1]));
        dto.setNominee(isEmpty(row[2]));
        dto.setAdditionalInfo(isEmpty(row[3]));
        dto.setWon(isEmpty(row[4]));
        dto.setDate(dateHandlerRules(dto.getYear()));
        return dto;
    }

    private static LocalDate dateHandlerRules(String year) {
        return LocalDate.parse(year.substring(0, 4) + "-01-01" );
    }

    public static String isEmpty(String value) {
        return !value.isEmpty() ? value : null;
    }
}
