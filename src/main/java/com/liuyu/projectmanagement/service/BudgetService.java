package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.Budget;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface BudgetService extends BaseService<Budget> {
    ResponsePack findAll(String projectId, String name);

    ResponsePack save(Budget budget, String projectId);
}
