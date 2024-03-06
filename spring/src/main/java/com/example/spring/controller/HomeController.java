package com.example.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author majin.wj
 * @date 2023/6/13 3:32 PM
 * @desc
 *
 *
 *
 *
 *
 */
@RestController
@RequestMapping("/home")
public class HomeController  {

    @GetMapping("/see")
    public String see() {
        return "welcome(~)";
    }

    @GetMapping("/get")
    public String get() {
        return "get";
    }

    @PostMapping("/destroy")
    public String destroy() {
        return "destroy";
    }


}
