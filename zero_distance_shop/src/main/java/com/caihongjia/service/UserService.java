package com.caihongjia.service;

import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.exception.user.UpdatePasswordException;
import com.caihongjia.pojo.dto.UserDTO;
import com.caihongjia.pojo.vo.UserVO;

public interface UserService {
    /**
     * 用户注册
     * @param userDTO
     */
    void register(UserDTO userDTO) throws LoginException;

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    UserVO login(UserDTO userDTO) throws LoginException;

    /**
     * 更新用户密码
     * @param userDTO
     */
    void updatePassword(UserDTO userDTO) throws UpdatePasswordException, Exception;

    /**
     * 查询用户信息
     * @return
     */
    UserVO getUser(Integer id);

    /**
     * 根据用户id修改用户信息
     * @return
     */
    void updateUser(UserDTO userDTO);
}
