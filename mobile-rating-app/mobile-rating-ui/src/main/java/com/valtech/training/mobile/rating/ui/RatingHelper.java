package com.valtech.training.mobile.rating.ui;

import com.valtech.training.mobile.ranking.MobileRankingService;
import com.valtech.training.mobile.ranking.MobileRankingServiceImpl;

public class RatingHelper {
	
	private MobileRankingService mobileRankingService=new MobileRankingServiceImpl();
;	public int getRanking (String mobile) {
		return mobileRankingService.rankMobile(mobile);
	}

}
