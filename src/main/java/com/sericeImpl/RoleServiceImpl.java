package com.sericeImpl;

import com.dao.RoleDao;
import com.entity.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 18:45
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findRoleByUserId(int userId) {
        return roleDao.findRoleByUserId(userId);
    }

    @Override
    public void addRoleByUserId(int userId, int roleId) {
        roleDao.addRoleByUserId(userId,roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public int deleteRole(int id) {
        return roleDao.deleteRole(id);
    }

    @Override
    public int addRole(String name) {
        return roleDao.addRole(name);
    }

    @Override
    public int updateRoleById(int id, String name) {
        return roleDao.updateRoleById(id, name);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
