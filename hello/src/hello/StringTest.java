package hello;

public class StringTest {
	public static void main(String[] args) {
		String s ="Hello World";
		String s1 ="Hello World";
		System.out.println(s==s1);
		final String s2="Hello ";
		String s3=s2 +"World";
		System.out.println(s==s3);
		
		
	}

}