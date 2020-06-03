package com.config;

import com.entity.Permisson;
import com.entity.Role;
import com.entity.User;
import com.service.PermissonService;
import com.service.RoleService;
import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @Author WisdomBao
 * @Date 2020/5/17 9:56
 * @Version 1.0
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissonService permissonService;

    //授权

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("shou quan");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = user.getId();

        List<Role> roleList = roleService.findRoleByUserId(userId);

        // Set permissionSet = new HashSet();

        for (Role role : roleList) {
            info.addRole(role.getName());
            for(Permisson permisson : permissonService.findPermissonByRoleId(role.getId())){
                info.addStringPermission(permisson.getName());
            }
        }

        // System.out.println("permisson:"+permissonList);

//        for(int i=0; i<permissonList.size(); i++){
//            System.out.println("mei yi ge permisson"+permissonList.get(i));
//        }

//        Collection roleCollection = roleList;
//        Collection permissonCollection = permissonList;
//        info.addStringPermissions(permissonCollection);
//        info.addRoles(roleList);
         //info.addStringPermission("admin");
         // info.addRole("admin");
//
        //拿到当前登录的对象
//        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User)subject.getPrincipal();
//        //设置当前用户权限
//        if(currentUser.getPerms()!=null){
//            info.addStringPermission(currentUser.getPerms());
//        }
        return info;
        // return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // System.out.println("ren zheng");
        // System.out.println(roleService.findRoleByUserId(1));

//        String username = (String)token.getPrincipal();
//
//        User user = userService.findUserByUsername(username);
//
//        if (user == null) {
//                        // 用户名不存在抛出异常
//                         System.out.println("认证：当前登录的用户不存在");
//                         throw new UnknownAccountException();
//                     }
//                 String pwd = user.getPassword();
//                 return new SimpleAuthenticationInfo(user, pwd, getName());


        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
//
//        System.out.println("userToken是:"+userToken.getUsername());
//        System.out.println(String.valueOf(userToken.getPassword()));
//
        User user = userService.findUserByUsername(userToken.getUsername());
//        System.out.println("user"+user);
        if(user==null){
            return null;
       }
//
        Object salt = ByteSource.Util.bytes(userToken.getUsername());
////        Object salt = ByteSource.Util.bytes(userToken.getUsername());
        SimpleHash simpleHash = new SimpleHash("MD5", String.valueOf(userToken.getPassword()), salt, 1);
        String realPassword = simpleHash.toString();
//        System.out.println("real"+realPassword);
        userToken.setPassword(realPassword.toCharArray());
//
//
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
        //return new SimpleAuthenticationInfo(user,user.getPwd(), ByteSource.Util.bytes(userToken.getUsername()),"UserRealm");
        // return null;
    }
}
