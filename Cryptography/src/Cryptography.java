import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Cryptography {

	public Cryptography() {
		Scanner s = new Scanner(System.in);
		
		String input = s.nextLine();
		
		int key = s.nextInt();
		
		//System.out.println("en: " + en_Scytale(input, key));
		//System.out.println("de: " + de_Scytale(input, key));
		
		
		System.out.println("en: " + en_Caesar(input, key));
		//System.out.println("de: " + de_Caesar(input, key));
		
		//System.out.println("en: " + en_Vigenere(input, key));
		//System.out.println("dn: " + de_Vigenere(input, key));
	}

	private String en_Caesar(String input, int key) {
		String output = "";
		
		while (key > 26) {
			key = key - 26;
		}
		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' && input.charAt(i) + key > 'Z') { //upper
				output += (char) ('A' - 1 + Math.abs('Z' - (input.charAt(i) + key)));
			} else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && input.charAt(i) + key > 'z') { //lower
				output += (char) ('a' - 1 + Math.abs('z' - (input.charAt(i) + key)));
			} else {
				output += (char) (input.charAt(i) + key);
			}
		}
		
		System.out.println("de: " + de_Caesar(output, key));
		
		return output;
	}

	private String de_Caesar(String input, int key) {
		String output = "";
		
		while (key > 26) {
			key = key - 26;
		}
		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z' && input.charAt(i) - key < 'A') { //upper
				output += (char) (1 + 'Z' - ('A' - (input.charAt(i) - key)));
			} else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && input.charAt(i) - key < 'a') { //lower
				output += (char) (1 + 'z' - ('a' - (input.charAt(i) - key)));
			}/* else {
				output += (char) (input.charAt(i) - key);
			}*/
		}
		
		return output;
	}

	private String en_Scytale(String input, int row) {
		String output = "";
		
		int col = Math.round((input.length() / row) + 1);
		int diff = row - ((row * col) - input.length());
		int strI = 0;
		
		char[][] t = new char[row][col];
		
		System.out.println("info: " + row + "," + col + "," + input.length());
		
		for (int i = 0; i < t.length; i++) 
		{
			for (int j = 0; j < t[0].length; j++) 
			{
				if (j == col - 1 && diff <= 0 || strI >= input.length()) {
					t[i][j] = '@';
				} else {
					t[i][j] = input.charAt(strI);
					strI++;
				}
			}
			diff--;
		}
		
		for (int i = 0; i < t[0].length; i++) 
		{
			for (int j = 0; j < t.length; j++) 
			{
				if (t[j][i] != '@') {
					output += t[j][i];
				}
			}
		}
		
		return output;
	}
	
	private String de_Scytale(String input, int row) {
		String output = "";
		
		int col = Math.round((input.length() / row) + 1);
		int diff = row - ((row * col) - input.length());
		int strI = 0;
		
		String ps = "";
		
		for (int i = 0; i < row * col; i++) 
		{
			if (strI >= input.length()) {
				ps += '@';
			} else {
				ps += input.charAt(strI);
				strI++;
			}
		}
		
		strI = 0;
		
		char[][] t = new char[row][col];
		
		for (int i = 0; i < t[0].length; i++) 
		{
			for (int j = 0; j < t.length; j++) 
			{
				t[j][i] = ps.charAt(strI);
				strI++;
			}
		}
		
		for (int i = 0; i < t.length; i++) 
		{
			for (int j = 0; j < t[0].length; j++) 
			{
				if (t[i][j] != '@') {
					output += t[i][j];
				}
			}
		}
		
		return output;
	}

	public static void main(String[] args) {
		new Cryptography();
	}

}
