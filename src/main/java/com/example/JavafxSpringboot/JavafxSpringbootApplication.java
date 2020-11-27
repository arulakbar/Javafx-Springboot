package com.example.JavafxSpringboot;

import com.example.JavafxSpringboot.application.JavaFxApplication;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavafxSpringbootApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
