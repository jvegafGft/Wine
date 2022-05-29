package com.example.wineshop.wine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Integer> {

    List<Wine> findTop10ByOrderByRatingDesc();


    @Query(value = "SELECT * FROM wine WHERE rating IS NOT NULL ORDER BY rating DESC, num_reviews DESC LIMIT ?1",
            nativeQuery = true)
    List<Wine> findTopWinesByRatingNative(Integer limit);
}
