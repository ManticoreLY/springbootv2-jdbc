package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_business")
public class Business {

    @Id
    @GeneratedValue
    @Column(name = "business_id")
    private String businessId;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "project_id")
    private String projectId;

    @Column
    private Integer status;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
