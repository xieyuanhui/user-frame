package cn.edu.xd.earthquake.interceptor;

import cn.edu.xd.earthquake.domain.User;

/**
 * @author LitheLight
 * @date 2019/6/26
 */
public class UserContext {

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }
}
