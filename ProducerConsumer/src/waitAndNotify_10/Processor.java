package waitAndNotify_10;

import java.util.Scanner;

public class Processor 
{
	private Object lock = new Object();//Object level lock
	//int lock = 0; Doesn't work due to being primitive data type
	//Integer lock = new Integer(10) Works
	public void produce() throws InterruptedException
	{
		synchronized(lock)//Synchronized code block, producer thread gets lock first
		{
			System.out.println("Producer thread running...");
			lock.wait();//Gives up lock to consume
			System.out.println("Resumed");
		}
	}
	public void consume() throws InterruptedException
	{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(lock)//Get lock from produce : Processor.class would be a class level lock : 'this' is an object level lock
		{
			System.out.println("Waiting for enter...");
			scanner.nextLine();
			System.out.println("Enter key pressed");
			notify();//Alerts that the lock will be given back
			Thread.sleep(5000);
		}
	}
}
