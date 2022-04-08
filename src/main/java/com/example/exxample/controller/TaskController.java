package com.example.exxample.controller;

import com.example.exxample.entity.Task;
import com.example.exxample.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("test")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/get")
    public String getAll(Model model) {

        List<Task> taskList = taskService.getAll();

        System.out.println(taskList.toString());
        model.addAttribute("taskList", taskList);
        model.addAttribute("taskSize", taskList.size());
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/test/get";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.save(task);

        return "redirect:/test/get";
    }
}
