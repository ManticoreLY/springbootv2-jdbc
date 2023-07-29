package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.RequestPack.FloorJobPack;
import com.liuyu.projectmanagement.entity.FloorJob;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.FloorJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/floorJob")
public class FloorJobController {

    @Autowired
    private FloorJobService floorJobService;

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack save(@RequestBody FloorJob floorJob) {
        return floorJobService.save(floorJob);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack update(@RequestBody FloorJob floorJob) {
        return floorJobService.update(floorJob);
    }

    @ResponseBody
    @PostMapping("/relate/batch")
    public ResponsePack handleBatch(@RequestBody FloorJobPack floorJob) {
            return floorJobService.batchSaveFloorJob(floorJob.getFloorList(), floorJob.getJobIdList());
    }

    @ResponseBody
    @GetMapping("/list/{jobId}")
    public ResponsePack listFloorByJobId(@PathVariable String jobId) {
        return floorJobService.findFloorByJobId(jobId);
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public ResponsePack remove(@PathVariable String id) {
        return floorJobService.remove(id);
    }

    @ResponseBody
    @GetMapping("/jobInfo")
    public ResponsePack listFloorJobInfo(@RequestParam String buildingId) {
        return floorJobService.listFloorJobInfo(buildingId);
    }

    @ResponseBody
    @GetMapping("/finish/{id}")
    public ResponsePack setFinishCount(@PathVariable String id, @RequestParam Double finishCount) {
        return floorJobService.setFinishCount(id, finishCount);
    }

    @ResponseBody
    @GetMapping("/progress")
    public ResponsePack generateProgress(@RequestParam String floorId) {
        return floorJobService.setFloorProgress(floorId);
    }
}
