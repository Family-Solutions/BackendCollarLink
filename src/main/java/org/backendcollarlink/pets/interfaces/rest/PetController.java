package org.backendcollarlink.pets.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.backendcollarlink.pets.domain.model.commands.DeletePetCommand;
import org.backendcollarlink.pets.domain.model.queries.GetAllPetsByUsernameQuery;
import org.backendcollarlink.pets.domain.model.queries.GetPetByCollarIdQuery;
import org.backendcollarlink.pets.domain.model.queries.GetPetByIdQuery;
import org.backendcollarlink.pets.domain.services.PetCommandService;
import org.backendcollarlink.pets.domain.services.PetQueryService;
import org.backendcollarlink.pets.interfaces.rest.resources.CreatePetResource;
import org.backendcollarlink.pets.interfaces.rest.resources.PetResource;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdatePetCollarResource;
import org.backendcollarlink.pets.interfaces.rest.resources.UpdatePetResource;
import org.backendcollarlink.pets.interfaces.rest.transform.CreatePetCommandFromResourceAssembler;
import org.backendcollarlink.pets.interfaces.rest.transform.PetResourceFromEntityAssembler;
import org.backendcollarlink.pets.interfaces.rest.transform.UpdatePetCollarCommandFromResourceAssembler;
import org.backendcollarlink.pets.interfaces.rest.transform.UpdatePetCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/pet", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Pets", description = "Pets Management Endpoints")
public class PetController {
    private final PetQueryService petQueryService;
    private final PetCommandService petCommandService;

    public PetController(PetQueryService petQueryService, PetCommandService petCommandService) {
        this.petQueryService = petQueryService;
        this.petCommandService = petCommandService;
    }

    @PostMapping
    public ResponseEntity<PetResource> createPet(@RequestBody CreatePetResource resource) {
        var createPetCommand = CreatePetCommandFromResourceAssembler.toCommandFromResource(resource);
        var petId = petCommandService.handle(createPetCommand);

        if(petId.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(petId.get());
        return new ResponseEntity<>(petResource, HttpStatus.CREATED);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetResource> updatePet(@PathVariable Long petId, @RequestBody UpdatePetResource resource) {
        var updatePetCommand = UpdatePetCommandFromResourceAssembler.toCommandFromResource(petId, resource);
        var updatePet = petCommandService.handle(updatePetCommand);
        if(updatePet.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(updatePet.get());
        return ResponseEntity.ok(petResource);
    }

    @PutMapping("/updatePetCollar/{petId}")
    public ResponseEntity<PetResource> updatePetCollar(@PathVariable Long petId, @RequestBody UpdatePetCollarResource resource) {
        var updatePetCollarCommand = UpdatePetCollarCommandFromResourceAssembler.toCommandFromResource(petId, resource);
        var updatePetCollar = petCommandService.handle(updatePetCollarCommand);
        if(updatePetCollar.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(updatePetCollar.get());
        return ResponseEntity.ok(petResource);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<PetResource> deletePet(@PathVariable Long petId) {
        var deletePetCommand = new DeletePetCommand(petId);
        var petDeleted = petCommandService.handle(deletePetCommand);
        if(petDeleted.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResource> getPetById(@PathVariable Long petId) {
        var getPetByIdQuery = new GetPetByIdQuery(petId);
        var pet = petQueryService.handle(getPetByIdQuery);
        if(pet.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet.get());
        return ResponseEntity.ok(petResource);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<PetResource>> getPetsByUsername(@PathVariable String username) {
        var getAllPetsByUsernameQuery = new GetAllPetsByUsernameQuery(username);
        var pets = petQueryService.handle(getAllPetsByUsernameQuery);
        if(pets.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = pets.stream().map(PetResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResource);
    }

    @GetMapping("/collarId/{collarId}")
    public ResponseEntity<PetResource> getPetByCollarId(@PathVariable Long collarId) {
        var getPetByCollarIdQuery = new GetPetByCollarIdQuery(collarId);
        var pet = petQueryService.handle(getPetByCollarIdQuery);
        if(pet.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet.get());
        return ResponseEntity.ok(petResource);
    }
}
