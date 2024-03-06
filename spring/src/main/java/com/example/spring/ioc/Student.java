package com.example.spring.ioc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author majin.wj
 * @date 2023/6/27 9:56 PM
 * @desc
 */
@Component
@Profile({"dev","pre"})
public class Student {
}
