package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.service.ISeasonFinderMgmtService;

@Controller
public class SeasonFinderController {

	@Autowired
	private ISeasonFinderMgmtService seasonService;

	@RequestMapping("/")
	public String season() {
		return "welcome";
	}

	@RequestMapping("/season")
	public String showSeason(Map<String, Object> map) {
		// use service
		String msg = seasonService.findSeason();
		// keep result in model attribute
		map.put("season_msg", msg);
		// return Lvn[logical view name]
		return "displayseason";

	}
}
