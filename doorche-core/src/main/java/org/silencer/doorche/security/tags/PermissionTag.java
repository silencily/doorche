/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security.tags;

import org.silencer.doorche.security.DoorchePermission;
import org.silencer.doorche.security.DoorcheUserDetails;
import org.silencer.doorche.security.SecurityContextHelper;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Map;

/**
 * @author gejb
 * @since 2016-04-26
 */
public abstract class PermissionTag extends TagSupport {
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void verifyAttributes() throws JspException {
        String permission = getName();

        if (permission == null || permission.length() == 0) {
            String msg = "The 'name' tag attribute must be set.";
            throw new JspException(msg);
        }
    }

    public int doStartTag() throws JspException {

        verifyAttributes();

        return internalDoStartTag();
    }

    protected int internalDoStartTag() throws JspException {
        String p = getName();

        boolean show = showTagBody(p);
        if (show) {
            return TagSupport.EVAL_BODY_INCLUDE;
        } else {
            return TagSupport.SKIP_BODY;
        }
    }

    protected boolean isPermitted(String p) {
        DoorcheUserDetails userDetails = (DoorcheUserDetails) SecurityContextHelper.obtainCurrentSecurityUser();
        Map<String, DoorchePermission> operations = userDetails.getOperationPermissions();
        return operations.containsKey(p);
    }

    protected abstract boolean showTagBody(String p);

}
