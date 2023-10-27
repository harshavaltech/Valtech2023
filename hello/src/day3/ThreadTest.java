package day3;

public class ThreadTest {
	public static class printerThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread()+" "+i);
			}
				
			}
			
		}
	
	
	public static class printerThreadByInterface implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread()+" "+i);
			}
		}
	}
	
	
	
	public static void main(String[] args) {
			Thread t1 = new printerThread();
			Thread t2 = new printerThread();
			t1.start();
			t2.start();
			
			new Thread(new printerThreadByInterface()).start();
			new Thread(new Runnable() {
			
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread()+" "+i);
					}
				
			
			
			
		}
	}).start();
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread()+" "+i);
			}
				
			
	}
}


