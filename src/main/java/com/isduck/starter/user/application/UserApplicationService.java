package com.isduck.starter.user.application;

import com.isduck.starter.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserService userService;

    public Users getUsers(Pageable pageable) {
        Users users = userService.getUsers(pageable);
        return new Users(users.getPage().map(member -> member));
    }

}
