package org.silencer.doorche.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 开发帮助工具
 * @author gejb
 * @since 2016/2/17
 */
public class DevHelper {
    /**
     * 加密密码盐值
     */
    private static final String PASSWORD_SALT = "silencer";
    /**
     * 加密密码
     *
     * @param password 明文密码
     * @return 密文密码
     */
    public static String encodePassword(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPassword = encoder.encodePassword(password, PASSWORD_SALT);
        return encodedPassword;
    }
}
