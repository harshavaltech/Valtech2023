package com.valtech.training.mobile.rating.ui;


import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RatingHelperTest extends TestCase{

	@Test
	public void test() {
		RatingHelper helper=new RatingHelper();
		Assert.assertEquals(0,helper.getRanking("9898989898"));
	}

}
