package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.service.IWishMessageMgmtService;

@Controller
public class WishMessageController {

	@Autowired
	private IWishMessageMgmtService messageservice;

	@RequestMapping("/")
	private String showMessage() {
		return "message";
	}

	@RequestMapping("/wish")
	private String wishMessages(Map<String, Object> map) {

		// use service
		String msg = messageservice.generateMessage();
		// keep result in model attribute
		map.put("wish_generator", msg);

		// LVN
		return "wishmessage";

	}
}
