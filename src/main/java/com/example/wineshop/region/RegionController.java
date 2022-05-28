package com.example.wineshop.region;

import com.example.wineshop.exceptions.RegionNotFoundException;
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
public class RegionController {
    private final RegionRepository     repository;
    private final RegionModelAssembler assembler;

    public RegionController(RegionRepository repository, RegionModelAssembler assembler) {
        this.repository = repository;
        this.assembler  = assembler;
    }

    @PostMapping("/region")
    ResponseEntity<?> newRegion(@RequestBody Region region) {

        repository.save(region);
        EntityModel<Region> entityModel = assembler.toModel(region);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                             .body(entityModel);
    }

    @GetMapping("/api/region")
    CollectionModel<EntityModel<Region>> all() {

        List<EntityModel<Region>> region = repository.findAll().stream() //
                                                     .map(assembler::toModel) //
                                                     .collect(Collectors.toList());


        return CollectionModel.of(region, linkTo(methodOn(RegionController.class).all()).withSelfRel());
    }

    @GetMapping("/region/{id}")
    EntityModel<Region> one(@PathVariable int id) {

        Region region = repository.findById(id) //
                .orElseThrow(() -> new RegionNotFoundException(id));

        return assembler.toModel(region);
    }

    @PutMapping("/region/{id}")
    ResponseEntity<?> replaceRegion(@RequestBody Region newRegion, @PathVariable int id) {

        Region updatedRegion = repository.findById(id) //
                .map(type -> {
                    type.setName(newRegion.getName());

                    return repository.save(type);
                }) //
                .orElseGet(() -> {
                    newRegion.setId(id);
                    return repository.save(newRegion);
                });

        EntityModel<Region> entityModel = assembler.toModel(updatedRegion);

        return ResponseEntity //
                              .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                              .body(entityModel);
    }

    @DeleteMapping("/region/{id}")
    ResponseEntity<?> deleteRegion(@PathVariable int id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

