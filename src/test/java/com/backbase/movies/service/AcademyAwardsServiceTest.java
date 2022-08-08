package com.backbase.movies.service;

import com.backbase.movies.domain.AcademyAwards;
import com.backbase.movies.repository.AcademyAwardsRepository;
import com.backbase.movies.service.dto.AcademyAwardsDTO;
import com.backbase.movies.service.mapper.AcademyAwardsEntityMapper;
import com.backbase.movies.service.mapper.AcademyAwardsEntityMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import({AcademyAwardsService.class, AcademyAwardsEntityMapperImpl.class})
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AcademyAwardsServiceTest {

    @Autowired
    AcademyAwardsRepository academyAwardsRepository;
    @Autowired
    AcademyAwardsService academyAwardsService;
    @Autowired
    AcademyAwardsEntityMapper academyAwardsEntityMapper;

    @Test
    void testUploadCsvData_whenValidInput_thenReturn() throws Exception {
        academyAwardsRepository.deleteAll();

        File file = new File("src/test/java/resources/static/academy_awards.csv");
        MockMultipartFile multipartFile =
                new MockMultipartFile("tempCsvTest.tmp", "data/academy_awards.csv", "text/plain",
                        Files.newInputStream(file.toPath()));

        final List<AcademyAwardsDTO> csvEntityDtos = academyAwardsService.uploadCsvFile(multipartFile);

        assertThat(csvEntityDtos).isNotNull().hasSize(10137);

        List<AcademyAwards> dataEntities = academyAwardsRepository.findAll();
        assertThat(dataEntities).isNotNull().hasSize(10137);
    }

    @Test
    void testGetAcademyAwards_whenValidInput_thenReturn() {
        academyAwardsRepository.deleteAll();

        fillDataToAcademyAwardsDB_correctData();

        List<AcademyAwards> academyAwards = academyAwardsService.getAcademyAwards();

        assertThat(academyAwards).isNotNull().hasSize(3);
    }

    @Test
    void testGetAcademyAwards_whenInvalidInput_thenReturn() {
        academyAwardsRepository.deleteAll();

        fillDataToAcademyAwardsToDB_wrongData();

        List<AcademyAwards> academyAwards = academyAwardsService.getAcademyAwards();

        assertThat(academyAwards).isNotNull().hasSize(2);
    }

    @Test
    void testGetAcademyAwards_getRealData_thenReturn() {

        List<AcademyAwards> academyAwards = academyAwardsService.getAcademyAwards();

        assertThat(academyAwards).isNotNull().hasSize(83);
    }


    void fillDataToAcademyAwardsDB_correctData() {

        AcademyAwards academyAward = AcademyAwards.builder()
                .additionalInfo("Duncan Kenworthy, Producer").date(LocalDate.parse("1994-01-01"))
                .category("Best Picture").won("Yes").nominee("Four Weddings and a Funeral")
                .year("1994 (67th)").build();

        AcademyAwards academyAward1 = AcademyAwards.builder()
                .additionalInfo("Jeff Bridges").date(LocalDate.parse("1989-01-01"))
                .category("Best Picture").won("Yes").nominee("My Left Foot")
                .year("1989").build();

        AcademyAwards academyAward2 = AcademyAwards.builder()
                .additionalInfo("John Powell").date(LocalDate.parse("2010-01-01"))
                .category("Best Picture").won("Yes")
                .nominee("The Fighter").year("2010").build();

        academyAwardsRepository.save(academyAward);
        academyAwardsRepository.save(academyAward1);
        academyAwardsRepository.save(academyAward2);
    }

    void fillDataToAcademyAwardsToDB_wrongData() {
        AcademyAwards academyAward = AcademyAwards.builder()
                .additionalInfo("Duncan Kenworthy, Producer").date(LocalDate.parse("1920-01-01"))
                .category("Short Film (Animated)").won("Yes").nominee("Four Weddings and a Funeral")
                .year("1994 (67th)").build();

        AcademyAwards academyAward1 = AcademyAwards.builder()
                .additionalInfo("Jeff Bridges").date(LocalDate.parse("1989-01-01"))
                .category("Best Picture").won("Yes").nominee("My Left Foot")
                .year("1989").build();

        AcademyAwards academyAward2 = AcademyAwards.builder()
                .additionalInfo("John Powell").date(LocalDate.parse("2010-01-01"))
                .category("Best Picture").won("Yes")
                .nominee("The Fighter").year("2010").build();

        academyAwardsRepository.save(academyAward);
        academyAwardsRepository.save(academyAward1);
        academyAwardsRepository.save(academyAward2);

    }
}
