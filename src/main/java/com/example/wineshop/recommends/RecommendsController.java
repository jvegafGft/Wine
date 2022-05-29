package com.example.wineshop.recommends;

import com.example.wineshop.wine.Wine;
import com.example.wineshop.wine.WineModelAssembler;
import com.example.wineshop.wine.WineRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RecommendsController {
    private final WineRepository repository;
    private final WineModelAssembler assembler;

    public RecommendsController(WineRepository repository, WineModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/api/recommends/best")
    CollectionModel<EntityModel<Wine>> bestRecommend(@RequestParam(defaultValue = "10") int top) {
        List<EntityModel<Wine>> wines = repository.findTopWinesByRatingNative(top).stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(wines, linkTo(methodOn(RecommendsController.class).bestRecommend(top)).withSelfRel());
    }
}
