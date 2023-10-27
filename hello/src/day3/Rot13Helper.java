package day3;

import java.security.DomainCombiner;

public class Rot13Helper {
	public static char rotate(char achar) {
		if(achar >='a' && achar <=   'm' || achar >= 'A' && achar <='M') {
			achar=(char)(achar+13);
			
			
		}else if(achar >= 'n' && achar <=   'z' ||achar >= 'N' && achar <=   'Z') {
			achar=(char)(achar-13);
		}
		return achar;
		
	}


public static void main(String[] args) {
	String s="helo world";
	for (int i = 0; i < s.length(); i++) {
		System.out.println(rotate(rotate(s.charAt(i))));
		
	}
}
}