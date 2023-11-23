package com.valtech.training.mobileratingui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.valtech.training.mobileranking.MobileRankingService;
import com.valtech.training.mobileranking.MobileRankingServiceImpl;
import com.valtech.training.patternchecker.PatternCheckerService;
import com.valtech.training.patternchecker.PatternCheckerServiceImpl;

public class RatingHelper {

	public void dispalyNumbers(List<String> sortedPhoneNumbers) {
		for (String phoneNumber : sortedPhoneNumbers) {
			System.out.println(phoneNumber);
		}
	}

	public static void main(String[] args) {

		List<String> phoneNumbers = Arrays.asList("5551234567", "5559876543", "5558765432", "5552345678", "5553456789",
				"5558765432", "5556543210", "5558901234","5554321098", "5555678901");
		PatternCheckerService patternCheckerService = new PatternCheckerServiceImpl();
		Map<String, Integer> phoneNumbersWithScores = patternCheckerService.checkPatterns(phoneNumbers);
		for (Map.Entry<String, Integer> entry : phoneNumbersWithScores.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();
			System.out.println(key+":"+val);
		}
		MobileRankingService mobileRankingService = new MobileRankingServiceImpl();
		List<String> sortedPhoneNumbers = mobileRankingService.rankPhoneNumbers(phoneNumbersWithScores);
		RatingHelper ratingHelper = new RatingHelper();
		ratingHelper.dispalyNumbers(sortedPhoneNumbers);

	}

}
