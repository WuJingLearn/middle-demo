package org.javaboy.builder.base;

import lombok.Data;
import org.javaboy.singleton.PrintStudent;

@Data
public class RequestParam {

    private String name;
    private Integer age;

    private Integer score;


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Integer age;

        private Integer score;

        public Builder() {
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }


        public Builder score(Integer score) {
            this.score = score;
            return this;
        }

        public RequestParam build() {
            RequestParam requestParam = new RequestParam();
            requestParam.setName(name);
            requestParam.setAge(age);
            requestParam.setScore(score);
            return requestParam;
        }


    }

}
