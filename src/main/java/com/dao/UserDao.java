package com.dao;

import com.entity.User;
import com.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:43
 * @Version 1.0
 */
public interface UserDao {
    public User findUserByUsername(String username);
    public List<User> findAllUsers();
    public void test();
    public int deleteUserByUserId(int userId);
    public int insertUserRoleByRoleId(@Param("userId") int userId, @Param("roleId") int roleId);
    public int deleteUserRoleByRoleId(@Param("userId") int userId, @Param("roleId") int roleId);
    public int addUser(User user);
}
