/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author gejb
 * @since 2016-06-04
 */
@MappedSuperclass
public class AbstractWorkFlowEntity extends AbstractEntity {
    private String processInstanceId;//流程实例id

    @Column(name = "PROCESS_INSTANCE_ID")
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
