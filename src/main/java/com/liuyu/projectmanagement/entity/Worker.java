package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_worker")
public class Worker extends Base{
    @Id
    @GeneratedValue
    @Column(name = "worker_id")
    private String workerId;
    @Column(name = "worker_name")
    private String workerName;
    @Column(name = "worker_phone")
    private String workerPhone;
    @Column(name = "id_code")
    private String idCode;

    @Column(name = "bank_card")
    private String bankCard;

    @Column(name = "bank_addr")
    private String bankAddr;

    @Column(name = "project_salary")
    private Double projectSalary;
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "user_id")
    private String userId;

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankAddr() {
        return bankAddr;
    }

    public void setBankAddr(String bankAddr) {
        this.bankAddr = bankAddr;
    }

    public Double getProjectSalary() {
        return projectSalary;
    }

    public void setProjectSalary(Double projectSalary) {
        this.projectSalary = projectSalary;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
