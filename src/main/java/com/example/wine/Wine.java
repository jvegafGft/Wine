package com.example.wine;

import com.example.wine.Classes.Region;
import com.example.wine.Classes.Type;
import com.example.wine.Classes.Winery;

import javax.persistence.*;


@Entity
public class Wine {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String wineYear;
    private float rating;
    private int num_reviews;
    private int price;
    private int body;
    private int acidity;

    @ManyToOne
    @JoinColumn(name = "REGION")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "TYPE")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "WINERY")
    private Winery winery;

    public Wine() {
    }

    public Wine(String name, String year, float rating, int num_reviews, int price, int body, int acidity, Region region, Type type, Winery winery) {
        this.name = name;
        this.wineYear = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
        this.region = region;
        this.type = type;
        this.winery = winery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return wineYear;
    }

    public void setYear(String year) {
        this.wineYear = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getAcidity() {
        return acidity;
    }

    public void setAcidity(int acidity) {
        this.acidity = acidity;
    }


    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + wineYear + '\'' +
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
