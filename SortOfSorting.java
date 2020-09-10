import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class SortOfSorting {
    
    static class SortName implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int r = s1.charAt(0) - s2.charAt(0);
            if (r == 0)
                return s1.charAt(1) - s2.charAt(1);
            return r;
        }
    }

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Output variable
        StringBuilder output = new StringBuilder();

        // Variables
        boolean first_case = true;
        int n = -1;
        while ((n = Integer.parseInt(br.readLine())) > 0) {
            // Print newline at the start of each test case
            // Except for the first
            if (first_case)
                first_case = false;
            else
                output.append('\n');

            // Array which will contain names of current test case
            String[] names = new String[n];

            // Populate array in current test case with names
            for (int i = 0; i < n; i++) {
                names[i] = br.readLine();
            }

            // Sort array based on comparator
            Arrays.sort(names, new SortName());

            // Append names to StringBuilder for output
            for (int i = 0; i < n; i++) {
                output.append(names[i]).append('\n');
            }
        }

        // Output
        System.out.println(output);
    }
    
}