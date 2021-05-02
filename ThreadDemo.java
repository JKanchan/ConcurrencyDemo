package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadDemo {

	public static void main(String[] args)  
	{		
		List<Thread> threadList = new ArrayList<>();
		
		Runnable status = ()->{
			while(true) {
				try {
					printthreadStates(threadList);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		};
		Thread threadReporter = new Thread(status);
		threadReporter.setDaemon(true);
		threadReporter.start();
		while(true) 
		{
			Scanner s = new Scanner(System.in);
			System.out.println("\n Enter number");		
			int num = s.nextInt();
			if(num==0)
			{
				threadReporter.interrupt();
				try {
				System.out.println("Waiting for all the threads to finish");
				joiningThreads(threadList);				
				}
				catch(InterruptedException e) {
					System.out.println("Got Interrupted Exception");
				}
				break;
			}

			Runnable r = ()-> {	
					System.out.print("\n"+Thread.currentThread().getName()+" First "+num+" elements of the series are: ");
					for(int i=1;i<=num;i++) {
						System.out.print(fib(i)+ " ");
					}
				};				
			
			Thread t = new Thread(r);
			threadList.add(t);	
			t.setDaemon(true);
			t.start();
			
		}
		
	}

	static int fib(int n)
	{
		if (n <= 1)
			return n;
		return fib(n - 1)+ fib(n - 2);
	}
	
	static void printthreadStates(List<Thread> list) {
		System.out.print("\n Thread states are:");
		for(Thread t : list)
			System.out.print(t.getState()+ " ");
	}
	static void joiningThreads(List<Thread> list) throws InterruptedException {
		for(Thread t : list)
			t.join();
		
	}

}
