package day2;

public class SimpleInterstImpl implements SimpleInterst {
	private Arithmeticc arithmeticc;
	public SimpleInterstImpl(Arithmeticc arthimeticc) {
		this.arithmeticc=arthimeticc;
		
		
	}

	

	public void setArithmeticc(Arithmeticc arithmeticc) {
		this.arithmeticc = arithmeticc;
	}

	@Override
	public double computeSimpleInterst(int p, int roi, int time) {
		int result=arithmeticc.mul(p, roi);
		result=arithmeticc.mul(result, time);
		
		// TODO Auto-generated method stub
		return arithmeticc.div(result, 100);
	}
	
public static void main(String[] args) {
	Arithmeticc arithmeticc=new ArithmeticImpl();
	SimpleInterst si=new SimpleInterstImpl(arithmeticc);
	System.out.println(si.computeSimpleInterst(200, 3, 4));
			
	
}
}
