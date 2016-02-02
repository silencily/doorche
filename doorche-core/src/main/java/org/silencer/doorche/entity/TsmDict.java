package org.silencer.doorche.entity;

import javax.persistence.*;

/**
 * @author gejb
 * @since 2015/12/7
 */
@Entity
@Table(name = "T_SM_DICT")
public class TsmDict extends AbstractEntity {
    private TsmDict parent;
    private String typeName;
    private String typeCode;
    private String name;
    private String code;
    private Integer sort;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public TsmDict getParent() {
        return parent;
    }

    public void setParent(TsmDict parent) {
        this.parent = parent;
    }

    @Column(name = "TYPE_NAME")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "TYPE_CODE")
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
