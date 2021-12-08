package ihm;

import java.util.Scanner;

/**
 * Your class in package ihm to read from the keyboard and write on the screen
 * @author Allain
 *
 */
public class IOKeyBoardConsole {

	// The Scanner is in another class according to MVC Design Pattern
	private static final Scanner sc = new Scanner(System.in); 

	/**
	 * @param text
	 * @return
	 */
	public static int readANumber(String text) {
		System.out.println(text);
		int number = sc.nextInt();		
		//System.out.println("number given = "+number);
		return number;
	}

	/**
	 * @param text
	 * @return
	 */
	public static String readAString(String text) {
		System.out.println(text);
		String word = sc.next();		
		//System.out.println("number given = "+word);
		return word;
	}

	public static void close() {
		sc.close();
	}

	public static void print(Object object) {
		System.out.println(object);
	}
	
	public static void pressEnter() {
		System.out.println("press Enter to continue...");
		sc.nextLine();	
	}
	
	public static boolean readABoolean(String string) {
		System.out.println(string+" y/Y n/N");
		String word = sc.next();		
		return word.equals("y")||word.equals("Y");
	}
	
}