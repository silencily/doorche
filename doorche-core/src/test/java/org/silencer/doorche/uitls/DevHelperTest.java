package org.silencer.doorche.uitls;

import org.junit.Test;
import org.silencer.doorche.utils.DevHelper;

/**
 * @author gejb
 * @since 2016/2/17
 */
public class DevHelperTest {
    @Test
    public void testEncodePassword() {
        String password = "admin";
        System.out.println("the password [" + password + "] encoded:[" + DevHelper.encodePassword(password) + "]");
    }
}
