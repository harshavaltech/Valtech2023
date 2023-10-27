package day2;

public class Outer {
	private int x;
	 public Inner CreateInner() {
		 return new Inner();
		 
		 
		 
		 
	 }
	 
	 
	 
	 
	 public static interface abcd{
		 
		 
	 }
	 
	 
	 
	 public abstract static class StaticInner implements abcd{
		 public void jumpBy10(Outer o) {
			 
			o.x=o.x+10;
	 }
		 
		 public abstract void jumpBy100(Outer o);
	 
		 
	 }
	private class Inner {
		public void printX() {
			System.out.println("X= "+x);
		}
		public void Increment() {
			x++;
		}
	}
	
	public static void main(String[] args) {
		Outer o=new Outer();
		Outer.Inner i= o.CreateInner();
		Outer.Inner i1= o.CreateInner();
		System.out.println(i);
		System.out.println(i1);
		i.Increment();
		i.printX();
		new Outer().CreateInner().printX();
		StaticInner si= new StaticInner() {
			@Override
			public void jumpBy100(Outer o) {
				// TODO Auto-generated method stub
				o.x=o.x+100;
			}
		};
		si.jumpBy10(o);
		si.jumpBy100(o);
		i.printX();
	}
	
	
	
}