package com.isduck.starter.user.presentation;

import com.isduck.starter.user.application.UserApplicationService;
import com.isduck.starter.user.domain.User;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;


    @Tag(name = "User", description = "회원 API")
    @Operation(summary = "회원 상세 조회", description = "특정 회원의 정보를 상세 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserDto.UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "회원 id를 입력하지 않았거나 잘못된 형식으로 입력",
                    content = @Content(schema = @Schema(ref = "#/components/schemas/Response400"))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원 조회",
                    content = @Content(schema = @Schema(ref = "#/components/schemas/Response404")))
    })
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto.UserResponse getUser(
            @Parameter(name = "id", description = "회원 id", in = ParameterIn.PATH)
            @NotNull(message = "회원 id를 입력해주세요.") @Positive(message = "회원 id는 숫자만 입력 가능합니다.") @PathVariable("id") Long id
    ) {
        User user = userApplicationService.getUser(id);
        return UserDto.UserResponse.from(user);
    }
    
    @Tag(name = "User", description = "회원 API")
    @Operation(summary = "회원 목록 조회", description = "가입된 회원들의 목록을 일정 페이지 단위로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserDto.UserPageResponse.class)))
    })
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public UserDto.UserPageResponse getUsers(
            @Parameter(name = "userSearchDto", description = "검색조건", in = ParameterIn.QUERY)
            UserSearchDto userSearchDto,
            @Parameter(name = "pageable", description = "페이지네이션", in = ParameterIn.QUERY)
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Users users = userApplicationService.getUsers(userSearchDto, pageable);
        return UserDto.UserPageResponse.from(users);
    }
}
