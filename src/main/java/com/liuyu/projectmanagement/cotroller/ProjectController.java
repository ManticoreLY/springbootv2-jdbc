package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.Project;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponsePack findAll() {
        return projectService.findAll();
    }

    @ResponseBody
    @GetMapping(value = "/find/{id}")
    public ResponsePack findProject(@PathVariable String id) {
        return projectService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponsePack listProjects(@CookieValue String userId) {
        return projectService.queryUserProjectList(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponsePack save(@RequestBody Project project, @CookieValue String userId) {
        return projectService.saveProject(project, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponsePack update(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @ResponseBody
    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public ResponsePack updateStatus(@RequestParam String projectId, @PathVariable String status) {
        return projectService.updateStatus(projectId, status);
    }

    @ResponseBody
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponsePack remove(@PathVariable String id) {
        return projectService.removeProject(id);
    }

}
