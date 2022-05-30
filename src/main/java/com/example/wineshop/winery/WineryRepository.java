package com.example.wineshop.winery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WineryRepository extends JpaRepository<Winery, Integer> {
  @Query("select w from Winery w where w.name = ?1")
  Winery findByName(String name);

}
