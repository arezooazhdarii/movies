package com.backbase.movies.service;

import com.backbase.movies.domain.AcademyAwards;
import com.backbase.movies.repository.AcademyAwardsRepository;
import com.backbase.movies.service.dto.AcademyAwardsDTO;
import com.backbase.movies.service.mapper.AcademyAwardsEntityMapper;
import com.backbase.movies.service.mapper.CsvMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcademyAwardsService {

    private final AcademyAwardsRepository academyAwardsRepository;
    private final AcademyAwardsEntityMapper academyAwardsEntityMapper;

    public List<AcademyAwardsDTO> uploadCsvFile(MultipartFile multipart) {
        final List<AcademyAwardsDTO> rowsToDto = new ArrayList<>();
        try {
            Reader reader = new InputStreamReader(multipart.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            for (String[] rowValues : csvReader) {
                final AcademyAwardsDTO csvEntityDto = CsvMapper.rowRecordToDto(rowValues);
                final AcademyAwards academyAwards = academyAwardsEntityMapper.toEntity(csvEntityDto);
                final AcademyAwards saved = academyAwardsRepository.save(academyAwards);
                log.debug("Csv row has been persisted in database: {}", saved);
                rowsToDto.add(academyAwardsEntityMapper.toDto(saved));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowsToDto;
    }

    public List<AcademyAwards> getAcademyAwards() {
        final LocalDate after = LocalDate.parse("1927-01-01");
        final LocalDate before = LocalDate.parse("2010-01-01");
        return academyAwardsRepository
                .findByCategoryAndWonAndDateBetween("Best Picture", "YES", after, before);
    }

    public Optional<AcademyAwards> getAcademyAward(String title) {
        final LocalDate after = LocalDate.parse("1927-01-01");
        final LocalDate before = LocalDate.parse("2010-01-01");
        return academyAwardsRepository
                .findByNomineeAndCategoryAndWonAndDateBetween(title,"Best Picture", "YES", after, before);
    }

}
