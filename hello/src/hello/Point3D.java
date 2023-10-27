package hello;

public class Point3D extends Point {
	public static final Point3D ORIGIN= new Point3D();
	private int z;
	
	public Point3D() {
		System.out.println("point 3d -no parameter");
	}
	
	@Override
	public double distance() {
		// TODO Auto-generated method stub
		return Math.sqrt(x*x+y*y+z*z);
	}
	public Point3D(int x,int y,int z) {
		super(x,y);
		System.out.println("IN const of point 3D");
		this.z=z;
		
	}
	@Override
	public boolean equals(Object obj) {
		Point3D p=(Point3D) obj;
		
		// TODO Auto-generated method stub
		return p.x==x && p.y==y && p.z==z;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return toString().hashCode();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x = "+ x +" y = "+ y + " z = " + z + "";}
		
		////public double distance(Point3D p) {
		//	System.out.println("point 3d --called disatnce method-point 3d p ");
			//return distance(p.x,p.y,p.z);
		
		

		
	//}
	public static void main(String[] args) {
		ORIGIN.z=10;
		System.out.println(ORIGIN.distance());
		System.out.println(Point.ORIGIN.distance());
		Point p=new Point(10,20);
	System.out.println(p);
		Point3D P =new Point3D(10,20,30);
		//double dis =p.distance()new Point(20,30);
		}

}
