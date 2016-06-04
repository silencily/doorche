/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service;

import org.silencer.doorche.entity.TsmDict;
import org.silencer.doorche.support.IService;

import java.util.List;

/**
 * @author gejb
 * @since 2016-04-27
 */
public interface DictService extends IService {
    /**
     * 查询字典列表，只包含第一层级
     * @return
     */
    public List<TsmDict> list();

    /**
     * 根据parentId查询编码
     * @param parentId
     * @return
     */
    public List<TsmDict> listByParentId(Integer parentId);
}
