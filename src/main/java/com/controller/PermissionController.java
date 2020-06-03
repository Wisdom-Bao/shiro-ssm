package com.controller;

import com.entity.Permisson;
import com.service.PermissonService;
import com.service.RoleService;
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
 * @Date 2020/5/30 17:10
 * @Version 1.0
 */

@Controller
public class PermissionController {

    @Autowired
    private PermissonService permissonService;

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("deletePermissionById/{id}")
    public int deletePermissionById(@PathVariable int id){
        return permissonService.deletePermissionById(id);
    }

    @ResponseBody
    @RequestMapping("updatePermissionById/{id}/{name}")
    public int updatePermissionById(@PathVariable int id, @PathVariable String name){
        return permissonService.updatePermissionById(id, name);
    }

    @ResponseBody
    @RequestMapping("addPermission/{name}")
    public int addPermission(@PathVariable String name){
        return permissonService.addPermission(name);
    }

    @ResponseBody
    @RequestMapping("getPermissionByRoleId/{id}")
    public Set<String> getPermissionByRoleId(@PathVariable int id){
        return permissonService.getPermissionByRoleId(id);
    }

    @ResponseBody
    @RequestMapping("getPermissionInfo")
    public List<Permisson> getPermissionInfo(){
        return permissonService.getAllPermissions();
    }

    @ResponseBody
    @RequestMapping("getPermissionName")
    public Set<String> getPermissionName(){
        return permissonService.getPermissionName();
    }

    @ResponseBody
    @RequestMapping("updatePermissionForRole/{permissionSet}/{name}/{rid}")
    public void updatePermissionForRole(@PathVariable(value = "permissionSet") Set<String> permissionSet,
                                        @PathVariable("name") String name,
                                        @PathVariable("rid") int rid){
        roleService.updateRoleById(rid, name);
        Set<String> permissons = permissonService.getPermissionByRoleId(rid);
        Set<String> add = new HashSet<>();
        Set<String> delete = new HashSet<>();
        add.addAll(permissionSet);
        add.removeAll(permissons);
        delete.addAll(permissons);
        delete.removeAll(permissionSet);
        for(String addPermission : add){
            int pid = permissonService.findPermissionByName(addPermission).getId();
            permissonService.insertPermissionForRole(rid, pid);
        }
        for(String deletePermission : delete){
            int pid = permissonService.findPermissionByName(deletePermission).getId();
            permissonService.deletePermissionForRole(rid, pid);
        }
    }

    @ResponseBody
    @RequestMapping("updateAllPermissionForRole/{name}/{rid}")
    public void deleteAllPermissionForRole( @PathVariable("name") String name,
                                            @PathVariable("rid") int rid){
        roleService.updateRoleById(rid, name);
        permissonService.deleteAllPermissionByRoleId(rid);
    }
}
