package org.backendcollarlink.geofences.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.backendcollarlink.geofences.domain.model.commands.DeleteGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.queries.GetAllGeofencesByUsernameQuery;
import org.backendcollarlink.geofences.domain.model.queries.GetGeofenceByIdQuery;
import org.backendcollarlink.geofences.domain.services.GeofenceCommandService;
import org.backendcollarlink.geofences.domain.services.GeofenceQueryService;
import org.backendcollarlink.geofences.interfaces.rest.resources.CreateGeofenceResource;
import org.backendcollarlink.geofences.interfaces.rest.resources.GeofenceResource;
import org.backendcollarlink.geofences.interfaces.rest.resources.UpdateGeofenceResource;
import org.backendcollarlink.geofences.interfaces.rest.transform.CreateGeofenceCommandFromResourceAssembler;
import org.backendcollarlink.geofences.interfaces.rest.transform.GeofenceResourceFromEntityAssembler;
import org.backendcollarlink.geofences.interfaces.rest.transform.UpdateGeofenceCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/geofence", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Geofences", description = "Geofences Management Endpoints")
public class GeofenceController {
    private final GeofenceQueryService geofenceQueryService;
    private final GeofenceCommandService geofenceCommandService;

    public GeofenceController(GeofenceQueryService geofenceQueryService, GeofenceCommandService geofenceCommandService) {
        this.geofenceQueryService = geofenceQueryService;
        this.geofenceCommandService = geofenceCommandService;
    }

    @PostMapping
    public ResponseEntity<GeofenceResource> createGeofence(@RequestBody CreateGeofenceResource resource) {
        var createGeofenceCommand = CreateGeofenceCommandFromResourceAssembler.toCommandFromResource(resource);
        var geofenceId = geofenceCommandService.handle(createGeofenceCommand);

        if(geofenceId.isEmpty()) return ResponseEntity.badRequest().build();
        var geofenceResource = GeofenceResourceFromEntityAssembler.toResourceFromEntity(geofenceId.get());
        return new ResponseEntity<>(geofenceResource, HttpStatus.CREATED);
    }

    @PutMapping("/{geofenceId}")
    public ResponseEntity<GeofenceResource> updateGeofence(@PathVariable Long geofenceId, @RequestBody UpdateGeofenceResource resource) {
        var updateGeofenceCommand = UpdateGeofenceCommandFromResourceAssembler.toCommandFromResource(geofenceId, resource);
        var updateGeofence = geofenceCommandService.handle(updateGeofenceCommand);
        if(updateGeofence.isEmpty()) return ResponseEntity.badRequest().build();
        var geofenceResource = GeofenceResourceFromEntityAssembler.toResourceFromEntity(updateGeofence.get());
        return ResponseEntity.ok(geofenceResource);
    }

    @DeleteMapping("/{geofenceId}")
    public ResponseEntity<Void> deleteGeofenceById(@PathVariable Long geofenceId) {
        var deleteGeofenceCommand = new DeleteGeofenceCommand(geofenceId);
        var geofenceDeleted = geofenceCommandService.handle(deleteGeofenceCommand);
        if(geofenceDeleted.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{geofenceId}")
    public ResponseEntity<GeofenceResource> getGeofenceById(@PathVariable Long geofenceId) {
        var getGeofenceByIdQuery = new GetGeofenceByIdQuery(geofenceId);
        var geofence = geofenceQueryService.handle(getGeofenceByIdQuery);
        if(geofence.isEmpty()) return ResponseEntity.badRequest().build();
        var geofenceResource = GeofenceResourceFromEntityAssembler.toResourceFromEntity(geofence.get());
        return ResponseEntity.ok(geofenceResource);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<GeofenceResource>> getAllGeofenceByUsername(@PathVariable String username) {
        var getAllGeofencesByUsernameQuery = new GetAllGeofencesByUsernameQuery(username);
        var geofences = geofenceQueryService.handle(getAllGeofencesByUsernameQuery);
        if(geofences.isEmpty()) return ResponseEntity.badRequest().build();
        var geofenceResource = geofences.stream().map(GeofenceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(geofenceResource);
    }
}
