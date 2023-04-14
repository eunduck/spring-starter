package com.isduck.starter.user.application;

import com.isduck.starter.user.domain.Users;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Users getUsers(Pageable pageable);
}
