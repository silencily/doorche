/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

import org.silencer.doorche.cache.SystemDictionaryCache;
import org.silencer.doorche.cache.SystemParameterCache;
import org.silencer.doorche.context.SpringContextHolder;

/**
 * @author gejb
 * @since 2016-04-12
 */
public class SystemConfigUtil {
    /**
     * 根据类型编码和值编码获取字典值
     *
     * @param typeCode 类型编码
     * @param code     值编码
     * @return 字典值
     */
    public static String getDictValue(String typeCode, String code) {
        SystemDictionaryCache systemDictionaryCache = SpringContextHolder.getBean(SystemDictionaryCache.class);
        return systemDictionaryCache.getValue(typeCode, code);
    }

    /**
     * 根据key获取系统参数值
     *
     * @param key key
     * @return 参数值
     */
    public static String getParamValue(String key) {
        SystemParameterCache systemParameterCache = SpringContextHolder.getBean(SystemParameterCache.class);
        return systemParameterCache.getValue(key);
    }

}
