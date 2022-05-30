package com.example.wineshop.wine;

import com.example.wineshop.region.Region;
import com.example.wineshop.type.Type;
import com.example.wineshop.winery.Winery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "WINE")
@NoArgsConstructor
public class Wine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private Integer year;
  private Double rating;
  private Integer num_reviews;
  private Double price;
  private Double body;
  private Double acidity;

  @ManyToOne()
  @JoinColumn(name = "region_id")
  @JsonIgnore
  private Region region;

  @ManyToOne()
  @JoinColumn(name = "type_id")
  @JsonIgnore
  private Type type;

  @ManyToOne()
  @JoinColumn(name = "winery_id")
  @JsonIgnore
  private Winery winery;

  public Wine(String name, Integer year, Double rating, Integer num_reviews, Double price, Double body, Double acidity) {
    this.name = name;
    this.year = year;
    this.rating = rating;
    this.num_reviews = num_reviews;
    this.price = price;
    this.body = body;
    this.acidity = acidity;
  }

  @Override public String toString() {
    return "Wine{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", wineYear=" + year +
           ", rating=" + rating +
           ", num_reviews=" + num_reviews +
           ", price=" + price +
           ", body=" + body +
           ", acidity=" + acidity +
           ", region=" + region +
           ", type=" + type +
           ", winery=" + winery +
           '}';
  }

}
