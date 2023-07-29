package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_job")
public class Job extends Base{

    @Id
    @GeneratedValue
    @Column(name = "job_id")
    private String jobId;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_unit")
    private String jobUnit; // 工序单位

    @Column(name = "job_price")
    private Double jobPrice;

    @Column(name = "project_id")
    private int projectId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobUnit() {
        return jobUnit;
    }

    public void setJobUnit(String jobUnit) {
        this.jobUnit = jobUnit;
    }

    public Double getJobPrice() {
        return jobPrice;
    }

    public void setJobPrice(Double jobPrice) {
        this.jobPrice = jobPrice;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
