package com.valtech.training.mobile.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.valtech.training.pattern.checker.PatternChecker;

public class MobileRanker {

	public static List<String> rankMobiles(Map<String, Integer> phoneScores) {
		List<Map.Entry<String, Integer>> entries = new ArrayList<>(phoneScores.entrySet());

		
		Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }

        });

		
		List<String> sortedPhoneNumbers = new ArrayList<>();

				for (Map.Entry<String, Integer> entry : entries) {
			sortedPhoneNumbers.add(entry.getKey());
		}

		return sortedPhoneNumbers;
	}


}
