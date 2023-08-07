package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Worker;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface WorkerService {

    ResponsePack findAll(String projectId);

    ResponsePack save(Worker worker, String projectId);

    ResponsePack update(Worker worker);

    ResponsePack remove(String workerId);

    ResponsePack listWorkerJob(String workerId);

    ResponsePack findWorkerId(String userId);
}
