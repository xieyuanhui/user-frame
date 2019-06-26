package cn.edu.xd.earthquake.service;

import cn.edu.xd.earthquake.dao.UserDao;
import cn.edu.xd.earthquake.domain.User;
import cn.edu.xd.earthquake.exception.GlobalException;
import cn.edu.xd.earthquake.result.CodeMsg;
import cn.edu.xd.earthquake.result.Result;
import cn.edu.xd.earthquake.util.JWTUtil;
import cn.edu.xd.earthquake.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LitheLight
 * @date 2019/6/25
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public Result<String> register(User user) {
        User findUser = userDao.getUser(user.getUsername());
        if (findUser != null) {
            throw new GlobalException(CodeMsg.USER_EXIST);
        }
        user.setRole("user");
        user.setPermission("normal");
        int result = userDao.insertUser(user);
        if (result == 1) {
            return Result.success("注册成功");
        } else {
            throw new GlobalException(CodeMsg.REGISTER_ERROR);
        }
    }

    public Result<String> login(User user) {
        User findUser = userDao.getUser(user.getUsername());
        if (findUser == null) {
            return Result.error(CodeMsg.USERNAME_ERROR);
        }
        String realPassword = userDao.getPassword(user.getUsername());
        if (realPassword == null || !realPassword.equals(user.getPassword())) {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        return Result.success(JWTUtil.createToken(user.getUsername()));
    }

    public Result<String> updatePassword(String username, String oldPassword, String newPassword) {
        String dataBasePassword = userDao.getPassword(username);
        if (dataBasePassword.equals(oldPassword)) {
            userDao.updatePassword(username, newPassword);
        } else {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        return Result.success("更新成功");
    }

    public User getByToken(HttpServletRequest request) {
        String token = request.getHeader("Token");
        if (token == null) {
            return null;
        }
        String username = JWTUtil.getUsername(token);
        return userDao.getUser(username);
    }
}
