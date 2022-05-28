package com.example.wineshop.winery;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
class WineryModelAssembler implements RepresentationModelAssembler<Winery, EntityModel<Winery>> {

    @Override
    public EntityModel<Winery> toModel(Winery winery) {

        return EntityModel.of(winery, //
                linkTo(methodOn(WineryController.class).one(winery.getId())).withSelfRel(),
                linkTo(methodOn(WineryController.class).all()).withRel("Winery"));
    }
}
