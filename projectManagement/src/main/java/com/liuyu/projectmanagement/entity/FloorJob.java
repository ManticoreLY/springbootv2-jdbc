package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_floor_job")
public class FloorJob extends Base{

    @Id
    @GeneratedValue
    @Column
    private String id;

    @Column(name = "floor_id")
    private String floorId;

    @Transient
    private String buildingName;
    @Transient
    private String floorName;

    @Transient
    private Double progressPer;

    @Column(name = "job_id")
    private String jobId;

    @Transient
    private String jobName;

    @Transient
    private String jobUnit;

    @Transient
    private Double jobPrice;
    @Column(name = "worker_id")
    private String workerId;

    @Transient
    private String workerName;

    @Column(name = "job_count")
    private Double jobCount; // 总数

    @Column(name = "finishCount")
    private Double finishCount; // 已完成数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Double getProgressPer() {
        return progressPer;
    }

    public void setProgressPer(Double progressPer) {
        this.progressPer = progressPer;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Double getJobPrice() {
        return jobPrice;
    }

    public void setJobPrice(Double jobPrice) {
        this.jobPrice = jobPrice;
    }

    public String getJobUnit() {
        return jobUnit;
    }

    public void setJobUnit(String jobUnit) {
        this.jobUnit = jobUnit;
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

    public Double getJobCount() {
        return jobCount;
    }

    public void setJobCount(Double jobCount) {
        this.jobCount = jobCount;
    }

    public Double getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Double finishCount) {
        this.finishCount = finishCount;
    }
}
