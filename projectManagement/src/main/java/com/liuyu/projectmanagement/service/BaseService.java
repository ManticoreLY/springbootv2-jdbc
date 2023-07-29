package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.pack.ResponsePack;

public interface BaseService<T> {

    ResponsePack findAll(String projectId);

    ResponsePack findAll();

    ResponsePack save(T t, String projectId);

    ResponsePack save(T t);

    ResponsePack update(T t);

    ResponsePack remove(String id);
    ResponsePack findById(String id);
}
