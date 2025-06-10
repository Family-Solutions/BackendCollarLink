package org.backendcollarlink.pets.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.backendcollarlink.pets.domain.model.commands.DeleteCollarCommand;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetCollarByPetIdQuery;
import org.backendcollarlink.pets.domain.services.CollarCommandService;
import org.backendcollarlink.pets.domain.services.CollarQueryService;
import org.backendcollarlink.pets.interfaces.rest.resources.CollarResource;
import org.backendcollarlink.pets.interfaces.rest.resources.CreateCollarResource;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdateCollarResource;
import org.backendcollarlink.pets.interfaces.rest.transform.CollarResourceFromEntityAssembler;
import org.backendcollarlink.pets.interfaces.rest.transform.CreateCollarCommandFromResourceAssembler;
import org.backendcollarlink.pets.interfaces.rest.transform.UpdateCollarCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/collar", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Collars", description = "Collars Management Endpoints")
public class CollarController {
    private final CollarQueryService collarQueryService;
    private final CollarCommandService collarCommandService;

    public CollarController(CollarQueryService collarQueryService, CollarCommandService collarCommandService) {
        this.collarQueryService = collarQueryService;
        this.collarCommandService = collarCommandService;
    }

    @PostMapping
    public ResponseEntity<CollarResource> createCollar(@RequestBody CreateCollarResource resource) {
        var createCollarCommand = CreateCollarCommandFromResourceAssembler.toCommandFromResource(resource);
        var collarId = collarCommandService.handle(createCollarCommand);

        if(collarId.isEmpty()) return ResponseEntity.notFound().build();
        var collarResource = CollarResourceFromEntityAssembler.toResourceFromEntity(collarId.get());
        return new ResponseEntity<>(collarResource, HttpStatus.CREATED);
    }

    @PutMapping("/{collarId}")
    public ResponseEntity<CollarResource> updateCollar(@PathVariable Long collarId, @RequestBody UpdateCollarResource resource) {
        var updateCollarCommand = UpdateCollarCommandFromResourceAssembler.toCommandFromResource(collarId, resource);
        var updateCollar = collarCommandService.handle(updateCollarCommand);
        if(updateCollar.isEmpty()) return ResponseEntity.notFound().build();
        var collarResource = CollarResourceFromEntityAssembler.toResourceFromEntity(updateCollar.get());
        return ResponseEntity.ok(collarResource);
    }

    @DeleteMapping("/{collarId}")
    public ResponseEntity<CollarResource> deleteCollar(@PathVariable Long collarId) {
        var deleteCollarCommand = new DeleteCollarCommand(collarId);
        var collarResource = collarCommandService.handle(deleteCollarCommand);
        if(collarResource.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{collarId}")
    public ResponseEntity<CollarResource> getCollarById(@PathVariable Long collarId) {
        var getCollarByIdQuery = new GetCollarByIdQuery(collarId);
        var collar = collarQueryService.handle(getCollarByIdQuery);
        if(collar.isEmpty()) return ResponseEntity.notFound().build();
        var collarResource = CollarResourceFromEntityAssembler.toResourceFromEntity(collar.get());
        return ResponseEntity.ok(collarResource);
    }

    @GetMapping("/petId/{petId}")
    public ResponseEntity<CollarResource> getCollarByPetById(@PathVariable Long petId) {
        var getCollarByPetIdQuery = new GetCollarByPetIdQuery(petId);
        var collar = collarQueryService.handle(getCollarByPetIdQuery);
        if(collar.isEmpty()) return ResponseEntity.notFound().build();
        var collarResource = CollarResourceFromEntityAssembler.toResourceFromEntity(collar.get());
        return ResponseEntity.ok(collarResource);
    }
}
