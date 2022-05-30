package com.example.wineshop.wine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Integer> {

    List<Wine> findTop10ByOrderByRatingDesc();


    @Query(value = "SELECT * FROM wine WHERE ?1 IS NOT NULL ORDER BY ?1 DESC, ?2 DESC LIMIT ?3",
            nativeQuery = true)
    List<Wine> findTopWinesByTwoCriteriaNative(String first_criteria, String second_criteria, Integer limit);

    @Query(value = "SELECT * FROM wine WHERE ?1 IS NOT NULL ORDER BY ?1 DESC LIMIT ?2",
            nativeQuery = true)
    List<Wine> findTopWinesByOneCriteriaNative(String criteria, Integer limit);

    @Query(value = "SELECT * FROM wine GROUP BY (SELECT AVG(?1/?2)) DESC LIMIT ?3",
            nativeQuery = true)
    List<Wine> findTopWinesByCrossCriteriaNative(String first_criteria, String second_criteria, Integer limit);
}
