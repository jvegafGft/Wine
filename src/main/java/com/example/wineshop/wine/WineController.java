package com.example.wineshop.wine;

import com.example.wineshop.exceptions.WineNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WineController {

    private final WineRepository repository;
    private final WineModelAssembler assembler;

    public WineController(WineRepository repository, WineModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    @PostMapping("/api/wine")
    ResponseEntity<?> newWine(@RequestBody Wine newWine) {

        EntityModel<Wine> entityModel = assembler.toModel(repository.save(newWine));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/api/wine/{id}")
    EntityModel<Wine> one(@PathVariable int id) {

        Wine wine = repository.findById(id) //
                .orElseThrow(() -> new WineNotFoundException(id));
        return assembler.toModel(wine);
    }

    @GetMapping("/api/wine")
    CollectionModel<EntityModel<Wine>> all() {

        List<EntityModel<Wine>> wines = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(wines, linkTo(methodOn(WineController.class).all()).withSelfRel());
    }

    @PutMapping("/api/wine/{id}")
    ResponseEntity<?> replaceWine(@RequestBody Wine newWine, @PathVariable int id) {

        Wine updatedEmployee = repository.findById(id) //
                .map(wine -> {
                    wine.setName(newWine.getName());
                    wine.setYear(newWine.getYear());
                    wine.setRating(newWine.getRating());
                    wine.setNum_reviews(newWine.getNum_reviews());
                    wine.setPrice(newWine.getPrice());
                    wine.setBody(newWine.getBody());
                    wine.setAcidity(newWine.getAcidity());
                    wine.setWinery(newWine.getWinery());
                    wine.setRegion(newWine.getRegion());
                    wine.setType(newWine.getType());

                    return repository.save(wine);
                }) //
                .orElseGet(() -> {
                    newWine.setId(id);
                    return repository.save(newWine);
                });

        EntityModel<Wine> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/api/wine/{id}")
    ResponseEntity<?> deleteWine(@PathVariable int id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
