package controller;
import java.util.concurrent.Semaphore;

public class ShowController extends Thread {
	private int ThreadId;
	private int IngressoId;
	private Semaphore semaforo;
	int vend = 0;
	int tempo;
	private static int colocacao;
	private static int ingressos = 100;

public ShowController(int threadId, Semaphore semaforo, int ingressototal) {
	this.ThreadId = threadId;
	this.semaforo = semaforo;
}
public void run() {
	login();
	int ingresso = ingresso();
	procdecompra(ingresso);
	confdacompra(ingresso);
	compraparando();
}

private int ingresso() {
	int ingresso = (int) ((Math.random()* 4)+ 1);
	return ingresso;
}
private void login() {
	System.out.println("Thread#" +ThreadId+ " efetuando login");
	int tempo = (int) ((Math.random()* 1950)+ 51);
	
	if (tempo > 1000) {
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread#"+ThreadId+" Timeout");
		return;
	}
	else{
		
		try {
		semaforo.acquire();
		Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		System.out.println("Login efetuado com sucesso");
	}
	}
}

private void procdecompra(int ingresso) {	
	System.out.println("Thread#"+ ThreadId +" esta comprando "+ingresso+" ingressos");
	int tempo = (int) ((Math.random()* 2001)+ 1000);
	if (tempo > 2500) {
		try {
		semaforo.acquire();
		Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		System.out.println("Thread# "+ThreadId +" Timeout.");
		}
	}else {
		try {
		semaforo.acquire();
		Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		semaforo.release();
		System.out.println("Thread#"+ThreadId+" realizou o pedido de " +ingresso+ " ingressos");
	}
	}
}
private void compraparando() {
	Thread.yield();
}
private void confdacompra(int ingresso) {
	int ingressototal = 100;
	try {
		semaforo.acquire();
		vend =+ ingresso; 
		ingressos = ingressos - vend;
		
		System.out.println("Thread#"+ThreadId+" Está sendo concluida a compra!!");
		
		while (ingressototal < ingressos) {
			
			System.out.println("Thread#"+ThreadId+" Não está disponível");
			break;
		}
		
		if(ingressos <= 0) {
			System.out.println("Thread#"+ThreadId+" Sem vagas diponiveis");
			
		}else {
			System.out.println("Thread#"+ThreadId+" Pedido concluido com sucesso");
		}
	} catch (InterruptedException e) {
		e.printStackTrace();
	}finally {
		if (ingressos > 0) {
		System.out.println("Número de ingressos restantes:" + ingressos);
		}
		semaforo.release();
	}
}
}

		

		
	





