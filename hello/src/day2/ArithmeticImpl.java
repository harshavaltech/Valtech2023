package day2;

public class ArithmeticImpl implements Arithmeticc {

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public int mul(int a, int b) {
		// TODO Auto-generated method stub
	
		return a*b;
	}

	@Override
	public int div(int a, int b) throws DivideByZeroException {
		// TODO Auto-generated method stub
		if (b==0) {
			throw new DivideByZeroException("zero cant be used in Denominator");
		}
		return a/b;
		
	}
	public static void main(String[] args) {
		Arithmeticc a= new ArithmeticImpl();
		System.out.println(a.add(2,3));
		System.out.println(a.sub(2,3));
		System.out.println(a.mul(2,3));
		try {
			System.out.println(a.div(5,0));
		} catch (DivideByZeroException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
		
		}finally {
			System.out.println("seen always");
		}
		System.out.println(a.mul(2, 3));
	
	}
}