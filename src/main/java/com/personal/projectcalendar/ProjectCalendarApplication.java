package com.personal.projectcalendar;

import com.personal.projectcalendar.dependencies.DaggerServiceComponents;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectCalendarApplication {
	public static final ServiceComponents components 	= DaggerServiceComponents.create();
	public static final String API_NAME 				= "api";
	public static final String VERSION 					= "3.0";

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", String.format("/%s/v%s", API_NAME, VERSION));
		SpringApplication.run(ProjectCalendarApplication.class, args);
	}
}
