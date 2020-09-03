import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class T9Spelling {

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // First row input
        int n = Integer.parseInt(br.readLine());

        // Create mapping of each character with its sequence of numbers
        int[] map = new int[123];
        map['a'] = 2;
        map['b'] = 22;
        map['c'] = 222;
        map['d'] = 3;
        map['e'] = 33;
        map['f'] = 333;
        map['g'] = 4;
        map['h'] = 44;
        map['i'] = 444;
        map['j'] = 5;
        map['k'] = 55;
        map['l'] = 555;
        map['m'] = 6;
        map['n'] = 66;
        map['o'] = 666;
        map['p'] = 7;
        map['q'] = 77;
        map['r'] = 777;
        map['s'] = 7777;
        map['t'] = 8;
        map['u'] = 88;
        map['v'] = 888;
        map['w'] = 9;
        map['x'] = 99;
        map['y'] = 999;
        map['z'] = 9999;
        map[' '] = 0;

        // Looping through each text
        StringBuilder output = new StringBuilder();
        int category = -1;
        for (int i = 0; i < n; i++) {
            output.append("Case #");
            output.append(i + 1);
            output.append(": ");
            
            // Looping through each character
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                // Fetch character from the line
                char character = line.charAt(j);
                // Check which number the character belongs to
                int temp_category = map[character] % 10;
                // Check if the very same button was already pressed
                // Print space to simulate pausing for a moment
                if (temp_category == category)
                    output.append(' ');
                // Set button pressed to category and update output
                category = temp_category;
                output.append(map[character]);
            }
            output.append('\n');
        }

        // Output conversion
        System.out.print(output);
    }
}
