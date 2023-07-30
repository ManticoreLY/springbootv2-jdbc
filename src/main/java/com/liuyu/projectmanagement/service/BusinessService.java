package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Business;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface BusinessService extends BaseService<Business> {

    ResponsePack listAll(String projectId, String name);
    ResponsePack listAll(String projectId);

    ResponsePack saveBusiness(Business business, String projectId);
}
