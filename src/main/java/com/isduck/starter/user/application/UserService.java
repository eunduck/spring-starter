package com.isduck.starter.user.application;

import com.isduck.starter.user.domain.User;
import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User getUser(Long id);

    Users getUsers(UserSearchDto userSearchDto, Pageable pageable);
}
