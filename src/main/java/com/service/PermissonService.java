package com.service;

import com.entity.Permisson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:45
 * @Version 1.0
 */
public interface PermissonService {
    public List<Permisson> findPermissonByRoleId(int roleId);
    public List<Permisson> getAllPermissions();
    public int deletePermissionById(int id);
    public int updatePermissionById(int id, String name);
    public int addPermission(String name);
    public Set<String> getPermissionByRoleId(int id);
    public Set<String> getPermissionName();
    public int insertPermissionForRole(int rid, int pid);
    public int deletePermissionForRole(int rid, int pid);
    public Permisson findPermissionByName(String name);
    public int deleteAllPermissionByRoleId(int id);
}
