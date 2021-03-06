package it.unitn.disi.wp.servizioSanitario.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Static class used to compute the Sha256 hash for a string.
 *
 * @author Mike_TdT
 */
public class Sha256 {
	
	/**
	 * Compute the hash value of a string.
	 * @param password String to be hashed
	 * @return Sha256 string for the input parameter
	 */
	public static String doHash(String password)/*fa l'hash di una String in sha256 e la converte in base64*/{
		byte[] encodedhash = null;
		try{
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		}
		catch(NoSuchAlgorithmException e){ System.err.println("Error during Hashing...\n"+e.toString()); }
		return Base64.getEncoder().encodeToString(encodedhash);
	}

	/**
	 * Checks if a string is the original value of the Sha256 hash.
	 * @param password	Clear text to be checked against hash
	 * @param hash Hashed string
	 * @return true if the hash of the password matches the hash string passed as a parameter
	 */
	public static boolean checkPassword(String password,String hash)/*controlla una password con un hash*/{
		return doHash(password).equals(hash);
	}
}
