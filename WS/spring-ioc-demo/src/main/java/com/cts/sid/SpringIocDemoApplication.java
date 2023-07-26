package com.cts.sid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cts.sid.service.Counter;

public class SpringIocDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(SpringIocDemoConfig.class);
		
		Counter c1 = (Counter) context.getBean("counter");
		System.out.println(c1.nextCount());
		System.out.println(c1.nextCount());
		
		Counter c2 = (Counter) context.getBean("counter");
		System.out.println(c2.nextCount());
		System.out.println(c2.nextCount());
		
	}

}
