package com.valtech.training.patternchecker;

import java.util.List;
import java.util.Map;

public interface PatternCheckerService {
	
	Map<String, Integer> checkPatterns(List<String> phoneNumbers);
}
