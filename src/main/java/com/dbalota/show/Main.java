package com.dbalota.show;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dbalota.show.services.UserService;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-beans.xml");
		App app = context.getBean("app", App.class);
		System.out.println(app.getUserService().getById(1));
	}
}
