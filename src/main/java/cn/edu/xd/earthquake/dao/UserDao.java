package cn.edu.xd.earthquake.dao;

import cn.edu.xd.earthquake.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author LitheLight
 * @date 2019/6/25
 */
@Mapper
@Repository
public interface UserDao {

    @Insert("insert into user(username,password,role,latitude,longitude) values(#{username},#{password},#{role},#{latitude},#{longitude})")
    int insertUser(User user);

    @Select("select password from user where username=#{username}")
    String getPassword(@Param("username")String username);

    @Select("select role from user where username=#{username}")
    String getRole(@Param("username")String username);

    @Select("select * from user where username=#{username}")
    User getUser(@Param("username")String username);

    @Select("select role.permission from user,role where username=#{username} and role.role=user.role")
    String getRolePermission(@Param("username") String username);

    @Select("select permission from user where username=#{username}")
    String getPermission(@Param("username") String username);

    @Update("update user set password=#{newPassword} where username=#{username}")
    int updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);
}
