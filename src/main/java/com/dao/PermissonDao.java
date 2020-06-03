package com.dao;

import com.entity.Permisson;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:44
 * @Version 1.0
 */
public interface PermissonDao {
    public List<Permisson> findPermissonByRoleId(int roleId);
    public List<Permisson> getAllPermissions();
    public int deletePermissionById(int id);
    public int updatePermissionById(@Param("id") int id, @Param("name") String name);
    public int addPermission(String name);
    public Set<Permisson> getPermissionByRoleId(int id);
    public int insertPermissionForRole(@Param("rid") int rid, @Param("pid") int pid);
    public int deletePermissionForRole(@Param("rid") int rid, @Param("pid") int pid);
    public Permisson findPermissionByName(String name);
    public int deleteAllPermissionByRoleId(int id);
}
