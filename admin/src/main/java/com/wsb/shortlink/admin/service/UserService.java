package com.wsb.shortlink.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wsb.shortlink.admin.dao.entity.UserDO;
import com.wsb.shortlink.admin.dto.req.UserLoginReqDTO;
import com.wsb.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.wsb.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.wsb.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.wsb.shortlink.admin.dto.resp.UserRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户是否存在
     *
     * @param username 用户名
     * @return 用户存在返回False，不存在返回True
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     *
     * @param requestParam 注册用户请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户
     *
     * @param requestParam 修改用户请求参数
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     *
     * @param requestParam 用户登录请求参数
     * @return 用户登录登录返回参数 Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     *
     * @param username 用户名
     * @param token    用户登录 Token
     * @return 用户是否登录
     */
    Boolean checkLogin(String username, String token);

    /**
     * 用户退出登录
     *
     * @param username 用户名
     * @param token    用户登录 Token
     */
    void logout(String username, String token);
}
