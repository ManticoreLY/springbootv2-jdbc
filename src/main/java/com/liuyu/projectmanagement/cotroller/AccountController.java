package com.liuyu.projectmanagement.cotroller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.liuyu.projectmanagement.entity.CashAccount;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.CashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private CashAccountService cashAccountService;

    @ResponseBody
    @GetMapping("/list")
    public ResponsePack list(@CookieValue String projectId, @RequestParam String businessId, @RequestParam String personId, @RequestParam String start, @RequestParam String end) throws ParseException {
        if (StringUtils.isEmpty(start) && StringUtils.isEmpty(end)) {
            return  cashAccountService.listAll(projectId, businessId, personId, null, null);
        }
        return cashAccountService.listAll(projectId, businessId, personId, start, end);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack save(@RequestBody CashAccount cashAccount, @CookieValue String projectId) {
        return cashAccountService.save(cashAccount, projectId);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack update(@RequestBody CashAccount cashAccount) {
        return cashAccountService.update(cashAccount);
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public ResponsePack remove(@PathVariable String id) {
        return cashAccountService.remove(id);
    }
}
