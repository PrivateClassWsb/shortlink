package com.wsb.shortlink.admin.controller;


import com.wsb.shortlink.admin.common.convention.result.Result;
import com.wsb.shortlink.admin.common.convention.result.Results;
import com.wsb.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.wsb.shortlink.admin.dto.resp.UserRespDTO;
import com.wsb.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查找用户
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        if (result == null){
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        }
        return Results.success(result);
    }


}
