package com.website.cibercrime;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "cibercrime")
public class CibercrimeApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(CibercrimeApplication.class, args);
	}

}
