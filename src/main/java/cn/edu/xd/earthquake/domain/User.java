package cn.edu.xd.earthquake.domain;

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
public class User {
    /**
     * 用户ID
     */
    private Long id;
    /**
    * 用户名
    */
    private String username;
    /**
    *
    */
    private String password;
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
