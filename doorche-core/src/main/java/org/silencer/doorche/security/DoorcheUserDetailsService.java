package org.silencer.doorche.security;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.entity.TsmPermission;
import org.silencer.doorche.entity.TsmRole;
import org.silencer.doorche.entity.TsmUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author gejb
 * @since 2015/12/7
 */
public class DoorcheUserDetailsService implements UserDetailsService {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TsmUser.class);
        criteria.add(Restrictions.eq("isDeleted", TsmUser.IS_FLAG_NO));
        criteria.add(Restrictions.eq("loginName", username));
        List<TsmUser> users = (List<TsmUser>) criteria.list();
        Set<DoorcheGrantedAuthority> authorities = new HashSet<DoorcheGrantedAuthority>();
        if (users.size() > 0) {
            TsmUser user = users.get(0);
            Set<TsmRole> tsmRoles = user.getTsmRoles();
            for (TsmRole role : tsmRoles) {
                if (TsmRole.IS_FLAG_NO.equals(role.getIsDisable())) {
                    Set<TsmPermission> tsmPermissions = role.getTsmPermissions();
                    Map<Integer, DoorchePermission> doorchePermissionMap = new HashMap<Integer, DoorchePermission>();//菜单权限集合
                    Map<String, DoorchePermission> opratePermissionMap = new HashMap<String, DoorchePermission>();//操作权限集合
                    for (TsmPermission tsmPermission : tsmPermissions) {
                        if (TsmPermission.IS_FLAG_NO.equals(tsmPermission.getIsShow())) {
                            continue;
                        }
                        int type = Integer.parseInt(tsmPermission.getType());
                        DoorchePermission doorchePermission = new DoorchePermission();
                        doorchePermission.setId(tsmPermission.getId());
                        doorchePermission.setName(tsmPermission.getName());
                        doorchePermission.setSort(tsmPermission.getSort());
                        doorchePermission.setIcon(tsmPermission.getIcon());
                        doorchePermission.setHref(tsmPermission.getHref());
                        doorchePermission.setPermission(tsmPermission.getPermission());
                        switch (type) {
                            case TsmPermission.CATALOG_TYPE:
                                if (!doorchePermissionMap.containsKey(tsmPermission.getId())) {
                                    doorchePermissionMap.put(tsmPermission.getId(), doorchePermission);
                                }
                                break;
                            case TsmPermission.MENU_TYPE:
                                TsmPermission parent = tsmPermission.getParent();
                                if (doorchePermissionMap.containsKey(parent.getId())) {
                                    doorchePermissionMap.get(parent.getId()).addChild(doorchePermission);
                                } else {
                                    DoorchePermission doorchePermission2 = new DoorchePermission();
                                    doorchePermission2.setId(parent.getId());
                                    doorchePermission2.setName(parent.getName());
                                    doorchePermission2.setSort(parent.getSort());
                                    doorchePermission2.setIcon(parent.getIcon());
                                    doorchePermission2.addChild(doorchePermission);
                                    doorchePermissionMap.put(parent.getId(), doorchePermission2);
                                }
                                break;
                            case TsmPermission.OPERATE_TYPE:
                                opratePermissionMap.put(doorchePermission.getPermission(), doorchePermission);
                                break;
                        }
                    }
                    DoorcheGrantedAuthority authority = new DoorcheGrantedAuthority(role.getCode());
                    authority.setMenuPermissions(doorchePermissionMap);
                    authority.setOperationPermissions(opratePermissionMap);
                    authorities.add(authority);
                }
            }
            String password = user.getPassword();
            boolean enabled = TsmUser.IS_FLAG_NO.equals(user.getIsDisable());
            Integer userId = user.getId();
            String no = user.getNo();
            String chName = user.getName();
            String email = user.getEmail();
            String mobile = user.getMobile();
            userDetails = new DoorcheUserDetails(userId, no, chName, email, mobile, username, password, enabled, authorities);
        } else {
            throw new UsernameNotFoundException("username:'" + username + "' not found.");
        }
        return userDetails;
    }
}
