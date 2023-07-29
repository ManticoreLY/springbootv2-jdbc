package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Job;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface JobService {

    ResponsePack findAll(String projectId);

    ResponsePack save(Job job, String projectId);

    ResponsePack update(Job job);

    ResponsePack remove(String jobId);
}
