package org.backendcollarlink.users.domain.model.commands;

public record CreateUserInformationCommand(String firstName, String lastName, String phoneNumber, String photo, String email, Long userId) {
}
