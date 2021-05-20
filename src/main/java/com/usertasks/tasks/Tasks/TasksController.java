package com.usertasks.tasks.Tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksController {

    @GetMapping(path="/hello-world")
    public String DisplayName(){
        return "hello-World";
    }

    @GetMapping(path="/hello-world-param/{param}")
    public String DisplayNameWithParameters(@PathVariable String param){
        return String.format("This is new parameter %s", param);
    }
}
