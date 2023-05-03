package com.isduck.starter.user.application;

import com.isduck.starter.common.ErrorCode;
import com.isduck.starter.user.domain.User;
import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import com.isduck.starter.user.infrastructure.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReader userReader;

    @Override
    public User getUser(Long id) {
        User user = userReader.findById(id);
        if (user == null) {
            throw new NoSuchElementException(ErrorCode.USER_NOT_EXISTS.getMessage());
        }
        return user;
    }

    @Override
    public Users getUsers(UserSearchDto userSearchDto, Pageable pageable) {
        return userReader.getUsers(userSearchDto, pageable);
    }
}
