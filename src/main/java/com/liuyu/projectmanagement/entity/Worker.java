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

    @Column(name = "project_salary")
    private Double projectSalary;
    @Column(name = "project_id")
    private String projectId;

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
}
