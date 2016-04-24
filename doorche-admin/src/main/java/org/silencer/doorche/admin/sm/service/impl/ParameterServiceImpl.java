/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service.impl;

import org.silencer.doorche.admin.sm.service.ParameterService;
import org.silencer.doorche.entity.TsmParameter;
import org.silencer.doorche.support.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gejb
 * @since 2016-04-13
 */
@Transactional
@Service
public class ParameterServiceImpl extends AbstractService implements ParameterService {

    @Override
    public void update(TsmParameter tsmParameter) {
//        TsmParameter parameter = load(TsmParameter.class, tsmParameter.getId());
//        parameter.setParamKey(tsmParameter.getParamKey());
//        parameter.setParamName(tsmParameter.getParamName());
//        parameter.setParamType(tsmParameter.getParamType());
//        parameter.setParamValue(tsmParameter.getParamValue());
        saveOrUpdate(tsmParameter);
    }
}
