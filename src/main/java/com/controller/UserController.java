package com.controller;

import com.entity.Role;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/6/1 21:07
 * @Version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("deleteUserByUserId/{userId}")
    public int deleteUserByUserId(@PathVariable("userId") int userId){
        return userService.deleteUserByUserId(userId);
    }

    @ResponseBody
    @RequestMapping("updateUserRole/{userId}/{roleIdSet}")
    public void updateUserRole(@PathVariable("userId") int userId,
                              @PathVariable("roleIdSet") Set<Integer> roleIdSet){
        List<Role> roleSet = roleService.findRoleByUserId(userId);
        Set<Integer> roles = new HashSet<>();
        for(Role role : roleSet){
            roles.add(role.getId());
        }
        Set<Integer> add = new HashSet<>();
        Set<Integer> delete = new HashSet<>();
        add.addAll(roleIdSet);
        add.removeAll(roles);
        delete.addAll(roles);
        delete.removeAll(roleIdSet);
        for(Integer roleAdd : add){
            userService.insertUserRoleByRoleId(userId, roleAdd);
        }
        for(Integer roleDelete : delete){
            userService.deleteUserRoleByRoleId(userId, roleDelete);
        }
    }

}
