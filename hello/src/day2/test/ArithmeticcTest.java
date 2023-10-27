package day2.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import day2.ArithmeticImpl;
import day2.Arithmeticc;
import day2.DivideByZeroException;

class ArithmeticcTest {
	
	private Arithmeticc arithmeticc=new ArithmeticImpl();
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all...");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("after all...");
	}
	
	@BeforeEach
	void  anything() {
		System.out.println("Init...");
		arithmeticc=new ArithmeticImpl();
	}
	@AfterEach
	
	void anything1() {
		System.out.println("Destroy..");
		
	}

	@Test @DisplayName("To check if the +ve nos,-ve nos are added correctly..")
	void testAdd() {
		assertEquals(5,arithmeticc.add(2,3));
		assertEquals(5,arithmeticc.add(3,2));
		assertEquals(5,arithmeticc.add(5,0));
		assertEquals(5,arithmeticc.add(0,5));
		assertEquals(-5,arithmeticc.add(-2,-3));
		assertEquals(-5,arithmeticc.add(-8,3));
		
	}

	@Test
	void testSub() {
		
	}

	
	@Test
	
	
	void testDivByZero() {
		assertThrows(DivideByZeroException.class,()->arithmeticc.div(5,0));
	}
	
	@Test
	void testMul() {
		
	}
	
	static Stream<Arguments>addArgsGenerator(){
		return Stream.of(Arguments.of(1,2,3),
				Arguments.of(-1,-2,-3),
				Arguments.of(1,-2,-1),
				Arguments.of(1,-2,-1),
				Arguments.of(-1,2,1));
		
	}
	
	@ParameterizedTest(name = "Add with CSV {0}+{1}={2}")
	@MethodSource(value= {"addArgsGenerator"})
	
	void testwithMethod(int a,int b,int c) {
		assertEquals(c, arithmeticc.add(a, b));
	}
	
	
	@ParameterizedTest(name = "Add with CSV {0}+{1}={2}")
	@CsvFileSource(files= {"add.csv"})
	void testWithCSVFile(int a,int b,int c) {
		assertEquals(c, arithmeticc.add(a, b));
	}
	
	
	
	
	@ParameterizedTest(name = "Add with CSV {0}+{1}={2}")
	@CsvSource(value= {"2,3,5","-2,3,1","5,-2,3","-1,-1,-2"})
	
	void testwithCSVParams(int a,int b,int c) {
		assertEquals(c, arithmeticc.add(a, b));
	}
	
	@ParameterizedTest(name = "Add With {0}")
	@ValueSource(strings = {"2,3,5","-2,3,1","5,-2,3","-1,-1,-2"})
	void testAddWithParams(String value) {
		String[] parts= value.split(",");
		List<Integer> values=Arrays.asList(parts).stream().map(it-> Integer.parseInt(it)).collect(Collectors.toList());
		assertEquals(values.get(2),arithmeticc.add(values.get( 0),values.get(1)));
	}

	@Test
	void testDiv() {
		assertEquals(1.666,5.0/3,0.001,"value not in range");
		assertEquals(2, arithmeticc.div(4, 2));
		assertEquals(2, arithmeticc.div(-6, -3));
		try {
		assertEquals(2, arithmeticc.div(4, 0));
		fail("should throws Divide by zero exeption");
		}catch(DivideByZeroException ex) {
	}

	}
}
