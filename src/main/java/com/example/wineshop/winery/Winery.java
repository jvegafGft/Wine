package com.example.wineshop.winery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "WINERY")
public class Winery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int    id;
  private String name;

  public Winery(String name) {
    this.name = name;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Winery)) return false;
    Winery winery = (Winery) o;
    return Objects.equals(getId(), winery.getId()) &&
           getName().equals(winery.getName());
  }

  @Override public int hashCode() {
    return Objects.hash(getId(), getName());
  }

  @Override public String toString() {
    return "Winery{" +
           "id=" + id +
           ", name='" + name + '\'' +
           '}';

  }
}