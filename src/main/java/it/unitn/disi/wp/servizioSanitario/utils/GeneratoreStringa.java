/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.utils;
 
import java.util.Random;
/**
 *
 * @author Mike_TdT
 */
public class GeneratoreStringa {

	// array delle lettere
	private String[] Caratteri = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
			"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "y",
			"j", "k", "x", "w", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "Y",
			"J", "K", "X", "W"};
 
	// array dei numeri
	private String[] Numeri = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
 
//	// array dei caratteri speciali
//	private String[] Speciali = { "!", "£", "$", "%", "&", "@", "*", ",", "_",
//			"-", "#", ";", "^", "\\", "/", ":", ".", "+", "§", "?", "ç" };
 
	// creo l'oggetto random
	private Random rand = new Random();
 
	public String GeneraCodice(int numeroCaratteriRandom) {
 
		// ottengo la lunghezza di ogni array
		int lunghezzaCaratteri = Caratteri.length;
		int lunghezzaNumeri = Numeri.length;
//		int lunghezzaSpeciali = Speciali.length;
 
		// istanzio la variabile che conterrà il prodotto finale
		String stringaRandom = "";
 
		while (stringaRandom.length() < numeroCaratteriRandom) {
 
			// ottengo un elemento casuale per ogni array
			int c = rand.nextInt(lunghezzaCaratteri);
			int n = rand.nextInt(lunghezzaNumeri);
//			int s = rand.nextInt(lunghezzaSpeciali);
 
			// aggiungo una lettera casuale
			stringaRandom += Caratteri[c];
			// aggiungo un numero random
			stringaRandom += Numeri[n];
			// se l'opzione conSpeciali è true aggiungo un carattere speciale
//			if (conSpeciali) {
//				stringaRandom += Speciali[s];
//			}
		}
 
		// se la stringa generata dovesse superare il numero di caratteri
		// richiesto, la taglio.
		if (stringaRandom.length() > numeroCaratteriRandom) {
			stringaRandom = stringaRandom.substring(0, numeroCaratteriRandom);
		}
 
//		// se abbiamo deciso di mettere il timestamp
//		if (conTimestamp) {
//			// recupero il timestamp
//			long timestamp = System.currentTimeMillis();
//			stringaRandom += separatore + timestamp;
//		}
 
		// restituisco la stringa generata
		return stringaRandom;
	}   
}
