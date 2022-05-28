package com.example.wineshop.winery;

import com.example.wineshop.exceptions.WineryNotFoundException;
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
public class WineryController {


  private final WineryRepository     repository;
  private final WineryModelAssembler assembler;

  public WineryController(WineryRepository repository, WineryModelAssembler assembler) {
    this.repository = repository;
    this.assembler  = assembler;
  }

  @GetMapping("/api/winery/{id}")
  EntityModel<Winery> one(@PathVariable int id) {

    Winery winery = (Winery) repository.findById(id)
                                       .orElseThrow(() -> new WineryNotFoundException(id));
    return assembler.toModel(winery);
  }

  @GetMapping("/api/winery")
  CollectionModel<EntityModel<Winery>> all() {

    List<EntityModel<Winery>> winery = repository.findAll().stream() //
                                                 .map(assembler::toModel) //
                                                 .collect(Collectors.toList());

    return CollectionModel.of(winery, linkTo(methodOn(WineryController.class).all()).withSelfRel());
  }

}

