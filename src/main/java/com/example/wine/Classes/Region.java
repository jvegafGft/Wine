package com.example.wine.Classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long   id;
  private String name;
  private String country;


  public Region(String name, String country) {
    this.name    = name;
    this.country = country;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Region)) return false;
    Region region = (Region) o;
    return Objects.equals(getId(), region.getId()) && getName().equals(region.getName()) && getCountry().equals(
        region.getCountry());
  }

  @Override public int hashCode() {
    return Objects.hash(getId(), getName(), getCountry());
  }

  @Override public String toString() {
    return "Region{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", country='" + country + '\'' +
           '}';
  }

}