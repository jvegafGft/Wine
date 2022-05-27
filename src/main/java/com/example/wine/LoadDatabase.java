package com.example.wine;


import com.example.wine.Classes.Region;
import com.example.wine.Classes.Type;
import com.example.wine.Classes.Winery;
import com.example.wine.spa.WineSpa;
import com.example.wine.spa.WineSpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean CommandLineRunner initDatabase(
      WineSpaRepository wineSpaRepository,
      RegionRepository regionRepository,
      TypeRepository typeRepository,
      WineryRepository wineryRepository,
      WineRepository wineshopRepository) {

    return args -> {

      if (regionRepository.count() < 1) {

        wineSpaRepository
            .findAll()
            .stream()
            .map(WineSpa::getRegion)
            .distinct()
            .forEach(r -> regionRepository.save(new Region(r, "Espana")));


        wineSpaRepository.findAll()
                         .stream()
                         .map(WineSpa::getType)
                         .distinct()
                         .forEach(t -> typeRepository.save(new Type(t)));


        wineSpaRepository.findAll()
                         .stream()
                         .map(WineSpa::getWinery)
                         .distinct()
                         .forEach(w -> wineryRepository.save(new Winery(w)));


        wineSpaRepository.findAll().forEach(wineSpa -> {
          Wine wine = new Wine();
          wine.setName(wineSpa.getWine());
          wine.setBody(wineSpa.getBody());
          wine.setAcidity(wineSpa.getAcidity());
          wine.setRating(wineSpa.getRating());
          wine.setNum_reviews(wineSpa.getNum_reviews());
          wine.setYear(wineSpa.getYear());
          wine.setPrice(wineSpa.getPrice());
          Region r = regionRepository.findByName(wineSpa.getRegion());
          wine.setRegion(r);
          Type t = typeRepository.findByName(wineSpa.getType());
          wine.setType(t);
          Winery winery = wineryRepository.findByName(wineSpa.getWinery());
          wine.setWinery(winery);
          wineshopRepository.save(wine);
        });
      }


      log.info("Total Preloaded " + wineshopRepository.count());
    };
  }
}

