package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service基类
 * @author gejb
 * @since 2016/3/4
 */
public abstract class AbstractService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;

    public <T extends AbstractEntity> List<T> listByCriteria(Criteria criteria){
        return null;
    }

}
