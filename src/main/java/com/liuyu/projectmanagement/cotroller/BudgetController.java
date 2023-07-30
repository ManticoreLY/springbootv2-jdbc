package com.liuyu.projectmanagement.cotroller;

import com.liuyu.projectmanagement.entity.Budget;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @ResponseBody
    @GetMapping("/list")
    public ResponsePack list(@CookieValue String projectId, @RequestParam String name) {
        return budgetService.findAll(projectId, name);
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponsePack save(@RequestBody Budget budget, @CookieValue String projectId) {
        return budgetService.save(budget, projectId);
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponsePack update(@RequestBody Budget budget) {
        return budgetService.update(budget);
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public ResponsePack remove(@PathVariable String id) {
        return budgetService.remove(id);
    }
}
