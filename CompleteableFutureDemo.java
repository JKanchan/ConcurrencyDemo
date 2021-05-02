package multithreading;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CompleteableFutureDemo {


	public static void main(String[] args)  
	{		
		ExecutorService exe = Executors.newFixedThreadPool(3);
		while(true) 
		{
			Scanner s = new Scanner(System.in);
			System.out.println("\n Enter number");		
			int num = s.nextInt();
			if(num==0)
				break;

			CompletableFuture.supplyAsync(()-> primeNumber(num),exe)
			.thenAccept(output ->System.out.println("The Prime number is"+output));	
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
