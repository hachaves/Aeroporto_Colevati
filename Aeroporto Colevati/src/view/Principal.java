package view;

/*
Nome: Henrique
Data: 21/05/2020 
Objetivo:
	Um aeroporto tem 2 pistas (norte e sul) e, em cada pista, apenas um avião pode fazer a decolagem.

	O procedimento de decolagem tem 4 fases (manobra, taxiar,
	decolagem e afastamento da área).

	A fase de manobra pode durar de 3 a 7 segundos.
	A fase de taxiar, de 5a 10 segundo.
	A fase de decolagem, de 1 a 4 segundos. 
	A fase de afastamento, de 3 a 8 segundos.

	O aeroporto reúne, por ciclo, 12 aeronaves que podem decolar pela pista norte ou pela pista sul (decisão aleatória). 
	Mas, apenas 2 aviões podem circular pela área de decolagem ao mesmo tempo.
	Fazer uma aplicação em Java que resolva o problema.
*/

import java.util.concurrent.Semaphore;
import controller.ThreadDecolagem;

public class Principal {
	public static void main(String[] args) {
		int limitacao = 2;
		Semaphore semaforo = new Semaphore(limitacao);
		for (int i = 0; i < 12; i++) {
			Thread td = new ThreadDecolagem(semaforo, i);
			td.start();
		}
	}
}