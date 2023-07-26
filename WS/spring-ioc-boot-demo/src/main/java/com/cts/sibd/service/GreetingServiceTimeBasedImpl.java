package com.cts.sibd.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service("gstbi")
public class GreetingServiceTimeBasedImpl implements GreetingService {

	@Override
	public String greetUser(String userName) {
		String greeting=null;
		
		int h = LocalDateTime.now().getHour();
		
		if(h>=3 && h<=11) greeting="Good Morning";
		else if(h>11 && h<=15) greeting="Good Noon";
		else greeting = "Good Evening";
		
		return String.format("%s %s",greeting, userName);
	}

}
