package com.example.wine;

import com.example.wine.Classes.Region;
import com.example.wine.Exceptions.RegionNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


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
    ResponseEntity<?> newRegion(@RequestBody Region newRegion) {

        Region region = repository.findById(id)
                                  .orElseThrow(() -> new RegionNotFoundException(id));
        return assembler.toModel(region);
    }

    @GetMapping("/api/region")
    CollectionModel<EntityModel<Region>> all() {

        List<EntityModel<Region>> region = repository.findAll().stream() //
                                                     .map(assembler::toModel) //
                                                     .collect(Collectors.toList());


        return CollectionModel.of(type, linkTo(methodOn(RegionController.class).all()).withSelfRel());
    }

    @GetMapping("/region/{id}")
    EntityModel<Region> one(@PathVariable Long id) {

        Region region = repository.findById(id) //
                .orElseThrow(() -> new RegionNotFoundException(id));

        return assembler.toModel(region);
    }

    @PutMapping("/region/{id}")
    ResponseEntity<?> replaceRegion(@RequestBody Region newRegion, @PathVariable Long id) {

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
    ResponseEntity<?> deleteRegion(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

