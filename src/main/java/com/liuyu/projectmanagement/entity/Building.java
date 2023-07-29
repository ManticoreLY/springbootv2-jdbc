package com.liuyu.projectmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_building")
public class Building extends Base{

    @Id
    @GeneratedValue
    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "project_id")
    private String project_id;


    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

}
