package com.valtech.training.mobile.rating.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.valtech.training.mobile.ranking.MobileRanker;
import com.valtech.training.pattern.checker.PatternChecker;

/**
 * Servlet implementation class MobileRankingServlet
 */
public class MobileRankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MobileRankingServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String phoneNumbersInput = request.getParameter("phoneNumbers");
        String[] phoneNumbersArray = phoneNumbersInput.split("\n");
        List<String> phoneNumbers = Arrays.asList(phoneNumbersArray);

        Map<String, Integer> phoneScores = PatternChecker.checkPatterns(phoneNumbers);
        List<String> rankedPhoneNumbers = MobileRanker.rankMobiles(phoneScores);
        
        request.setAttribute("rankedPhoneNumbers", rankedPhoneNumbers);
        
        request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
