/*============================================================================
|  Encrypting a plaintext file using the Hill cipher
|
| Author: Charles Matthews
| Language: C
| To Compile: gcc -o pa01 pa01.c
| To Execute: ./pa01 kX.txt pX.txt
| where kX.txt is the keytext file and pX.txt is plaintext file
|
| Note:
| All input files are simple 8 bit ASCII input
| All execute commands above have been tested on a Linux server
+===========================================================================*/

import java.util.*;
import java.io.*;

public class pa01 {

	
	//Global variable matrixSize to retain size of matrix through entire process
	static int matrixSize;
	
	public static void main(String[] args) {
		
		int[][] keyMatrix = null;
		String plaintext = null;
		
		//Obtain key, plaintext from command line input
		try {
			 keyMatrix = readKeyFile(args[0]);
			 plaintext = readPlaintextFile(args[1]);
		}
		// If files fail to open, end program
		catch (Exception e) {
			System.out.println("Error processing files...");
			e.printStackTrace();
			System.exit(1);
		}
		
		//Pad the plaintext to fit hill matrix appropriately
		plaintext = padPlaintext(plaintext);
		
		//Print the initial info: key matrix, padded plaintext
		System.out.println("Key matrix:");
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++)
				System.out.printf("%4d", keyMatrix[i][j]);
			System.out.print("\n");
		}
		System.out.println("\nPlaintext:");
		printFormattedText(plaintext);
		
		//Call hillCipher to do matrix multiplication on the plaintext
		String ciphertext = hillCipher(keyMatrix, plaintext);
		System.out.println("\nCiphertext:");
		printFormattedText(ciphertext);
	}

	
	
	private static int[][] readKeyFile(String filename) throws IOException {
		
		//Declare a scanner, create matrix of appropriate size
		Scanner scan = new Scanner(new File(filename));
		matrixSize = scan.nextInt();
		int[][] keyMatrix = new int[matrixSize][matrixSize];
		
		//Fill matrix with read values
		for (int i = 0; i < matrixSize; i++)
			for (int j = 0; j < matrixSize; j++)
				keyMatrix[i][j] = scan.nextInt();
		
		scan.close();
		return keyMatrix;
	}
	
	
	
	private static String readPlaintextFile(String filename) throws IOException {
	    //Create scanner and StringBuilder to parse plaintext
	    Scanner scan = new Scanner(new File(filename));
	    StringBuilder sb = new StringBuilder();

	    //Use scanner to read all text that is not whitespace
	    while (scan.hasNext()) {
	        String word = scan.next().toLowerCase();

	        //Only append letters a-z to string builder, ignore non-Roman symbols
	        for (char c : word.toCharArray()) {
	            if (c >= 'a' && c <= 'z') {  //Only allow English lowercase letters
	                sb.append(c);
	            }
	        }
	    }

	    scan.close();
	    return sb.toString();
	}

	
	
	
	private static String padPlaintext(String plaintext) {
		
		//Determine number of characters over size
		int extraChars = plaintext.length() % matrixSize;
		
		//Append x to end to make the result even within hill matrix
		if (extraChars != 0) {
			StringBuilder sb = new StringBuilder(plaintext);
			for (int i = 0; i < matrixSize - extraChars; i++)
				sb.append('x');
			
			//Return the padded plaintext
			return sb.toString();
		}
		
		//If no padding is needed, return the plaintext
		return plaintext;
	}
	
	
	
	private static void printFormattedText(String text) {
	    for (int i = 0; i < text.length(); i += 80) {
	        //Get a substring of max 80 characters (or less if its the last row)
	        System.out.println(text.substring(i, Math.min(i + 80, text.length())));
	    }
	}

	
	
	private static String hillCipher(int[][] key, String plaintext) {
	    
		//Split strings into appropriate sizes for the matrix
	    StringBuilder ciphertext = new StringBuilder();
	    int charIndex = 0;
	    int iterations = plaintext.length() / matrixSize;

	    for (int i = 0; i < iterations; i++) {
	        
	        //Create a plaintext vector
	        int[] plain = new int[matrixSize];
	        for (int j = 0; j < matrixSize; j++) {
	            plain[j] = plaintext.charAt(charIndex) - 'a'; //Convert letter to number (0-25)
	            charIndex++;
	        }
	        
	        // Encrypt using matrix multiplication
	        int[] cipher = new int[matrixSize];
	        for (int row = 0; row < matrixSize; row++) {
	            int sum = 0;
	            for (int col = 0; col < matrixSize; col++) {
	                sum += key[row][col] * plain[col];
	            }
	            cipher[row] = sum % 26; //Apply mod 26
	        }
	        
	        //Convert numbers back to letters and append to ciphertext
	        for (int j = 0; j < matrixSize; j++) {
	            ciphertext.append((char) (cipher[j] + 'a')); //Convert back to letter
	        }
	    }

	    return ciphertext.toString();
	}
}