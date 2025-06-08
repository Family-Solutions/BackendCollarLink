package org.backendcollarlink.users.interfaces.rest.resources;

public record UpdateUserInformationResource(String firstName, String lastName, String phoneNumber, String photo, String email) {
}