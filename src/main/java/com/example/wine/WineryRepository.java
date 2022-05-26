package com.example.wine;

import com.example.wine.Classes.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WineryRepository extends JpaRepository<Winery, Long> {
  @Query("select w from Winery w where w.name = ?1")
  Winery findByName(String name);

}
