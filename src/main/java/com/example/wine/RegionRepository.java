package com.example.wine;

import com.example.wine.Classes.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegionRepository extends JpaRepository<Region, Long> {

  @Query("select r from Region r where r.name = ?1")
  Region findByName(String name);

}
