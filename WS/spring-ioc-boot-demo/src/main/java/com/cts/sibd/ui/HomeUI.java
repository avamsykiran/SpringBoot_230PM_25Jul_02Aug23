package com.cts.sibd.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.sibd.service.GreetingService;

@Component
public class HomeUI implements CommandLineRunner{

	@Autowired
	@Qualifier("gssi")
	private GreetingService gs1;

	@Autowired
	@Qualifier("gstbi")
	private GreetingService gs2;
	
	@Autowired
	private Scanner scan;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("User Name: ");
		String userName = scan.nextLine();
		System.out.println(gs1.greetUser(userName));
		System.out.println(gs2.greetUser(userName));
	}
}
