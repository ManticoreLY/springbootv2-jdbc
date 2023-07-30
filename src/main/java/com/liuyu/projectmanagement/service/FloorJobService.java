package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Floor;
import com.liuyu.projectmanagement.entity.FloorJob;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.repository.FloorJobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FloorJobService extends BaseService<FloorJob>{

    ResponsePack batchSaveFloorJob(List<Floor> floorList, List<String> jobIdList);

    ResponsePack findFloorByJobId(String jobId);

    ResponsePack listFloorJobInfo(String buildingId);

    ResponsePack findAll(String projectId);

    ResponsePack save(FloorJob floorJob, String projectId);

    ResponsePack setFinishCount(String id, Double finishCount);

    ResponsePack setFloorProgress(String floorId);
}
