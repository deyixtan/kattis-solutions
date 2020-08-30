import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Apaxiaaans {

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Used StringBuffer to handle immutable string
        StringBuffer output = new StringBuffer("");

        int curr = -1;
        int prev = -1;
        int read_counter = 0;
        // looping through each character, comparing with prev char
        // if not the same, then add to output
        while ((curr = br.read()) != -1) {
            read_counter++;
            
            if (curr != prev) {
                output.append((char)curr);
            }
            prev = curr;

            if (read_counter == 250)
                break;
        }

        System.out.println(output);
    }
}
