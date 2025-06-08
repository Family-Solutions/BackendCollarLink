package org.backendcollarlink.users.interfaces.rest.resources;

public record CreateUserInformationResource(String firstName,
                                            String lastName,
                                            String phoneNumber,
                                            String photo,
                                            String email,
                                            Long userId) {
}
