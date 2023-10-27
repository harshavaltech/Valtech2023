package day5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class DateSample {
	public static void main(String[] args)
 
	{
		Date date = new Date(101, 0, 11);
		LocalDate l = LocalDate.of(1947, 5, 15);
		System.out.println(date);
		System.out.println(l);
		LocalDateTime LT = LocalDateTime.of(2014, 11, 5, 11, 55);
		System.out.println(LT);
	}
 
}
