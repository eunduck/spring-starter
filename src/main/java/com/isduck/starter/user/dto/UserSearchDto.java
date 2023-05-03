package com.isduck.starter.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserSearchDto {
    @Schema(description = "이름(미 입력 시 전체 조회)", nullable = true)
    private String name = "";
}
