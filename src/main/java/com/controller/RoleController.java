package com.controller;

import com.entity.Permisson;
import com.service.PermissonService;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/31 11:50
 * @Version 1.0
 */

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissonService permissonService;

    @ResponseBody
    @RequestMapping("deleteRoleById/{id}")
    public int deletePermissionById(@PathVariable int id){
        permissonService.deleteAllPermissionByRoleId(id);
        return roleService.deleteRole(id);
    }

    @ResponseBody
    @RequestMapping("addRole/{name}")
    public int addRole(@PathVariable String name){
        return roleService.addRole(name);
    }

    @ResponseBody
    @RequestMapping("addRoleAndPermission/{name}/{permissionSet}")
    public void addRoleAndPermission(@PathVariable String name,
                                     @PathVariable Set<String> permissionSet){
        roleService.addRole(name);
        int rid = roleService.getRoleByName(name).getId();
        for(String permisson : permissionSet){
            int pid = permissonService.findPermissionByName(permisson).getId();
            permissonService.insertPermissionForRole(rid, pid);
        }
    }

}
