package com.backbase.movies.repository;

import com.backbase.movies.domain.AcademyAwards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademyAwardsRepository extends JpaRepository<AcademyAwards,Long> {
    Optional<AcademyAwards> findByAdditionalInfo(String additionalInfo);
    List<AcademyAwards> findByCategoryAndWonAndDateBetween(String category, String won, LocalDate after,LocalDate before);
    Optional<AcademyAwards> findByNomineeAndCategoryAndWonAndDateBetween(String title, String category,String won, LocalDate after,LocalDate before);
}
