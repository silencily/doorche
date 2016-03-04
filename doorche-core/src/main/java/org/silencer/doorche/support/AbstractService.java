package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service基类
 * @author gejb
 * @since 2016/3/4
 */
public abstract class AbstractService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;

}
