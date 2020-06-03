package com.sericeImpl;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:45
 * @Version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public void test() {
        System.out.println("aaaaaa");
    }

    @Override
    public int deleteUserByUserId(int userId) {
        return userDao.deleteUserByUserId(userId);
    }

    @Override
    public int insertUserRoleByRoleId(int userId, int roleId) {
        return userDao.insertUserRoleByRoleId(userId, roleId);
    }

    @Override
    public int deleteUserRoleByRoleId(int userId, int roleId) {
        return userDao.deleteUserRoleByRoleId(userId, roleId);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
