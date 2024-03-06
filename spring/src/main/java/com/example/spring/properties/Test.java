package com.example.spring.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

public class Test {

    @Data
    @Builder
    static class Stu {

        private String name;

    }

    public static void main(String[] args) {




    }
}
