package com.example.wine;

import com.example.wine.Classes.Region;
import com.example.wine.Classes.Type;
import com.example.wine.Classes.Winery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@Table(name = "WINE")
@NoArgsConstructor
public class Wine {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id", nullable=false)
  @NotNull(message= "No puede ser nulo")
  private Long id;

  @NotEmpty(message= "Tiene que haber nombre")
  private String name;

  @NotNull
  @Min(1900)
  @Max(2022)
  private String year;

  @NotNull
  @Min(0)
  @Max(5)
  private double rating;

  @NotNull
  @Min(0)
  private int    num_reviews;

  @NotNull
  @Min(0)
  private double price;

  @NotNull
  @Min(1)
  @Max(5)
  private String body;

  @NotNull
  @Min(1)
  @Max(5)
  private String acidity;

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

  public Wine(String name, String year, double rating, int num_reviews, double price, String body, String acidity) {
    this.name        = name;
    this.year        = year;
    this.rating      = rating;
    this.num_reviews = num_reviews;
    this.price       = price;
    this.body        = body;
    this.acidity     = acidity;
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
