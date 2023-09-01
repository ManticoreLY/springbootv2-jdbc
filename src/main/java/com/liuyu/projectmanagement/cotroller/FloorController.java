package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.RequestPack.FloorJobPack;
import com.liuyu.projectmanagement.RequestPack.FloorPack;
import com.liuyu.projectmanagement.entity.Floor;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @ResponseBody
    @GetMapping("/list/{buildingId}")
    public ResponsePack list(@PathVariable String buildingId) {
        return floorService.findAll(buildingId);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack save(@RequestBody Floor floor) {
        return floorService.save(floor);
    }

    @ResponseBody
    @PostMapping("/save/batch")
    public ResponsePack batchSave(@RequestBody FloorPack floorPack) {
        return floorService.batchSave(floorPack.getFloorNameList(), floorPack.getBuildingId());
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack update(@RequestBody Floor floor) {
        return floorService.update(floor);
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public ResponsePack remove(@PathVariable String id) {
        return floorService.remove(id);
    }
    @ResponseBody
    @GetMapping("/listFloor/{buildingId}")
    public ResponsePack listFloors(@PathVariable String buildingId) {
        return floorService.listFloors(buildingId);
    }
}
