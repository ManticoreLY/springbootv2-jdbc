package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.Worker;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @ResponseBody
    @GetMapping("/list")
    public ResponsePack listAll(@CookieValue String projectId) {
        return workerService.findAll(projectId);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack saveJob(@RequestBody Worker worker, @CookieValue String projectId) {
        return workerService.save(worker, projectId);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack updateJob(@RequestBody Worker worker) {
        return workerService.update(worker);
    }

    @ResponseBody
    @DeleteMapping("/remove/{workerId}")
    public ResponsePack removeJob(@PathVariable String workerId) {
        return workerService.remove(workerId);
    }

    @ResponseBody
    @GetMapping("/job/{workerId}")
    public ResponsePack listJob(@PathVariable String workerId) {
        return workerService.listWorkerJob(workerId);
    }
}
