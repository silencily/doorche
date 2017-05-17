/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.admin.sm.service.DictService;
import org.silencer.doorche.entity.TsmDict;
import org.silencer.doorche.support.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gejb
 * @since 2016-04-27
 */
@Transactional
@Service
public class DictServiceImpl extends AbstractService implements DictService {

    @Transactional(readOnly = true)
    public List<TsmDict> list() {
        DetachedCriteria dc = DetachedCriteria.forClass(TsmDict.class);
        dc.add(Restrictions.isNull("parent"));
        return list(dc);
    }

    public List<TsmDict> listByParentId(Integer parentId) {
        DetachedCriteria dc = DetachedCriteria.forClass(TsmDict.class);
        dc.add(Restrictions.eq("parent.id",parentId));
        return list(dc);
    }


}
