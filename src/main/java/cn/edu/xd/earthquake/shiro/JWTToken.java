package cn.edu.xd.earthquake.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author LitheLight
 * @date 2019/6/25
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
