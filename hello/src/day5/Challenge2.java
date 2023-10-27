package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Challenge2 {
	

	  public static void main(String[] args) {
		  List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u'); 
		  String str = "Hello How are you"; 
		  String [] words = str.split(" "); 
		      List<String> resultWords = new ArrayList<>(); 
		      OUTER_LOOP:
		      for (String word : words) { 
		      //	char[] vowels;
				for (char vw: vowels) { 
		      		if (word.indexOf(vw) == -1) { 
		      			
		      			continue OUTER_LOOP; 
		      		} 
		      	} 
		       
		      	resultWords.add(word); 
		      } 
		       System.out.println(str);
		      System.out.println(resultWords); 
		  }
	  }


