package org.backendcollarlink.users;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.backendcollarlink.users.domain.model.aggregates.User;
import org.backendcollarlink.users.domain.model.commands.SignInCommand;
import org.backendcollarlink.users.domain.services.UserCommandService;
import org.backendcollarlink.users.interfaces.rest.AuthenticationController;
import org.backendcollarlink.users.interfaces.rest.resources.SignInResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
@ContextConfiguration(classes = {AuthenticationController.class})
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCommandService userCommandService;

    @Test
    void signIn_shouldReturnAuthenticatedUser() throws Exception {
        var resource = new SignInResource("john", "1234");
        var command = new SignInCommand("john", "1234");
        var user = new User("john", "hashed");
        var token = "token-jwt";

        when(userCommandService.handle(command))
                .thenReturn(Optional.of(ImmutablePair.of(user, token)));

        mockMvc.perform(post("/api/v1/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"username":"john","password":"1234"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"))
                .andExpect(jsonPath("$.token").value(token));
    }

    @Test
    void signIn_ShouldReturnOkAndToken_WhenCredentialsAreValid() throws Exception {
        // Arrange
        var user = new User("john", "hashedPass");
        var token = "valid.jwt.token";
        var command = new SignInCommand("john", "1234");

        when(userCommandService.handle(command))
                .thenReturn(Optional.of(ImmutablePair.of(user, token)));

        // Act & Assert
        mockMvc.perform(post("/api/v1/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "john",
                                  "password": "1234"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("john")))
                .andExpect(jsonPath("$.token", is("valid.jwt.token")));

        verify(userCommandService).handle(command);
    }
}
