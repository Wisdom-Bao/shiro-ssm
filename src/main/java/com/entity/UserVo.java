package com.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author WisdomBao
 * @Date 2020/5/27 16:30
 * @Version 1.0
 */

@Data
public class UserVo {
    private int userId;
    private String userName;
    private Set<Role> userRoles;
    private Set<Permisson> userPermissons;


}
