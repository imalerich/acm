import java.util.ArrayList;
import java.util.Scanner;

public class Bridges {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (proc(s)) { }
		s.close();
	}
	
	public static boolean proc(Scanner s) {
		Pair config = new Pair(s.nextLine());
		config.x = -config.x; // Invert the negative it gives us.
		if (shouldTerminate(config)) {
			return false;
		}
		
		// Load the configuration for our bridges.
		ArrayList<Pair> bridges = new ArrayList<Pair>();
		ArrayList<Pair> status = new ArrayList<Pair>();
		for (int i=0; i<config.x; i++) {
			bridges.add(new Pair(s.nextLine()));
			status.add(new Pair()); // Waiting on this bridge.
			status.add(new Pair()); // Currently on this bridge and time remaining.
		}
		
		// Add the destination zone, this will just be used for convenience.
		status.add(new Pair());
		
		// Initial status : everyone is waiting for bridge one.
		status.get(0).x = config.y;
		
		// While someone is still waiting to cross, or is on a bridge.
		int time=0;
		for (; !isSimComplete(status); time++) {
			// Update the status of each bridge.
			for (int i=0; i<bridges.size(); i++) {
				Pair bridge = bridges.get(i);
				Pair waiting = status.get(i*2);
				Pair onBridge = status.get(i*2 + 1);
				
				// If there are people on this bridge, move off of it.
				if (onBridge.y > 0) {
					// Decrement the time remaining on the bridge;
					onBridge.y--;

					if (onBridge.y == 0) {
						// Move off of the bridge.
						status.get(i*2 + 2).x += onBridge.x;
						onBridge.x = 0;
					}
				}
				
				
				// If there are no people on this bridge, and people waiting, move people onto the bridge.
				if (onBridge.x == 0 && waiting.x > 0) {
					int count = Math.min(waiting.x, bridge.x);
					onBridge.x = count; // Move people onto this bridge.
					waiting.x -= count;
					onBridge.y = bridge.y; // Set the time for crossing this bridge.
				}
			} // END for each bridge
		} // END simulation
		
		System.out.println(time-1); // Off by one.
		return true;
	}
	
	public static boolean isSimComplete(ArrayList<Pair> status) {
		// Ignore the last status, that is the destination zone.
		for (int i=0; i<status.size()-1; i++) {
			Pair p = status.get(i);
			if (p.x != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean shouldTerminate(Pair p) {
		return p.x == 0 && p.y == 0;
	}
}

class Pair {
	int x;
	int y;
	
	Pair() {
		x = 0;
		y = 0;
	}
	
	Pair(int X, int Y) {
		x = X;
		y = Y;
	}
	
	Pair(String line) {
		Scanner s = new Scanner(line);
		x = s.nextInt();
		y = s.nextInt();
		s.close();
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}