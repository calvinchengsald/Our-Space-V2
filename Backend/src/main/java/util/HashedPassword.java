/**
 * 
 */
package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.hash.Hashing;

/**
 * @author Jerry Affricot
 *
 */
public class HashedPassword {
	
	/*
	 * get hash version of the password
	 */
	public static String getHash(String password) throws NoSuchAlgorithmException {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	}

}
