package com.isduck.starter.user.presentation;

import com.isduck.starter.user.application.UserApplicationService;
import com.isduck.starter.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;

    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public UserDto.UserPageResponse getMembers(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Users members = userApplicationService.getUsers(pageable);
        return UserDto.UserPageResponse.from(members);
    }

}
