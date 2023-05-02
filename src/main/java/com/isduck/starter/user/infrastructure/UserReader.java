package com.isduck.starter.user.infrastructure;

import com.isduck.starter.user.domain.User;
import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import org.springframework.data.domain.Pageable;

public interface UserReader {
    User findById(Long id);
    Users getUsers(UserSearchDto userSearchDto, Pageable pageable);
}
