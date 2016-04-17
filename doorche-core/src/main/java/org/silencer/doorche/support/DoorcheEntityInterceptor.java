/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.silencer.doorche.entity.AbstractEntity;
import org.silencer.doorche.security.SecurityContextHelper;

import java.io.Serializable;

/**
 * hibernate 实体拦截器
 *
 * @author gejb
 * @since 2016-04-16
 */
public class DoorcheEntityInterceptor extends EmptyInterceptor {

    @Override
    public Boolean isTransient(Object entity) {
        if (entity instanceof AbstractEntity) {
            //根据id是否为空判断实体状态从而决定saveOrUpdate策略，空：瞬时态(save)，不为空：游离态(update)
            Integer id = ((AbstractEntity) entity).getId();
            return id == null ? true : false;
        }
        return super.isTransient(entity);
    }
}
