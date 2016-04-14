/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

import org.silencer.doorche.entity.AbstractEntity;

import java.util.List;

/**
 * 基础service
 *
 * @author gejb
 * @since 2016-03-27
 */
public interface IService {
    /**
     * 获取hibernatetemplate
     *
     * @return HibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate();

    /**
     * 查询，默认带分页数据
     *
     * @param clazz 实体类
     * @param <T>   实体泛型
     * @return 实体结果集
     */
    public <T extends AbstractEntity> List<T> list(Class<T> clazz);

    /**
     * load实体
     *
     * @param clazz 实体类
     * @param id    实体id
     * @param <T>   实体泛型
     * @return 实体对象
     */
    public <T extends AbstractEntity> T load(Class<T> clazz, Integer id);

    /**
     * 新增保存
     *
     * @param entity 实体对象
     */
    public void save(AbstractEntity entity);

    /**
     * 修改保存
     *
     * @param entity 实体对象
     */
    public void update(AbstractEntity entity);
}
