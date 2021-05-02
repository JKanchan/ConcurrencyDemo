package multithreading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static void main(String[] args)  
	{		
		List<Future<Integer>> futureList = new ArrayList<>();
		ExecutorService e = Executors.newCachedThreadPool();
		while(true) 
		{
			Scanner s = new Scanner(System.in);
			System.out.println("\n Enter number");		
			int num = s.nextInt();
			if(num==0)
				break;

			Callable<Integer> c = ()-> {	
				return primeNumber(num);
			};	
			
			Future<Integer> output = e.submit(c);
			futureList.add(output);
			
			Iterator<Future<Integer>> iter =futureList.iterator();
			while(iter.hasNext())
			{
				Future<Integer> f = iter.next();
				if(f.isDone()) {
					try {
						System.out.println("The prime number is"+ f.get());
						iter.remove();
					} catch (InterruptedException | ExecutionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
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







