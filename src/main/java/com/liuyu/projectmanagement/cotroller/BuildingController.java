package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.Building;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @ResponseBody
    @GetMapping("/list")
    public ResponsePack listAll(@CookieValue String projectId) {
        return buildingService.findAll(projectId);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack save(@RequestBody Building building, @CookieValue String projectId) {
        return buildingService.save(building, projectId);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack update(@RequestBody Building building) {
        return buildingService.update(building);
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public ResponsePack remove(@PathVariable String id) {
        return buildingService.remove(id);
    }
}
