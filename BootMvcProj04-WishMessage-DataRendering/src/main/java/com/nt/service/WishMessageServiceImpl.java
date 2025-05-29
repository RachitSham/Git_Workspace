package com.nt.service;



import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service("wishservice")
public class WishMessageServiceImpl implements IWishMessageMgmtService {

	@Override
	public String generateMessage() {
		// get system time
		LocalTime lt = LocalTime.now();
		
		// get current hour
		int hour = lt.getHour();
		
		// find hour
		if(hour < 12)
			return "Good Morning";
		
		else if(hour < 16)
			return "Good Afternoon";
		
		else if(hour < 20)
			return "Good Evening";
		
		else
			return "Good Night";
		
	}

}
