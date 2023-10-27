package day3;

import java.util.Random;

public class EmployeeGenerator {

	int age;

	String firstname;

	int experience;

	int level;

	Random random = new Random();

 

	public void nameGenertor() {

 

		String inputChar = "vwhbohjbdcjbkbadfbabfvhakirmwj";

		String firstname = "";

		int len = random.nextInt(3, 8);

		char[] text = new char[len];

 

		for (int i = 0; i < len; i++) {

			if (i == 0) {

				char s = inputChar.charAt(random.nextInt(inputChar.length()));

				text[i] = Character.toUpperCase(s);

			} else {

				text[i] = inputChar.charAt(random.nextInt(inputChar.length()));

			}

		}

		for (int j = 0; j < text.length; j++) {

			firstname += Character.toString(text[j]);

		}

 

		String lastname = "";

		int len1 = random.nextInt(0, 6);

		char[] text1 = new char[len1];

		for (int j = 0; j < text1.length; j++) {

			if (j == 0) {

				char s = inputChar.charAt(random.nextInt(inputChar.length()));

				text1[j] = Character.toUpperCase(s);

			} else {

				text1[j] = inputChar.charAt(random.nextInt(inputChar.length()));

			}

		}

		for (int j = 0; j < text1.length; j++) {

			lastname += Character.toString(text1[j]);

		}

		System.out.println("Name=" + firstname + " " + lastname);

 

	}

 

	public void ageGenerator() {

		age = random.nextInt(22, 60);

		System.out.println("Age=" + age);

 

	}

//AGE 

	public void experianceGenerator() {

		if (age == 22)

			experience = 0;

		if (age == 23)

			experience = random.nextInt(0, 1);

		if (age == 24)

			experience = random.nextInt(0, 2);

		if (age == 25)

			experience = random.nextInt(0, 3);

		if (age >= 26) {

 

			experience = age - random.nextInt(22, 26);

		}

 

		System.out.println("Experience=" + experience);

 

	}

 
//LEVEL
	public void levelGenerator() {

		if (experience < 3) {

			level = random.nextInt(1, 2);

		} else if (experience < 5 && experience > 3) {

			level = random.nextInt(2, 3);

 

		} else if (experience < 8 && experience > 5) {

			level = random.nextInt(3, 5);

 

		} else {

			level = 5;

		}

		System.out.println("L" + level);

	}

 

	public void details() {

		nameGenertor();

		ageGenerator();

		experianceGenerator();

		levelGenerator();

	}

	public static void main(String[] args) {

		EmployeeGenerator e1 = new EmployeeGenerator();

		e1.details();

 

	}

}
