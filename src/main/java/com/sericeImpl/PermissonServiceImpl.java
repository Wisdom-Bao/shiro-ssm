package com.sericeImpl;

import com.dao.PermissonDao;
import com.entity.Permisson;
import com.service.PermissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:46
 * @Version 1.0
 */
@Service
public class PermissonServiceImpl implements PermissonService {

    @Autowired
    private PermissonDao permissonDao;

    @Override
    public List<Permisson> findPermissonByRoleId(int roleId) {
        return permissonDao.findPermissonByRoleId(roleId);
    }

    @Override
    public List<Permisson> getAllPermissions() {
        return permissonDao.getAllPermissions();
    }

    @Override
    public int deletePermissionById(int id) {
        return permissonDao.deletePermissionById(id);
    }

    @Override
    public int updatePermissionById(int id, String name) {
        return permissonDao.updatePermissionById(id, name);
    }

    @Override
    public int addPermission(String name) {
        return permissonDao.addPermission(name);
    }

    @Override
    public Set<String> getPermissionByRoleId(int id) {
        Set<Permisson> permissons = permissonDao.getPermissionByRoleId(id);
        Set<String> myPermission = new HashSet<>();
        for(Permisson permisson : permissons){
            myPermission.add(permisson.getName());
        }
        return myPermission;
    }

    @Override
    public Set<String> getPermissionName() {
        List<Permisson> permissons = permissonDao.getAllPermissions();
        Set<String> permissionNames = new HashSet<>();
        for(Permisson permisson : permissons){
            permissionNames.add(permisson.getName());
        }
        return permissionNames;
    }

    @Override
    public int insertPermissionForRole(int rid, int pid) {
        return permissonDao.insertPermissionForRole(rid, pid);
    }

    @Override
    public int deletePermissionForRole(int rid, int pid) {
        return permissonDao.deletePermissionForRole(rid, pid);
    }

    @Override
    public Permisson findPermissionByName(String name) {
        return permissonDao.findPermissionByName(name);
    }

    @Override
    public int deleteAllPermissionByRoleId(int id) {
        return permissonDao.deleteAllPermissionByRoleId(id);
    }
}
