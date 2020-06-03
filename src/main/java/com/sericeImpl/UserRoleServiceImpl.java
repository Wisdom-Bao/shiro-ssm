package com.sericeImpl;

import com.dao.UserRoleDao;
import com.entity.UserRole;
import com.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WisdomBao
 * @Date 2020/5/27 11:22
 * @Version 1.0
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void addRoleByUserId(UserRole userRole) {
        userRoleDao.addRoleByUserId(userRole);
    }
}
