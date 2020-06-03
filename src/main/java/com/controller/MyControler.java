package com.controller;


import com.entity.*;
import com.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author WisdomBao
 * @Date 2020/5/25 22:14
 * @Version 1.0
 */

@Controller
//@CrossOrigin(origins = "*")
//@CrossOrigin(allowCredentials="true")
public class MyControler {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissonService permissonService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SendMessage sendMessage;

    String messageCode;

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        System.out.println("login");
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录，如果没有异常则ok
        try {
            subject.login(token);
            model.addAttribute("msg", "登录成功");
//            model.addAttribute("username", token.getUsername());
//            model.addAttribute("remember", token.isRememberMe());
            return "success";
        } catch (UnknownAccountException e) {   //用户名不存在
            model.addAttribute("msg", "用户名错误");
            return "fail";
        } catch (IncorrectCredentialsException e) {     //密码错误
            model.addAttribute("msg", "密码错误");
            return "fail";
        }

    }

    @RequestMapping("add")
    @RequiresPermissions("addProduct")
     //@RequiresRoles("admin")
    public String add(){
        return "add";
    }

    @RequestMapping("delete")
    @RequiresPermissions("addOrder")
    //@RequiresRoles("productManager")
    public String delete(){
        return "delete";
    }

    @RequestMapping("addRole")
    @RequiresRoles("admin")
    public String addRole(UserRole userRole) {
        userRoleService.addRoleByUserId(userRole);
        return "success";
    }

    @RequestMapping("gotoaddRole")
    @RequiresRoles("admin")
    public String gotoaddRole(){
        return "addRole";
    }

    @RequestMapping("userMessage")
    public String userMessage(Model model){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //System.out.println(user);
        int userId = user.getId();

        List<Role> roleList = roleService.findRoleByUserId(userId);

        //List permissonList = new ArrayList();

        Set permissionSet = new HashSet();

        for (Role role : roleList) {
            // permissonList.add(permissonService.findPermissonByRoleId(role.getId()));
            for(Permisson permisson : permissonService.findPermissonByRoleId(role.getId())){
                permissionSet.add(permisson);
            }
        }

        model.addAttribute("user",user);
        model.addAttribute("role",roleList);
        model.addAttribute("permisson",permissionSet);

        return "usermessage";
    }

    @ResponseBody
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
//    @CrossOrigin(origins = "*", maxAge = 3600)
    public UserVo getUserInfo(){
        UserVo userVo = new UserVo();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        Set permissionSet = new HashSet();
        Set roleSet = new HashSet();
        for (Role role : roleList) {
            roleSet.add(role.getName());
            for(Permisson permisson : permissonService.findPermissonByRoleId(role.getId())){
                permissionSet.add(permisson.getName());
            }
        }
        userVo.setUserId(user.getId());
        userVo.setUserName(user.getName());
        userVo.setUserPermissons(permissionSet);
        userVo.setUserRoles(roleSet);
        return userVo;
    }

    @ResponseBody
    @RequestMapping("getRoleInfo")
    public List<Role> getRoleInfo(){
        return roleService.getAllRoles();
    }

    @ResponseBody
    @RequestMapping("findAllUserInfo")
    public List<UserVo> findAllUserInfo(){
        List<User> userList = userService.findAllUsers();
        List<UserVo> userVoList = new ArrayList<>();

        for(User user : userList) {
            Set roleSet = new HashSet();
            List<Role> roleList = roleService.findRoleByUserId(user.getId());
            for (Role role : roleList) {
                roleSet.add(role.getName());
            }
            UserVo userVo = new UserVo();
            userVo.setUserId(user.getId());
            userVo.setUserName(user.getName());
            userVo.setUserRoles(roleSet);
            System.out.println(userVo);
            userVoList.add(userVo);
        }
        System.out.println(userVoList);
        return userVoList;
    }

//    @ResponseBody
//    @RequestMapping("qlogin/{username}/{password}")
//    public String qlogin(@PathVariable String username,
//                         @PathVariable String password){
//        User user = userService.findUserByUsername(username);
//        if(user == null){
//            return "no user";
//        }else{
//            if(!user.getPassword().equals(password)){
//                return "wrong password";
//            }else{
//                return "success";
//            }
//        }
//    }

    @RequestMapping("qlogin")
    @ResponseBody
    public String qlogin(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "success";
        } catch (UnknownAccountException e) {   //用户名不存在
            return "no user";
        } catch (IncorrectCredentialsException e) {     //密码错误
            return "wrong password";
        }

    }

    @RequestMapping("register")
    @ResponseBody
    public String register(String username, String password, String code){
        if(!code.equals(this.messageCode)){
            return "wrong code";
        }
        Object salt = ByteSource.Util.bytes(username);
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);
        User user = new User();
        user.setName(username);
        user.setPassword(simpleHash.toString());
        userService.addUser(user);
        return "register success";
    }

    @RequestMapping("/send")
    @ResponseBody
    public String code(String phone){
        this.messageCode = "";
        String code = UUID.randomUUID().toString().substring(0,4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);
        this.messageCode = code;
        boolean isSend = sendMessage.send(phone, "SMS_190282420", param);
        if(isSend){
            // return phone + "\n" + code + "\n发送成功";
            return "code send success";
        }else{
            return "code send fail";
        }
    }
}
