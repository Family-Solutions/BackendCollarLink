package org.backendcollarlink.users;

import org.backendcollarlink.users.application.internal.commandservices.UserCommandServiceImpl;
import org.backendcollarlink.users.application.internal.outboundservices.hashing.HashingService;
import org.backendcollarlink.users.application.internal.outboundservices.tokens.TokenService;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.domain.model.commands.SignUpCommand;
import org.backendcollarlink.users.domain.model.entities.Role;
import org.backendcollarlink.users.domain.model.valueobjects.Roles;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.backendcollarlink.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private HashingService hashingService;

    @Mock
    private TokenService tokenService;

    private UserCommandServiceImpl userCommandService;

    @BeforeEach
    void setUp() {
        userCommandService = new UserCommandServiceImpl(userRepository, hashingService, tokenService, roleRepository);
    }

    @Test
    void shouldSignUpUserSuccessfully() {
        // Arrange
        var username = "john";
        var password = "1234";
        var encodedPassword = "hashed1234";
        var role = new Role(Roles.ROLE_USER);
        var command = new SignUpCommand(username, password, List.of(role));

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(hashingService.encode(password)).thenReturn(encodedPassword);
        when(roleRepository.findByName(Roles.ROLE_USER)).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var result = userCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(encodedPassword, result.get().getPassword());
        assertTrue(result.get().getRoles().stream().anyMatch(r -> r.getName() == Roles.ROLE_USER));

        // Verify interactions
        verify(userRepository).existsByUsername(username);
        verify(hashingService).encode(password);
        verify(roleRepository).findByName(Roles.ROLE_USER);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldFailSignUpWhenUsernameExists() {
        var command = new SignUpCommand("existingUser", "pass", List.of(new Role(Roles.ROLE_USER)));

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        var exception = assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
        assertEquals("Username already exists", exception.getMessage());
        verify(userRepository).existsByUsername("existingUser");
        verifyNoMoreInteractions(userRepository, hashingService, roleRepository);
    }

    @Test
    void shouldFailSignUpWhenRoleNotFound() {
        var command = new SignUpCommand("newUser", "secret", List.of(new Role(Roles.ROLE_ADMIN)));

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_ADMIN)).thenReturn(Optional.empty());

        var exception = assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
        assertEquals("Role not found", exception.getMessage());
    }
}
