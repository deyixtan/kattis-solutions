import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Coconut {

    static class Hand {
        private int player_id;
        // state 1=folded, state 2=fist*2, state 3=palm down, state 4=behind back
        private int state;

        public Hand(int id) {
            this(id, 1);
        }

        public Hand(int id, int state) {
            this.player_id = id;
            this.state = state;
        }

        public int getPlayerId() {
            return this.player_id;
        }

        public int getState() {
            return this.state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
    
    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read inputs
        String[] inputs = br.readLine().split(" ");
        int s = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);

        // Add players and assign default state
        ArrayList<Hand> hands = new ArrayList<Hand>();
        for (int i = 0; i < n; i++) {
            hands.add(new Hand(i));
        }

        // Main logic
        int current = 0; // first player starts
        while (hands.size() > 1) {
            // Get index of player, last touched
            current = current + s - 1;
            current %= hands.size(); // wrap index within hands size()
            Hand current_hand = hands.get(current);
            switch (current_hand.getState()) {
                case 1:
                    // Convert folded hands -> fist
                    current_hand.setState(2);
                    // Create another fist
                    Hand second_hand = new Hand(current_hand.getPlayerId(), 2);
                    hands.add(current + 1, second_hand);
                    break;
                case 2:
                    // Convert fist to palm down
                    current_hand.setState(3);
                    current = current + 1;
                    current %= hands.size();
                    break;
                case 3:
                    // Hand behind the back (removed from game)
                    hands.remove(current);
                    break;
            }
        }

        // Output
        int standing_player_id = hands.get(0).getPlayerId() + 1;
        System.out.println(standing_player_id);
    }
    
}
