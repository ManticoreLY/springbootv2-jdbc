package com.liuyu.projectmanagement.RequestPack;

import com.liuyu.projectmanagement.entity.Floor;

import java.util.List;

public class FloorPack {

    private List<String> floorNameList;

    private String buildingId;

    public List<String> getFloorNameList() {
        return floorNameList;
    }

    public void setFloorNameList(List<String> floorNameList) {
        this.floorNameList = floorNameList;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
}
