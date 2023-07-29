package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Floor;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface FloorService {

    ResponsePack findAll(String buildingId);

    ResponsePack save(Floor floor);

    ResponsePack update(Floor floor);

    ResponsePack remove(String floorId);

    ResponsePack listFloors(String buildingId);

}
