package org.backendcollarlink.users.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.backendcollarlink.users.application.internal.outboundservices.tokens.TokenService;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {

    /**
     * This method is responsible for extracting the JWT token from the HTTP request.
     * @param token the HTTP request
     * @return String the JWT token
     */
    String getBearerTokenFrom(HttpServletRequest token);

    /**
     * This method is responsible for generating a JWT token from an authentication object.
     * @param authentication the authentication object
     * @return String the JWT token
     * @see Authentication
     */
    String generateToken(Authentication authentication);
}
