package org.vaadin.spring.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TutorialApplication {
	
    public static void main(String[] args) {
		try {
			SpringApplication.run(TutorialApplication.class, args);
		} catch (Throwable e) {
			if (e.getClass().getName().contains("SilentExitException")) {
				log.debug("Spring is restarting the main thread - See spring-boot-devtools");
			} else {
				log.error("Application crashed!", e);
			}
		}
    }
}
