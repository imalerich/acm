import java.util.HashMap;
import java.util.Scanner;

public class MirrorMirror {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String out = proc(s.nextLine());
			if (out.length() == 0) {
				break;
			}
			
			System.out.println(out);
		}
	}

	public static String proc(String line) {
		if (shouldTerminate(line)) {
			return "";
		}
		
		HashMap<Character, Character> mirror = new HashMap<Character, Character>();
		mirror.put('b', 'd');
		mirror.put('d', 'b');
		mirror.put('p', 'q');
		mirror.put('q', 'p');
		
		mirror.put('i', 'i');
		mirror.put('o', 'o');
		mirror.put('v', 'v');
		mirror.put('w', 'w');
		mirror.put('x', 'x');
		
		String out = "";
		for (int i=0; i<line.length(); i++) {
			Character c = new Character(line.charAt(i));
			if (!mirror.containsKey(c)) {
				return "INVALID";
			} else {
				out = mirror.get(c) + out;
			}
		}
		
		return out;
	}
	
	public static boolean shouldTerminate(String line) {
		return line.equals("#");
	}
}
