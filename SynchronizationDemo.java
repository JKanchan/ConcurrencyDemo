package multithreading;

public class SynchronizationDemo {
	static int count = 0;
	public static void main(String[] args) 
	{
		Integer x = 10;
		Runnable r = ()->{
			synchronized(x) {
				count++;
				System.out.println(Thread.currentThread().getName()+"returns value of Count as "+ count + " after increment");
				count--;
				System.out.println(Thread.currentThread().getName()+"returns value of Count as "+ count+ " after decrement");
		}};
			for(int i=0;i<5;i++) {
				Thread t = new Thread(r);
				t.start();

			}
	}

}
