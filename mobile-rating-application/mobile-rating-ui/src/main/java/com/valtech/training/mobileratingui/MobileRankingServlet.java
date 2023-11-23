package com.valtech.training.mobileratingui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.valtech.training.mobileranking.MobileRankingService;
import com.valtech.training.mobileranking.MobileRankingServiceImpl;
import com.valtech.training.patternchecker.PatternCheckerService;
import com.valtech.training.patternchecker.PatternCheckerServiceImpl;

public class MobileRankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MobileRankingServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String phoneNumbers = request.getParameter("phoneNumbers");
		List<String> phoneNumbersList = Arrays.asList(phoneNumbers.split(","));

		PatternCheckerService patternCheckerService = new PatternCheckerServiceImpl();
		MobileRankingService mobileRankingService = new MobileRankingServiceImpl();

		List<String> rankedPhoneNumbers = mobileRankingService
				.rankPhoneNumbers(patternCheckerService.checkPatterns(phoneNumbersList));

		request.setAttribute("rankedPhoneNumbers", rankedPhoneNumbers);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

}
