package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.Business;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @ResponseBody
    @GetMapping("/list")
    public ResponsePack list(@CookieValue String projectId) {
        return businessService.listAll(projectId);
    }
    @ResponseBody
    @GetMapping("/query")
    public ResponsePack list(@CookieValue String projectId, @RequestParam String name) {
        return businessService.listAll(projectId, name);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack save(@RequestBody Business business, @CookieValue String projectId) {
        return businessService.saveBusiness(business, projectId);
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public ResponsePack remove(@PathVariable String id) {
        return businessService.remove(id);
    }
}
