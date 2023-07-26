package com.cts.sid.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cts.sid.service.GreetingService;

@Component
public class HomeUI {

	@Autowired
	@Qualifier("gssi")
	private GreetingService gs1;

	@Autowired
	@Qualifier("gstbi")
	private GreetingService gs2;
	
	@Autowired
	private Scanner scan;
	
	public void run() {
		System.out.println("User Name: ");
		String userName = scan.nextLine();
		System.out.println(gs1.greetUser(userName));
		System.out.println(gs2.greetUser(userName));
	}
}
