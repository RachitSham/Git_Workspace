package com.nt.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service("findservice")
public class SeasonFinderServiceImpl implements ISeasonFinderMgmtService {

	@Override
	public String findSeason() {
		// get system date
		LocalDate ld = LocalDate.now();

		// get current month
		int month = ld.getMonthValue();

		// find season
		if (month >= 3 && month <= 6)
			return "Summer Season";

		if (month >= 7 && month <= 9)
			return "Rainy Season";

		else
			return "Winter Season";
	}

}
