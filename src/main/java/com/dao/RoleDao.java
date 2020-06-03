package com.dao;

import com.entity.Permisson;
import com.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:43
 * @Version 1.0
 */
public interface RoleDao {
    public List<Role> findRoleByUserId(int userId);
    public void addRoleByUserId(int userId, int roleId);
    public List<Role> getAllRoles();
    public int deleteRole(int id);
    public int addRole(String name);
//    public int addRolePermissions(@Param("permissionSet") int pid, @Param("roleId") int rid);
    public int updateRoleById(@Param("id") int id, @Param("name") String name);
    public Role getRoleByName(String name);
}
