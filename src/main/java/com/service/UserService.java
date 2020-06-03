package com.service;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:45
 * @Version 1.0
 */
public interface UserService {
    public User findUserByUsername(String username);
    public List<User> findAllUsers();
    public void test();
    public int deleteUserByUserId(int userId);
    public int insertUserRoleByRoleId(int userId, int roleId);
    public int deleteUserRoleByRoleId(@Param("userId") int userId, @Param("roleId") int roleId);
    public int addUser(User user);
}
