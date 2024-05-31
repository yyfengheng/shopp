package com.caihongjia.service.impl;

import com.caihongjia.common.constant.GlobalExceptionConstant;
import com.caihongjia.common.constant.JwtSigningKey;
import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.exception.user.PasswordMismatchException;
import com.caihongjia.common.exception.user.UpdatePasswordException;
import com.caihongjia.common.util.JwtUtil;
import com.caihongjia.common.util.ThreadLocalUtil;
import com.caihongjia.mapper.UserMapper;
import com.caihongjia.pojo.dto.UserDTO;
import com.caihongjia.pojo.entity.UserEntity;
import com.caihongjia.pojo.vo.UserVO;
import com.caihongjia.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用于实现具体逻辑的类
 */
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param userDTO
     */
    @Override
    public void register(UserDTO userDTO) throws LoginException {
        // 创建一个与数据库字段一致的实体类,用于与数据库做交互
        UserEntity userEntity = new UserEntity();
        // spring中自带的对象工具类
        // 该方法是用于对象属性拷贝,将参数一中与参数二相同的属性名，拷贝到参数二中的属性里
        BeanUtils.copyProperties(userDTO,userEntity);
        // 从类中获取未加密密码进行加密
        String password = userEntity.getPassword();

        // Spring中的加密算法类
        // 使用md5加密
        String newPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        // 在将加密后的密码放入实体类中
        userEntity.setPassword(newPassword);
        try{
            // 新增一个新的用户
            userMapper.insertUser(userEntity);
        }catch(Exception e){
            // 出现异常抛出自定义异常
            throw new LoginException("用户名已存在");
        }
    }

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    @Override
    public UserVO login(UserDTO userDTO) throws LoginException {

        // 获取接收过来的标识用户的身份的字符串, 可以是用户名,手机号,邮箱
        String userIdentifier = userDTO.getUserIdentifier();
        // 用正则表达式判断用什么登录的
        if (userIdentifier.matches("1[3-9]\\d{9}")){// 判断是否为手机号, 如果为手机号就将手机号设置实体类中
            userDTO.setPhone(userIdentifier);
        }else if(userIdentifier.matches("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")){// 判断是否为邮箱, 如果为邮箱就将邮箱设置实体类中
            userDTO.setEmail(userIdentifier);
        }else{ // 如若都不是就是用户名登录
            userDTO.setUsername(userIdentifier);
        }

        // 跟注册作用一致
        // 创建一个与数据库字段一致的实体类,用于与数据库做交互
        UserEntity userEntity = new UserEntity();
        // spring中自带的对象工具类
        // 该方法是用于对象属性拷贝,将参数一中与参数二相同的属性名，拷贝到参数二中的属性里
        BeanUtils.copyProperties(userDTO,userEntity);

        // 从类中获取密码进行加密
        String password = userEntity.getPassword();
        //将密码md5加密然后再对比, 无法反向解密, 只能加密后去比较是否一致
        userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        // 根据用户名或手机号或邮箱和密码一起查询信息是否存在
        UserEntity user = userMapper.getByUserAndPassword(userEntity);

        // 将获得的数据转换为VO对象传递给前端
        UserVO userVO = new UserVO();
        try{
            BeanUtils.copyProperties(user, userVO);
        } catch(Exception e){
            throw new LoginException(GlobalExceptionConstant.USERNAME_PASSWORD_EXCEPTION);
        }

        // 获得jwt令牌, 获取令牌, 其他请求如若没有令牌会被拦截
        String jwt = JwtUtil.createJwt(JwtSigningKey.userKey,userVO);
        userVO.setToken(jwt);
        return userVO;
    }

    /**
     * 更新用户密码
     * @param userDTO
     */
    @Override
    public void updatePassword(UserDTO userDTO) throws Exception {
        Integer id = userDTO.getId();
        UserEntity userEntity = userMapper.getById(id);

        // 判断原密码和数据库密码是否一致
        // 将原密码使用md5加密后,再去对比
        String password = DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes());
        if(!password.equals(userEntity.getPassword())){
            // 报错抛出
            throw new PasswordMismatchException();
        }

        UserEntity userEntity1 = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity1);
        userEntity1.setPassword(DigestUtils.md5DigestAsHex(userDTO.getNewPassword().getBytes()));

        // 修改密码
        try{
            userMapper.updatePassword(userEntity1);
        }catch(Exception e){
            throw new UpdatePasswordException();
        }

    }

    /**
     * 解析token获得用户信息
     * @return
     */
    @Override
    public UserVO getUser(Integer id) {
        UserEntity userEntity = userMapper.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEntity,userVO);
        return userVO;
    }

    /**
     * 根据用户id修改用户信息
     * @return
     */
    @Override
    public void updateUser(UserDTO userDTO) {
        LinkedHashMap o = (LinkedHashMap) ThreadLocalUtil.get();
        Integer userId = Integer.parseInt(o.get("id").toString());
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        userEntity.setId(userId);

        userMapper.updateUser(userEntity);
    }
}
