package multithreading;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

		public static void main(String[] args)  
		{	
			//ExecutorService exe = Executors.newFixedThreadPool(1);
			ThreadPoolExecutor exe = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
			ScheduledExecutorService se = Executors.newScheduledThreadPool(1);
			
			Runnable reporterThread = ()->{
				System.out.println("Report:");
				System.out.println("Active Threads"+exe.getActiveCount());
				System.out.println("Completed Threads"+exe.getCompletedTaskCount());
				
			};
			se.scheduleAtFixedRate(reporterThread, 1, 5, TimeUnit.SECONDS);
			while(true) 
			{
				Scanner s = new Scanner(System.in);
				System.out.println("\n Enter number");		
				int num = s.nextInt();
				if(num==0)
				break;

				Runnable r = ()-> {	
						System.out.print("\n"+Thread.currentThread().getName()+" First "+num+" elements of the series are: ");
						for(int i=1;i<=num;i++) {
							System.out.print(fib(i)+ " ");
						}
					};	
				exe.execute(r);
			}			
		}

		static int fib(int n)
		{
			if (n <= 1)
				return n;
			return fib(n - 1)+ fib(n - 2);
		}
}


