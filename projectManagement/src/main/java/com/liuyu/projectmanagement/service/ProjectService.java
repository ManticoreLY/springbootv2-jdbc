package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Project;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

    ResponsePack findAll();

    ResponsePack findById(String projectId);

    ResponsePack queryUserProjectList(String userId);

    ResponsePack saveProject(Project project, String userId);

    ResponsePack updateProject(Project project);

    ResponsePack removeProject(String projectId);

    ResponsePack updateStatus(String projectId, String status);
}
