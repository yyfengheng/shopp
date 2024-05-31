package com.caihongjia.mapper;

import com.caihongjia.pojo.dto.UserDTO;
import com.caihongjia.pojo.entity.UserEntity;
import com.caihongjia.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     * 新增用户信息
     * @param userEntity
     */
    @Insert("insert into user(username, name, phone, email, password, status, sex, age, image) " +
            "VALUES (#{username},#{name},#{phone},#{email},#{password},#{status},#{sex},#{age},#{image})")
    void insertUser(UserEntity userEntity);

    /**
     * 根据用户用户名或手机号或邮箱和密码查询用户信息
     * @param userEntity
     * @return
     */
    UserEntity getByUserAndPassword(UserEntity userEntity);

    /**
     * 根据用户ID查询密码
     * @param id
     */
    UserEntity getById(Integer id);

    /**
     * 根据用户ID修改密码
     * @param userEntity1
     */
    @Update("update user set password=#{password} where id=#{id}")
    void updatePassword(UserEntity userEntity1);

    /**
     * 根据用户ID修改用户信息
     * @param userEntity
     * @return
     */
    void updateUser(UserEntity userEntity);
}
