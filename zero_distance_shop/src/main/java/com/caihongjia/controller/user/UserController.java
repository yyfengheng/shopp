package com.caihongjia.controller.user;

import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.result.Result;
import com.caihongjia.common.util.ThreadLocalUtil;
import com.caihongjia.pojo.dto.UserDTO;
import com.caihongjia.pojo.vo.UserVO;
import com.caihongjia.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 */
@Controller
@ResponseBody
@RequestMapping("/zero/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userServer;


    /**
     * 用户注册, 前端访问的接口就是这个方法, 会接收前端参数
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody UserDTO userDTO) throws LoginException {
        log.info("用户注册: {}",userDTO);
        userServer.register(userDTO);
        return Result.success();
    }

    /**
     * 用户登录, 用实体类接收
     * @param userDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserDTO userDTO) throws LoginException {
        log.info("用户登录: {}",userDTO);
        UserVO userVO = userServer.login(userDTO);
        // 将用户信息放入线程中
        ThreadLocalUtil.set(userVO);
        return Result.success(userVO);
    }

    /**
     * 根据用户id修改用户信息
     * @param userDTO
     * @return
     */
    @PutMapping()
    public Result<UserVO> updateUser(@RequestBody UserDTO userDTO){
        log.info("修改用户信息: {}",userDTO);
        userServer.updateUser(userDTO);
        return Result.success();
    }

    /**
     * 更新用户密码
     * @param userDTO
     * @return
     */
    @PutMapping("/password")
    public Result<UserVO> updatePassword(@RequestBody UserDTO userDTO) throws Exception {
        userServer.updatePassword(userDTO);
        return Result.success();
    }

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping()
    public Result<UserVO> getUser(Integer id){
        UserVO user = userServer.getUser(id);
        return Result.success(user);
    }

}
