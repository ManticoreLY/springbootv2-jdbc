package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.User;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponsePack findUserById(String id);

    List<User> findUserListByProjId(String projectId);

    ResponsePack removeUserById(String id);

    ResponsePack userLogin(String userName, String password);

    ResponsePack findAll(String userName, String phoneNum, String status);

    ResponsePack setStatus(String id, String status);

    ResponsePack saveUser(User user);

    ResponsePack updateUser(User user);
}
