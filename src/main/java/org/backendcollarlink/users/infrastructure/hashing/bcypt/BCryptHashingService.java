package org.backendcollarlink.users.infrastructure.hashing.bcypt;

import org.backendcollarlink.users.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}

