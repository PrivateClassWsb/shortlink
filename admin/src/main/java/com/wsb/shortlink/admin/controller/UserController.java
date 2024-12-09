package com.wsb.shortlink.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import com.wsb.shortlink.admin.common.convention.result.Result;
import com.wsb.shortlink.admin.common.convention.result.Results;
import com.wsb.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.wsb.shortlink.admin.dto.resp.UserActualRespDTO;
import com.wsb.shortlink.admin.dto.resp.UserRespDTO;
import com.wsb.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查找无脱敏用户
     */
    @GetMapping("/api/short-link/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUserName(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     */
    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     */
    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }
}
