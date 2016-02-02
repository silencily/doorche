package org.silencer.doorche.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gejb
 * @since 2015/12/7
 */
@Entity
@Table(name = "T_SM_USER")
public class TsmUser extends AbstractEntity {
    private String loginName;
    private String password;
    private String no;
    private String name;
    private String email;
    private String mobile;
    private String isDisable;
    private String loginIp;
    private Date loginTime;
    private Set<TsmRole> tsmRoles = new HashSet<TsmRole>();

    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "NO")
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "IS_DISABLE")
    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    @Column(name = "LOGIN_IP")
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "LOGIN_TIME")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    //关系维护端
    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(name = "T_SM_USER_ROLE", inverseJoinColumns = @JoinColumn(name = "ROLE_ID"),
            joinColumns = @JoinColumn(name = "USER_ID"))
    public Set<TsmRole> getTsmRoles() {
        return tsmRoles;
    }

    public void setTsmRoles(Set<TsmRole> tsmRoles) {
        this.tsmRoles = tsmRoles;
    }
}
