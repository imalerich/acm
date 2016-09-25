import java.util.ArrayList;
import java.util.Scanner;

public class Backgammon {
	
	public static final int N = 15;
	public static final int C = 6;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		for (int i=0; proc(s.nextLine(), ++i);) { }
		s.close();
	}
	
	public static boolean proc(String line, int idx) {
		Scanner s = new Scanner(line);
		String cmd = s.next();
		if (cmd.equals("e")) { 
			s.close();
			return false; 
		}
		
		System.out.print("Case: " + idx + ": ");
		if (cmd.equals("m")) {
			toIndex(s);
		} else if (cmd.equals("u")) {
			toConfig(s);
		}
		
		return true;
	}
	
	public static void toIndex(Scanner s) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while (s.hasNextInt()) { arr.add(s.nextInt()); }
		
		int count = 15503;
		int idx = 15 - arr.get(0);
		arr.remove(0);
		count -= idx;
	}
	
	public static void toConfig(Scanner s) {
		//
	}
}