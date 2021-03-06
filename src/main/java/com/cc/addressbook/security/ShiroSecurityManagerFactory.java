package com.cc.addressbook.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;

/**
 * @author cclaudiu
 *         <p/>
 *         <br/>
 */
public final class ShiroSecurityManagerFactory {
    private static final String SHIRO_INI_LOCALIZATION = "classpath:shiro.ini";
    private static SecurityManager securityManager;

    private ShiroSecurityManagerFactory() {
    }


    public static void initShiro() {
        if (securityManager == null) {
            IniSecurityManagerFactory factory = new IniSecurityManagerFactory(SHIRO_INI_LOCALIZATION);
            securityManager = factory.getInstance();
        }

        SecurityUtils.setSecurityManager(securityManager);
    }
}
