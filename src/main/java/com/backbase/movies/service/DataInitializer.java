package com.backbase.movies.service;

import com.backbase.movies.repository.AcademyAwardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AcademyAwardsRepository academyAwardsRepository;
    private final AcademyAwardsService academyAwardsService;

    @Override
    public void run(String... args) throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("academy_awards.csv",
                this.getClass().getResourceAsStream("/data/academy_awards.csv"));
        if (academyAwardsRepository.count() < 1) {
            academyAwardsService.uploadCsvFile(multipartFile);
        }
    }

}