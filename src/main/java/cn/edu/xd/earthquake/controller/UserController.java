package cn.edu.xd.earthquake.controller;

import cn.edu.xd.earthquake.domain.User;
import cn.edu.xd.earthquake.result.CodeMsg;
import cn.edu.xd.earthquake.result.Result;
import cn.edu.xd.earthquake.service.UserService;
import cn.edu.xd.earthquake.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LitheLight
 * @date 2019/6/25
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/not_login")
    public Result<String> notLogin() {
        return Result.error(CodeMsg.NOT_LOGIN);
    }

    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @GetMapping("/not_role")
    public Result<String> notRole() {
        return Result.error(CodeMsg.NOT_ROLE);
    }

    @GetMapping("/logout")
    public Result<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("成功注销！");
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/update_password")
    public Result<String> updatePassword(@RequestParam("username") String username,
                                         @RequestParam("oldPassword") String oldPassword,
                                         @RequestParam("newPassword") String newPassword) {
        return userService.updatePassword(username, oldPassword, newPassword);
    }

    @GetMapping("/info")
    public Result<UserVo> getUserInfo(User user) {
        UserVo userVo = new UserVo();
        userVo.setRole(user.getRole());
        userVo.setUsername(user.getUsername());
        userVo.setLatitude(user.getLatitude());
        userVo.setLongitude(user.getLongitude());
        userVo.setPermission(user.getPermission());
        return Result.success(userVo);
    }
}
