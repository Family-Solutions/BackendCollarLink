package org.backendcollarlink.users.interfaces.rest.resources;

public record UserInformationResource(Long id,
                                      String fullName,
                                      String phoneNumber,
                                      String photo,
                                      String email,
                                      Long userId) {
}
