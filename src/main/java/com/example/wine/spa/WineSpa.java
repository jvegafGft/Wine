package com.example.wine.spa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "wines_spa")
public class WineSpa {
  @Id
  private int    id;
  private String winery;
  private String wine;
  private String year;
  private double rating;
  private int    num_reviews;
  private String country;
  private String region;
  private double price;
  private String type;
  private String body;
  private String acidity;

  @Override public String toString() {
    return "WineSpa{" +
           "id=" + id +
           ", winery='" + winery + '\'' +
           ", wine='" + wine + '\'' +
           ", year=" + year +
           ", rating=" + rating +
           ", numReviews=" + num_reviews +
           ", country='" + country + '\'' +
           ", region='" + region + '\'' +
           ", price=" + price +
           ", type='" + type + '\'' +
           ", body=" + body +
           ", acidity=" + acidity +
           '}';
  }
}
