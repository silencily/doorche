package org.silencer.doorche.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author gejb
 * @since 2015/12/7
 */
@DynamicUpdate(true)  //若字段为null生成sql将不包含此字段
@DynamicInsert(true)
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    public static String IS_FLAG_YES = "1";
    public static String IS_FLAG_NO = "0";
    private Integer id;
    private TsmUser createBy;
    private Date createTime;
    private TsmUser updateBy;
    private Date updateTime;
    private String isDeleted = "0";
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_BY")
    public TsmUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(TsmUser createBy) {
        this.createBy = createBy;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATE_BY")
    public TsmUser getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(TsmUser updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "IS_DELETED")
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
