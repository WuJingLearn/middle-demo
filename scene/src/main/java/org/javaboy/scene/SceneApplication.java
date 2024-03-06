package org.javaboy.scene;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SceneApplication {

	public static void main(String[] args) {
		MDC.put("userId","zssss");
		SpringApplication.run(SceneApplication.class, args);
	}

}
