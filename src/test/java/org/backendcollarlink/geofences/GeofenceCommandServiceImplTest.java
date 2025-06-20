package org.backendcollarlink.geofences;

import org.backendcollarlink.geofences.applications.internal.commandservices.GeofenceCommandServiceImpl;
import org.backendcollarlink.geofences.domain.model.aggregates.Geofence;
import org.backendcollarlink.geofences.domain.model.commands.CreateGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.commands.DeleteGeofenceCommand;
import org.backendcollarlink.geofences.domain.model.commands.UpdateGeofenceCommand;
import org.backendcollarlink.geofences.infrastrucutre.persistence.jpa.repositories.GeofenceRepository;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeofenceCommandServiceImplTest {

    private UserRepository userRepository;
    private GeofenceRepository geofenceRepository;
    private GeofenceCommandServiceImpl geofenceCommandService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        geofenceRepository = mock(GeofenceRepository.class);
        geofenceCommandService = new GeofenceCommandServiceImpl(userRepository, geofenceRepository);
    }

    @Test
    void shouldCreateGeofenceSuccessfully() {
        var username = "juan";
        var user = new User(username, "password123");
        var command = new CreateGeofenceCommand("Zona 1", -12.0464, -77.0428, 100.0, username);
        var geofence = new Geofence("Zona 1", -12.0464, -77.0428, 100.0, user);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(geofenceRepository.save(any(Geofence.class))).thenReturn(geofence);
        when(geofenceRepository.findById(any())).thenReturn(Optional.of(geofence));

        var result = geofenceCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals("Zona 1", result.get().getName());
        verify(geofenceRepository).save(any(Geofence.class));
    }

    @Test
    void shouldThrowWhenUserNotFoundOnCreate() {
        var command = new CreateGeofenceCommand("Zona 2", -12.05, -77.03, 200.0, "noExiste");

        when(userRepository.findByUsername("noExiste")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> geofenceCommandService.handle(command));
    }

    @Test
    void shouldUpdateGeofenceSuccessfully() {
        var existingGeofence = mock(Geofence.class);
        var updatedGeofence = mock(Geofence.class);
        var command = new UpdateGeofenceCommand(1L, "Zona Nueva", -12.01, -77.01, 150.0);

        when(geofenceRepository.findById(1L)).thenReturn(Optional.of(existingGeofence));
        when(existingGeofence.UpdateInformation("Zona Nueva", -12.01, -77.01, 150.0)).thenReturn(updatedGeofence);
        when(geofenceRepository.save(updatedGeofence)).thenReturn(updatedGeofence);

        var result = geofenceCommandService.handle(command);

        assertTrue(result.isPresent());
        verify(geofenceRepository).save(updatedGeofence);
    }

    @Test
    void shouldThrowWhenGeofenceNotFoundOnUpdate() {
        var command = new UpdateGeofenceCommand(999L, "Zona Inexistente", -12.0, -77.0, 100.0);

        when(geofenceRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> geofenceCommandService.handle(command));
    }

    @Test
    void shouldDeleteGeofenceSuccessfully() {
        var geofence = mock(Geofence.class);
        var command = new DeleteGeofenceCommand(1L);

        when(geofenceRepository.existsById(1L)).thenReturn(true);
        when(geofenceRepository.findById(1L)).thenReturn(Optional.of(geofence));

        var result = geofenceCommandService.handle(command);

        assertTrue(result.isPresent());
        verify(geofenceRepository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenGeofenceNotFoundOnDelete() {
        var command = new DeleteGeofenceCommand(123L);

        when(geofenceRepository.existsById(123L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> geofenceCommandService.handle(command));
    }
}
