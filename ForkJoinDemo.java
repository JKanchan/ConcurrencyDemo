package multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*Fork Join is designed for work that can be broken 
 * into smaller pieces recursively.
 */
public class ForkJoinDemo {
	 public void runForkJoinExample()
	 {
		int[] array = {10,22,31,46,57,63,72,88,96,110};
		ForkJoinPool forkJoinPool =ForkJoinPool.commonPool();
		CalculatePrimeTask calculatePrimeTask=new CalculatePrimeTask(array, 0, array.length-1);
		Integer result = forkJoinPool.invoke(calculatePrimeTask);
		System.out.println("The sum of primes is:"+ result);
	}

	public static void main(String[] args) {
		ForkJoinDemo forkJoinDemo = new ForkJoinDemo();
		forkJoinDemo.runForkJoinExample();
		
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

class CalculatePrimeTask extends RecursiveTask<Integer>{
	
	private static final long serialVersionUID = 1L;
	int start;
	int end;
	int[] array;
	
	public CalculatePrimeTask(int[] array,int start,int end) {
		this.start = start;
		this.end = end;
		this.array = array;
	}

	@Override
	protected Integer compute() {
		if(start==end) {
			System.out.println("start:"+array[start]+" prime is "+ForkJoinDemo.primeNumber(array[start]));
			return ForkJoinDemo.primeNumber(array[start]);
		}
		if(end -start ==1) {
			System.out.println("Start:"+array[start]+" prime is "+ForkJoinDemo.primeNumber(array[start]));
			System.out.println("End:"+array[end]+" prime is "+ForkJoinDemo.primeNumber(array[end]));

			return ForkJoinDemo.primeNumber(array[start])
					+ForkJoinDemo.primeNumber(array[end]);
		}
		int mid = (start+end)/2;
		CalculatePrimeTask subtask1 = new CalculatePrimeTask(array,start,mid);
		CalculatePrimeTask subtask2 = new CalculatePrimeTask(array,mid+1,end);
		invokeAll(subtask1,subtask2);
		return subtask1.join()+subtask2.join();
	}
	
}
