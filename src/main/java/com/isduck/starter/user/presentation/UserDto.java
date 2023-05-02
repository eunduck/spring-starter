package com.isduck.starter.user.presentation;

import com.isduck.starter.common.util.DateUtil;
import com.isduck.starter.user.domain.User;
import com.isduck.starter.user.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

public class UserDto {

    @Builder
    @Getter
    public static class UserResponse {
        @Schema(description = "회원 id", example = "1")
        private Long id;
        @Schema(description = "이름", example = "김은덕")
        private String name;
        @Schema(description = "등록일시", example = "yyyy-MM-dd HH:mm:ss")
        private String createdAt;


        public static UserResponse from(User user) {
            return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .createdAt(DateUtil.getStringWithZone(user.getCreatedAt()))
                    .build();
        }
    }
    @Builder
    @Getter
    public static class UserPageResponse {
        private Page<UserResponse> page;

        public static UserPageResponse from(Users users) {
            return UserPageResponse.builder()
                    .page(users.getPage().map(UserResponse::from))
                    .build();
        }
    }
}
