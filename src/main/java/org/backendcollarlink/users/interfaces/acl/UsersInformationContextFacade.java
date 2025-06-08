package org.backendcollarlink.users.interfaces.acl;

import org.backendcollarlink.users.domain.model.commands.CreateUserInformationCommand;
import org.backendcollarlink.users.domain.services.UserInformationCommandService;
import org.backendcollarlink.users.domain.services.UserInformationQueryService;
import org.springframework.stereotype.Service;

@Service
public class UsersInformationContextFacade {

    private final UserInformationQueryService userInformationQueryService;
    private final UserInformationCommandService userInformationCommandService;

    public UsersInformationContextFacade(UserInformationQueryService userInformationQueryService, UserInformationCommandService userInformationCommandService) {
        this.userInformationQueryService = userInformationQueryService;
        this.userInformationCommandService = userInformationCommandService;
    }

    public Long createUser(String firstName, String lastName,String phoneNumber,String photo, String email, Long userId) {
        var createProfileCommand = new CreateUserInformationCommand(firstName, lastName, phoneNumber,photo, email, userId);
        var user = userInformationCommandService.handle(createProfileCommand);
        if (user.isEmpty()) return 0L;
        return user.get().getId();
    }

}
