package com.example.wineshop.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegionRepository extends JpaRepository<Region, Integer> {

  @Query("select r from Region r where r.name = ?1")
  Region findByName(String name);

}
