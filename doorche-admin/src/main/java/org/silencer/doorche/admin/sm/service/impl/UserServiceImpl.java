/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service.impl;

import org.silencer.doorche.admin.sm.service.UserService;
import org.silencer.doorche.support.AbstractService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author gejb
 * @since 2016-03-27
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {
    @Value("security.password.default")
    private String defaultPassword;
    @Value("security.password.encoder.salt")
    private String passwordSalt;

    public String getDefaultPassword() {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPassword = encoder.encodePassword(defaultPassword, passwordSalt);
        return encodedPassword;
    }
}
