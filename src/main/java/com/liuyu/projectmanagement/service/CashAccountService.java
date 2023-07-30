package com.liuyu.projectmanagement.service;

import com.liuyu.projectmanagement.entity.CashAccount;
import com.liuyu.projectmanagement.pack.ResponsePack;
import org.springframework.stereotype.Service;

@Service
public interface CashAccountService extends BaseService<CashAccount> {

    ResponsePack listAll(String projectId, String businessId, String personId, String start, String end);

    ResponsePack save(CashAccount cashAccount, String projectId);
}
