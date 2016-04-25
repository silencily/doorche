/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security.tags;

/**
 * @author gejb
 * @since 2016-04-26
 */
public class HasPermissionTag extends PermissionTag {
    @Override
    protected boolean showTagBody(String p) {
        return isPermitted(p);
    }
}
