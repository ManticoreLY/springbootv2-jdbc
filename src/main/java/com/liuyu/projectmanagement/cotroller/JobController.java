package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.Job;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @ResponseBody
    @GetMapping("/list")
    public ResponsePack listAll(@CookieValue String projectId) {
        return jobService.findAll(projectId);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack saveJob(@RequestBody Job job, @CookieValue String projectId) {
        return jobService.save(job, projectId);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack updateJob(@RequestBody Job job) {
        return jobService.update(job);
    }

    @ResponseBody
    @DeleteMapping("/remove/{jobId}")
    public ResponsePack removeJob(@PathVariable String jobId) {
        return jobService.remove(jobId);
    }
}
