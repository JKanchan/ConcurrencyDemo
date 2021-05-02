package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*The traditional way to achieve thread synchronization in Java
 *  is by the use of synchronized keyword. While it provides a certain basic 
 *  synchronization, the synchronized keyword is quite rigid in its use.
 *  For example, a thread can take a lock only once. Synchronized blocks don’t
 *  offer any mechanism of a waiting queue and after the exit of one thread,
 *  any thread can take the lock. This could lead to starvation of resources
 *  for some other thread for a very long period of time. Reentrant Locks are
 *   provided in Java to provide synchronization with greater flexibility.  
 */
class Counter implements Runnable{

	private int count=0;
	private Lock l = new ReentrantLock();

	public void increment() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		count++;
	}
	public void decrement() {
		count--;
	}
	public int getCount() {
		return count;
	}
	@Override
	public void run() {
		l.lock();
		try {
			this.increment();
			System.out.println(Thread.currentThread().getName()+" returns value of Count as "+ count + " after increment");
			this.decrement();
			System.out.println(Thread.currentThread().getName()+" returns value of Count as "+ count + " after decrement");	
		}
		finally	{
			l.unlock();					
		}

	}
}

public class ReEntrantLockDemo {

	public static void main(String[] args) {
		Counter c = new Counter();
		new Thread(c, "One").start();
		new Thread(c, "Two").start();
		new Thread(c, "Three").start();


	}

}
