package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.User;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/all")
    public ResponsePack findAll(@RequestParam String userName, @RequestParam String phoneNum, @RequestParam String status) {
        return userService.findAll(userName, phoneNum, status);
    }

   @RequestMapping(value = "/query/{id}",method = RequestMethod.GET)
   @ResponseBody
    public ResponsePack getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
    public List<User> queryByProjectId(@PathVariable String projectId) {
       return userService.findUserListByProjId(projectId);
    }

    @ResponseBody
    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public ResponsePack login(@RequestBody Map<String, String> map) {
        return userService.userLogin(map.get("phoneNum"), map.get("password"));
    }

    @ResponseBody
    @RequestMapping(value="/status/{status}", method = RequestMethod.GET)
    public ResponsePack updateStatus(@PathVariable String status, @RequestParam String userId) {
        return userService.setStatus(userId, status);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponsePack saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponsePack updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponsePack removeUser(@PathVariable String id) {
        return userService.removeUserById(id);
    }
}
