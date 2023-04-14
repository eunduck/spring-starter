package com.isduck.starter.user.application;

import com.isduck.starter.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserService userService;

    @Override
    public Users getUsers(Pageable pageable) {
        Users users = null;
        return users;
    }
}
