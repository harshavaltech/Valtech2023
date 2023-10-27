package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Streams {
	private void traditional() {
		// TODO Auto-generated method stub
		List<Integer>nums=Arrays.asList(1,2,3,4,5,6,7,8,9,10);//to store array as list
List<Integer>sqrs=new ArrayList<>();
for(int i:nums){
	sqrs.add(i*i);
}
	System.out.println(nums);
	System.out.println(sqrs);
	}
	public static void main(String[] args) {
		Streams s=new Streams();
		s.traditional();
		List<Integer>nums=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer>sqrs=nums.stream().map(i->i*i).collect(Collectors.toList());
		List<Double>sqrts=nums.stream().map(i->Math.sqrt(i)).collect(Collectors.toList());
		List<Integer>odd=nums.stream().filter(i->(i%2)==1).collect(Collectors.toList());
		System.out.println(sqrs);
		System.out.println(sqrts);
		System.out.println(odd);
	}
}
