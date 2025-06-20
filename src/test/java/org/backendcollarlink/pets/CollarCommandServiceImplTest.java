package org.backendcollarlink.pets;

import org.backendcollarlink.pets.applications.internal.commandservices.CollarCommandServiceImpl;
import org.backendcollarlink.pets.domain.model.aggregates.Collar;
import org.backendcollarlink.pets.domain.model.commands.CreateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.DeleteCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarCommand;
import org.backendcollarlink.pets.domain.model.commands.UpdateCollarLocationCommand;
import org.backendcollarlink.pets.infrastructure.persistence.jpa.repositories.CollarRepository;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;


@ExtendWith(MockitoExtension.class)
class CollarCommandServiceImplTest {

    private CollarRepository collarRepository;
    private UserRepository userRepository;
    private CollarCommandServiceImpl service;

    @BeforeEach
    void setUp() {
        collarRepository = mock(CollarRepository.class);
        userRepository = mock(UserRepository.class);
        service = new CollarCommandServiceImpl(collarRepository, userRepository);
    }

    @Test
    void shouldCreateCollarSuccessfully() {
        // Arrange
        var user = new User("testuser", "password");
        var command = new CreateCollarCommand("testuser", 123L, "ModelX", 10.0, 20.0);
        var collar = new Collar(user, 123L, "ModelX", 10.0, 20.0);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(collarRepository.save(any(Collar.class))).thenReturn(collar);
        when(collarRepository.findById(any())).thenReturn(Optional.of(collar));

        // Act
        var result = service.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("ModelX", result.get().getModel());
        assertEquals(123L, result.get().getSerialNumber());
        assertEquals(10.0, result.get().getLastLatitude());
        assertEquals(20.0, result.get().getLastLongitude());
        verify(userRepository).findByUsername("testuser");
        verify(collarRepository).save(any(Collar.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        var command = new CreateCollarCommand("ghostuser", 123L, "ModelY", 0.0, 0.0);

        when(userRepository.findByUsername("ghostuser")).thenReturn(Optional.empty());

        var exception = assertThrows(Exception.class, () -> service.handle(command));
        assertEquals("No value present", exception.getMessage()); // por Optional.get()
    }

    @Test
    void shouldUpdateCollarSuccessfully() {
        var user = new User("juan", "pass");
        var collar = new Collar(user, 111L, "OldModel", 1.0, 2.0);
        var updatedCollar = new Collar(user, 111L, "NewModel", 3.0, 4.0);
        var command = new UpdateCollarCommand(1L, "juan", 111L, "NewModel", 3.0, 4.0);

        when(collarRepository.findById(1L)).thenReturn(Optional.of(collar));
        when(userRepository.findByUsername("juan")).thenReturn(Optional.of(user));
        when(collarRepository.save(any())).thenReturn(updatedCollar);

        var result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals("NewModel", result.get().getModel());
        verify(collarRepository).save(any(Collar.class));
    }

    @Test
    void shouldFailToUpdateCollarWhenNotFound() {
        var command = new UpdateCollarCommand(99L, "juan", 999L, "ModelZ", 0.0, 0.0);

        when(collarRepository.findById(99L)).thenReturn(Optional.empty());

        var ex = assertThrows(IllegalArgumentException.class, () -> service.handle(command));
        assertEquals("Collar not found", ex.getMessage());
    }

    @Test
    void shouldDeleteCollarSuccessfully() {
        var collar = mock(Collar.class);
        var command = new DeleteCollarCommand(5L);

        when(collarRepository.existsById(5L)).thenReturn(true);
        when(collarRepository.findById(5L)).thenReturn(Optional.of(collar));

        var result = service.handle(command);

        assertTrue(result.isPresent());
        verify(collarRepository).deleteById(5L);
    }

    @Test
    void shouldFailToDeleteWhenCollarNotFound() {
        var command = new DeleteCollarCommand(999L);

        when(collarRepository.existsById(999L)).thenReturn(false);

        var ex = assertThrows(IllegalArgumentException.class, () -> service.handle(command));
        assertEquals("Collar not found", ex.getMessage());
    }

    @Test
    void shouldUpdateCollarLocation() {
        var collar = new Collar(null, 123L, "ModelZ", 1.0, 2.0);
        var command = new UpdateCollarLocationCommand(123L, 5.5, 6.6);
        var updated = new Collar(null, 123L, "ModelZ", 5.5, 6.6);

        when(collarRepository.findBySerialNumber(123L)).thenReturn(Optional.of(collar));
        when(collarRepository.save(any())).thenReturn(updated);

        var result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(5.5, result.get().getLastLatitude());
        assertEquals(6.6, result.get().getLastLongitude());
    }

    @Test
    void shouldFailToUpdateLocationIfSerialNotFound() {
        var command = new UpdateCollarLocationCommand(321L, 5.0, 6.0);

        when(collarRepository.findBySerialNumber(321L)).thenReturn(Optional.empty());

        var ex = assertThrows(IllegalArgumentException.class, () -> service.handle(command));
        assertEquals("Collar serial number not found", ex.getMessage());
    }
}
