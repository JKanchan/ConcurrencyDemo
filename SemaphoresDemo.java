package multithreading;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class SemaphoresDemo {
	public static void main(String[] args)  
	{		
		Semaphore semaphore = new Semaphore(3); //It will allow only 3 threads
		while(true) 
		{
			Scanner s = new Scanner(System.in);
			System.out.println("\n Enter number");		
			int num = s.nextInt();
			if(num==0)
				break;

			Runnable r = ()-> {	
				try {
					semaphore.acquire();
					int output = primeNumber(num);
					System.out.println("The "+num+"th prime number is:"+output);
				} 
				catch (InterruptedException e)
				{
					System.out.println("Interrupted while acquiring");
				}
				finally 
				{
					semaphore.release();
				}

			};	

			Thread t = new Thread(r);
			t.start();		
		}
	}

	static int primeNumber(int n)
	{
		int num, count, i;
		num=1;
		count=0;
		while (count < n){
			num=num+1;
			for (i = 2; i <= num; i++)
			{ 
				if (num % i == 0) {
					break;
				}
			}
			if ( i == num)
			{
				count = count+1;
			}
		}
		return num;
	}

}
