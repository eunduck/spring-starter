package com.isduck.starter.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_EXISTS("사용자를 찾을 수 없습니다.")
    ;
    private final String message;
}
