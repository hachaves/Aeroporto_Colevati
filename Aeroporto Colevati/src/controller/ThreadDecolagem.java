package controller;

import java.util.concurrent.Semaphore;

public class ThreadDecolagem extends Thread {
	
	private Semaphore semaforo;//Limitação de 2
	private int idAviao;
	private int pista;//Pista que ira decolar
	private static String[] nPistas = { "NORTE", "SUL" };//Direção das pistas
	private static boolean[] permPistas = { true, true };//Permissão de decolagem das pistas

	public ThreadDecolagem(Semaphore semaforo,int idAviao) {
		this.semaforo = semaforo;
		this.idAviao = idAviao;
	}//------------------------------------------ Construtor

	private void decPista() {//Decide que pista ele ira decolar
		this.pista = (int) (Math.random() * 2) + 0;
		System.out.println("O avião #" + idAviao + " está aguardando para entrar na pista " + nPistas[pista] + ".");
	}

	private void verfPista() {//Verifica se a pista se encontra livre
		while (!permPistas[pista]) {
			try {//Se não estiver livre, faz a Thread dormir
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		permPistas[pista]= false;//Se estiver livre, bloqueia a pista novamente e permite a decolagem
	}

	private void decolagem() {//Fase de decolagem
			System.out.println(" + O avião #" + idAviao + " entrou na pista " + nPistas[pista] + ".");

			int tempo = (int) (Math.random() * 5) + 3;
			System.out.println("PISTA " + nPistas[pista] + " - O avião #" + idAviao + " está manobrando.");
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			tempo = (int) (Math.random() * 6) + 5;
			System.out.println("PISTA " + nPistas[pista] + " - O avião #" + idAviao + " está taxiando pela pista.");
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			tempo = (int) (Math.random() * 4) + 1;
			System.out.println("PISTA " + nPistas[pista] + " - O avião #" + idAviao + " está decolando.");
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			tempo = (int) (Math.random() * 6) + 3;
			System.out.println(" - PISTA " + nPistas[pista] + " - O avião #" + idAviao + " está se afastando da pista.");
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			permPistas[pista]= true;//Libera a permissão da pista
	}

	@Override//---------------------------------------------------------------------------------------------------------------
	public void run() {
		decPista();//Decide que pista ele ira decolar
		verfPista();//Verifica se a pista se encontra livre
		try {
			semaforo.acquire();
			decolagem();//Fase de decolagem
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
}