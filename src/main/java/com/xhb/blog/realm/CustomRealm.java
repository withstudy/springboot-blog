package com.xhb.blog.realm;


import com.xhb.blog.entity.ActiveUser;
import com.xhb.blog.entity.User;
import com.xhb.blog.service.PermissionService;
import com.xhb.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiveUser activeUser= (ActiveUser) principalCollection.getPrimaryPrincipal();
       // System.out.println(activeUser);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       // List roles=activeUser.getRoles();
        info.addStringPermissions(activeUser.getFunctions());
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        //String userPwd = new String((char[]) authenticationToken.getCredentials());
        User user= userService.findUserByName(userName);
        //System.out.println(user.toString());
        //根据用户名从数据库获取密码
        //String password = "5be1ac6418314812cc1763365618fb9f";

        ActiveUser activeUser=null;
        if(null!=user){
            //权限列表
            List<String> functions=permissionService.findPermissionByUid(user.getId());
            activeUser=new ActiveUser(user,functions);
            ByteSource salt= ByteSource.Util.bytes(user.getUsername());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(),salt,getName());
            return info;
       }else{
            throw new AccountException("用户名不正确");
        }

    }
}
