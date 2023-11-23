package com.valtech.training.patternchecker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternCheckerServiceImpl implements  PatternCheckerService{
	
	

	public static void main(String[] args) {

	}

	@Override
	public Map<String, Integer> checkPatterns(List<String> phoneNumbers) {

		
		Map<String, Integer> phoneNumbersWithScores = new HashMap<String, Integer>();

		for (String phoneNumber : phoneNumbers) {
			int score = 0;

			if (isAllDigitsSame(phoneNumber)) {
				score += 6;
			}

			if (isPalindrome(phoneNumber)) {
				score += 5;
			}

			if (hasRepeatedDigit(phoneNumber)) {
				score += 4;
			}

			if (hasAscendingSequence(phoneNumber)) {
				score += 3;
			}

			if (hasDescendingSequence(phoneNumber)) {
				score += 2;
			}

			if (hasConsecutiveSequence(phoneNumber)) {
				score += 1;
			}

			phoneNumbersWithScores.put(phoneNumber, score);

		}

		return phoneNumbersWithScores;
	}

	private static boolean isAllDigitsSame(String phoneNumber) {
		return phoneNumber.matches("^(.)\\1*$");
	}

	private static boolean isPalindrome(String phoneNumber) {
		String reversed = new StringBuilder(phoneNumber).reverse().toString();
		return phoneNumber.equals(reversed);
	}

	private static boolean hasRepeatedDigit(String phoneNumber) {
		return phoneNumber.matches(".*(\\d)\\1.*");
	}

	public static boolean hasAscendingSequence(String phoneNumber) {
      if (phoneNumber == null || phoneNumber.length() < 10) {
          return false;
      }

      for (int i = 0; i <= phoneNumber.length() - 4; i++) {
          if (isAscending(phoneNumber.substring(i, i + 4))) {
              return true;
          }
      }

      return false;
  }

  private static boolean isAscending(String substring) {
      boolean isAscending = true;
      for (int i = 0; i < substring.length() - 1; i++) {
          if (substring.charAt(i + 1) <= substring.charAt(i)) {
              isAscending = false;
              break;
          }
      }
      return isAscending;
  }

	public static boolean hasDescendingSequence(String phoneNumber) {
      if (phoneNumber == null || phoneNumber.length() < 10) {
          return false;
      }

      for (int i = 0; i <= phoneNumber.length() - 4; i++) {
          if (isDescending(phoneNumber.substring(i, i + 4))) {
              return true;
          }
      }

      return false;
  }

  private static boolean isDescending(String substring) {
      boolean isDescending = true;
      for (int i = 0; i < substring.length() - 1; i++) {
          if (substring.charAt(i + 1) >= substring.charAt(i)) {
              isDescending = false;
              break;
          }
      }
      return isDescending;
  }

	public static boolean hasConsecutiveSequence(String phoneNumber) {
      if (phoneNumber == null || phoneNumber.length() < 10) {
          return false;
      }

      for (int i = 0; i <= phoneNumber.length() - 4; i++) {
          if (isConsecutive(phoneNumber.substring(i, i + 4))) {
              return true;
          }
      }

      return false;
  }

  private static boolean isConsecutive(String substring) {
      for (int i = 0; i < substring.length() - 1; i++) {
          if (substring.charAt(i + 1) - substring.charAt(i) != 1) {
              return false;
          }
      }
      return true;
  }

}
