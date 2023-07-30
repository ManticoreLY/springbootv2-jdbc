package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_cash_account")
public class CashAccount extends Base{

    @Id
    @GeneratedValue
    @Column
    private String id;

    @Column(name = "business_id")
    private String businessId;

    @Transient
    private String businessName;

    @Column
    private Double cash;

    @Column(name = "person_id")
    private String personId;

    @Transient
    private String personName;

    @Column(name = "project_id")
    private String projectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
