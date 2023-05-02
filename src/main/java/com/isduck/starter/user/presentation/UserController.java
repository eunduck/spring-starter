package com.isduck.starter.user.presentation;

import com.isduck.starter.user.application.UserApplicationService;
import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;

    @Tag(name = "User", description = "회원 API")
    @Operation(summary = "회원 목록 조회", description = "가입된 회원들의 목록을 일정 페이지 단위로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserDto.UserPageResponse.class)))
    })
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public UserDto.UserPageResponse getUsers(
            @Parameter(name = "memberSearchDto", description = "검색조건", in = ParameterIn.QUERY)
            UserSearchDto userSearchDto,
            @Parameter(name = "pageable", description = "페이지네이션", in = ParameterIn.QUERY)
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Users users = userApplicationService.getUsers(userSearchDto, pageable);
        return UserDto.UserPageResponse.from(users);
    }
}
