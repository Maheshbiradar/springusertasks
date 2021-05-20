package com.usertasks.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksController {

    @GetMapping(path="/hello-world")
    public String DisplayName(){
        return "hello-World";
    }
}
