package com.le.bookweb.config;

import com.le.bookcommon.GlobalStatic;
import com.le.bookdao.entity.User;
import com.le.bookservice.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UserRealm extends AuthorizingRealm {



    @Autowired
    private IUserService userService;


    @Override
    public String getName(){
        return "commonRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof UsernamePasswordToken;
    }



    // 鉴权
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        User user = userService.selectByUsername(username);
        if(Objects.isNull(user)){
            throw new UnknownAccountException();
        }
        // 使用md5加密
        Md5Hash md5Hash = new Md5Hash(password, user.getId()+GlobalStatic.SALT);
        String result = md5Hash.toString();
        if(!user.getPassword().equals(result)){
            throw new IncorrectCredentialsException();
        }
        return  new SimpleAuthenticationInfo(username,password,getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
