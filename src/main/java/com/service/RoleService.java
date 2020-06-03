package com.service;

import com.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:45
 * @Version 1.0
 */
public interface RoleService {
    public List<Role> findRoleByUserId(int userId);
    public void addRoleByUserId(int userId, int roleId);
    public List<Role> getAllRoles();
    public int deleteRole(int id);
    public int addRole(String name);
    public int updateRoleById(int id, String name);
    public Role getRoleByName(String name);
}
