package com.example.wine;

import com.example.wine.Classes.Region;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<Region, EntityModel<Region>> {
    @Override
    public EntityModel<Region> toModel(Region region) {

        return EntityModel.of(region,
                linkTo(methodOn(RegionController.class).one(region.getId())).withSelfRel(),
                linkTo(methodOn(RegionController.class).all()).withRel("region"));
    }
}

