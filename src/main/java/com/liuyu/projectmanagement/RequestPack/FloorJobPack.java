package com.liuyu.projectmanagement.RequestPack;

import com.liuyu.projectmanagement.entity.Floor;

import java.util.List;

public class FloorJobPack {

    private List<Floor> floorList;

    private List<String> jobIdList;

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    public List<String> getJobIdList() {
        return jobIdList;
    }

    public void setJobIdList(List<String> jobIdList) {
        this.jobIdList = jobIdList;
    }
}
