package com.cts.sibd.service;

import org.springframework.stereotype.Service;

@Service("gssi")
public class GreetingServiceSimpleImpl implements GreetingService {

	@Override
	public String greetUser(String userName) {
		return String.format("Hello %s", userName);
	}

}
