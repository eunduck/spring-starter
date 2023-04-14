package com.isduck.starter.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@RequiredArgsConstructor
public class Users {
    private final Page<User> page;
}
