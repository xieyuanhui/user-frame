package cn.edu.xd.earthquake.interceptor;

import cn.edu.xd.earthquake.domain.User;
import cn.edu.xd.earthquake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LitheLight
 * @date 2019/6/26
 */
@Service
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userService.getByToken(request);
        UserContext.setUser(user);
        return true;
    }
}
