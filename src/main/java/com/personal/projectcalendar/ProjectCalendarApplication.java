package com.personal.projectcalendar;

import com.personal.projectcalendar.dependencies.DaggerServiceComponents;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectCalendarApplication {
	public static final ServiceComponents components = DaggerServiceComponents.create();

	public static void main(String[] args) {
		SpringApplication.run(ProjectCalendarApplication.class, args);
	}
}
