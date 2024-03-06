package com.example.spring.web.tomcat.mvc;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author:majin.wj
 */

@RestController
public class NewYearController {

    public Result<String> myWife() {
        Result<String> result = new Result<>();
        result.setData("王");
        return result;
    }

}
