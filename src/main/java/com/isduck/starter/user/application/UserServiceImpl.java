package com.isduck.starter.user.application;

import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import com.isduck.starter.user.infrastructure.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReader userReader;

    @Override
    public Users getUsers(UserSearchDto userSearchDto, Pageable pageable) {
        return userReader.getUsers(userSearchDto, pageable);
    }
}
