package view;
import java.util.concurrent.Semaphore;

import controller.ShowController;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for (int ThreadId = 1; ThreadId <= 300; ThreadId++) {
			Thread thread = new ShowController(ThreadId, semaforo, ThreadId);
			thread.start();	
		}	
}
}
