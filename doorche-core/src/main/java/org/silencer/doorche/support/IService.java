/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

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
     * get实体
     *
     * @param clazz 实体类
     * @param id    实体id
     * @param <T>   实体泛型
     * @return 实体对象
     */
    public <T extends AbstractEntity> T get(Class<T> clazz, Integer id);

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

    /**
     * 如果对象已经在本session中持久化了，不做任何事<br>
     * 如果另一个与本session关联的对象拥有相同的持久化标识(identifier)，抛出一个异常<br>
     * 如果对象没有持久化标识(identifier)属性，对其调用save()<br>
     * 如果对象的持久标识(identifier)表明其是一个新实例化的对象，对其调用save()<br>
     * 如果对象是附带版本信息的（通过<version>或<timestamp>） 并且版本属性的值表明其是一个新实例化的对象，save()它。<br>
     * 否则update() 这个对象
     *
     * @param entity 实体对象
     */
    public void saveOrUpdate(AbstractEntity entity);

    /**
     * merge实体，若包含实体id则update若不包含save
     *
     * @param entity 实体对象
     */
    public void merge(AbstractEntity entity);

    /**
     * 删除实体(逻辑删除，设置isDeleted标识为“1”）
     *
     * @param clazz 实体类
     * @param id    实体对象id
     * @param <T>   实体类泛型
     */
    public <T extends AbstractEntity> void delete(Class<T> clazz, Integer id);

    /**
     * 删除实体（物理删除）
     *
     * @param clazz 实体类
     * @param id    实体对象id
     * @param <T>   实体类泛型
     */
    public <T extends AbstractEntity> void forceDelete(Class<T> clazz, Integer id);
}
