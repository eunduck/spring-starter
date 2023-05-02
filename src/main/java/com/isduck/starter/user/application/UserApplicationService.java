package com.isduck.starter.user.application;

import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserService userService;

    public Users getUsers(UserSearchDto userSearchDto, Pageable pageable) {
        Users users = userService.getUsers(userSearchDto, pageable);
        return new Users(users.getPage().map(member -> member));
    }

}
