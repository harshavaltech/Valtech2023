package com.valtech.training.pattern.checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternChecker {

	@SuppressWarnings("unchecked")
	public static Map<String, Integer> checkPatterns(List<String> phoneNumbers) {

		Map<String, Integer> phoneNumberScores = new HashMap<String, Integer>();

//        List<PhoneNumberScore> fancyNumbers = new ArrayList<>();
//        @SuppressWarnings("rawtypes")
//		List<PhoneNumberScore> nonFancyNumbers = new ArrayList();

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

			phoneNumberScores.put(phoneNumber, score);

//            PhoneNumberScore phoneScore = new PhoneNumberScore(phoneNumber, score);
//
//            if (score > 0) {
//                fancyNumbers.add(phoneScore);
//            } else {
//                nonFancyNumbers.add(phoneScore);
//            }
		}
//        List<PhoneNumberScore> combinedList = new ArrayList<>();
//        combinedList.addAll(fancyNumbers);
//        combinedList.addAll(nonFancyNumbers);

		return phoneNumberScores;
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
        if (phoneNumber == null || phoneNumber.length() < 4) {
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
        if (phoneNumber == null || phoneNumber.length() < 4) {
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

	
//	public static void main(String[] args) {
//		List<String> phoneNumbers=Arrays.asList("9900135729","9916878237","9999999999");
//		
//		PatternChecker checker=new PatternChecker();
//		Map<String,Integer> answer=checker.checkPatterns(phoneNumbers);
//		System.out.println(answer);
//		
//		
//	}
}
