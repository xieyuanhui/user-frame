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
public class Result<T> {

    /**
    * 返回信息码
    */
    private int code;
    /**
    * 返回信息
    */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setMsg("success");
        result.setCode(200100);
        return result;
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
