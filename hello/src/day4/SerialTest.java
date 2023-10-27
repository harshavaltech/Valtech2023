package day4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hello.Point;
import hello.Point3D;

public class SerialTest {

	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
Point p =new Point(2,3);

Point3D p2 = new Point3D();
System.out.println(p2);
System.out.println(p);
ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("objects")));
oos.writeObject(p);
oos.flush();
oos.close();

ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("objects")));
Point p1=(Point) ois.readObject();
//System.out.println(p1);
System.out.println(p==p1);

			}

}
