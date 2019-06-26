package cn.edu.xd.earthquake.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LitheLight
 * @date 2019/6/25
 */
@Setter
@Getter
@ToString
public class CodeMsg {

    /**
    *
    */
    private int code;
    /**
    *
    */
    private String msg;

    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg NOT_LOGIN = new CodeMsg(500102, "尚未登录");
    public static CodeMsg NOT_ROLE = new CodeMsg(500103, "没有权限");
    public static CodeMsg USER_EXIST = new CodeMsg(500104, "用户已存在");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(500105, "注册失败");
    public static CodeMsg AUTH_ERROR = new CodeMsg(500106, "权限错误");
    public static CodeMsg USERNAME_ERROR = new CodeMsg(500106, "用户名错误");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500106, "密码错误");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
