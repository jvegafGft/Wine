package com.example.wine;

import com.example.wine.Classes.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type, Long> {

  @Query("select t from Type t where t.name = ?1")
  Type findByName(String name);


}
