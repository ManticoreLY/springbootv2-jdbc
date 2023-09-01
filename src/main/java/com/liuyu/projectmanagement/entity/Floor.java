package com.liuyu.projectmanagement.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_floor")
public class Floor extends Base{

    @Id
    @GeneratedValue
    @Column(name = "floor_id")
    private String floorId;

    @Column(name = "floor_name")
    private String floorName;

    @Column(name = "floor_info")
    private String floorInfo; // 楼层信息

    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "progress_per")
    private double progressPer; // 进度

    @Column(name = "index")
    private Integer orderIndex; // 排序

    @Transient
    private List<FloorJob> floorJobs;

    @Transient
    private String jobId;

    @Transient
    private String jobName;

    @Transient
    private Integer jobStatus;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getFloorInfo() {
        return floorInfo;
    }

    public void setFloorInfo(String floorInfo) {
        this.floorInfo = floorInfo;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

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

    public double getProgressPer() {
        return progressPer;
    }

    public void setProgressPer(double progressPer) {
        this.progressPer = progressPer;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public List<FloorJob> getFloorJobs() {
        return floorJobs;
    }

    public void setFloorJobs(List<FloorJob> floorJobs) {
        this.floorJobs = floorJobs;
    }
}
