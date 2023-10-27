package day2;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import hello.Point;



public class UtilsTest {
	
	public static void testStack(Stack<Point>Points) {
		Points.push(new Point(2,3));
		Points.push(new Point(2,36));
		Points.push(new Point(2,35));
		System.out.println("Size="+Points.size());
		
		System.out.println(Points.pop());
		System.out.println(Points.pop());
		System.out.println("Size="+Points.size());
		
	}
	
	public static void testMap(Map<String,Point>Points) {
		Points.put("2,3",new Point(2,3));
		Points.put("2,6",new Point(2,3));
		Points.put("3,2",new Point(2,3));
		Points.put("2,3",new Point(2,4));
		System.out.println("Size="+Points.size());
		System.out.println(Points);
		
	}
	public static void testList(List<Point>Points) {
		Point p =new Point(2,3);
		Point p1 = new Point(2,3);
		Points.add(p);
		Points.add(p1);
		Points.add(p);
		Points.add(new Point(3,2));
		System.out.println("Size="+Points.size());
		System.out.println(Points);
		
	}
	public static void testSet(Set<Point>Points) {
		Point p =new Point(2,3);
		Point p1 = new Point(2,3);
		Points.add(p);
		Points.add(p1);
		Points.add(p);
		Points.add(new Point(3,2));
		System.out.println("Size="+Points.size());
		System.out.println(Points);
		
		
		
	}
	public static void main(String[] args) {
		testSet(new HashSet<>());
		testSet(new TreeSet<>());
		testList(new ArrayList<Point>());
		testList(new LinkedList<Point>());
		testMap(new HashMap<>());
		testStack(new Stack<>());
	}

}
