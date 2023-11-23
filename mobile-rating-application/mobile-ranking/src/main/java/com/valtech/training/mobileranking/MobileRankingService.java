package com.valtech.training.mobileranking;

import java.util.List;
import java.util.Map;

public interface MobileRankingService {
	
	List<String> rankPhoneNumbers(Map<String, Integer> phoneNumbersWithScores);
}
