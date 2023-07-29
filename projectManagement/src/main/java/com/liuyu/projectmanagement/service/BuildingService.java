package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Building;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface BuildingService {

    ResponsePack findAll(String projectId);

    ResponsePack save(Building building, String projectId);

    ResponsePack update(Building building);

    ResponsePack remove(String buildingId);

}
