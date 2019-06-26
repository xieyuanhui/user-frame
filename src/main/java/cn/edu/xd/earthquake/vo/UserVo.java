package cn.edu.xd.earthquake.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LitheLight
 * @date 2019/6/25
 */
@Getter
@Setter
@ToString
public class UserVo {

    /**
     * 用户名
     */
    private String username;
    /**
     *
     */
    private String role;
    /**
     *
     */
    private String permission;
    private Double latitude;
    private Double longitude;

}
