import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BestRelayTeam {

    static class Runner {
        private String name;
        private double first_leg;
        private double rest_leg;

        public Runner(String name, double first_leg, double rest_leg) {
            this.name = name;
            this.first_leg = first_leg;
            this.rest_leg = rest_leg;
        }

        public String getName() {
            return this.name;
        }

        public double getFirstLeg() {
            return this.first_leg;
        }

        public double getRestLeg() {
            return this.rest_leg;
        }
    }

    static class SortFirstLeg implements Comparator<Runner> {
        @Override
        public int compare(Runner runner1, Runner runner2) {
            if (runner1.first_leg > runner2.first_leg) {
                return 1;
            }
            return -1;
        }
	}

    static class SortRestLeg implements Comparator<Runner> {
        @Override
        public int compare(Runner runner1, Runner runner2) {
            if (runner1.rest_leg > runner2.rest_leg) {
                return 1;
            }
            return -1;
        }
	}

    public static void main(String args[]) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Used StringTokenizer to break string into tokens 
        StringTokenizer st = new StringTokenizer(br.readLine()); 

        // Get pool count
        int n = Integer.parseInt(st.nextToken());

        // Set up pool of runners (in terms of first leg and rest leg)
        Runner[] first_leg_pool = new Runner[n];
        Runner[] rest_leg_pool = new Runner[n];

        // Populate pool
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            double first_leg = Double.parseDouble(st.nextToken());
            double rest_leg = Double.parseDouble(st.nextToken());
            first_leg_pool[i] = new Runner(name, first_leg, rest_leg);
            rest_leg_pool[i] = new Runner(name, first_leg, rest_leg);
        }

        // Sort both pools to respective leg timings
        Arrays.sort(first_leg_pool, new SortFirstLeg());
        Arrays.sort(rest_leg_pool, new SortRestLeg());

        // Logic to get best team
        Runner[] best_team = null;
        double best_time = 99; // Since time a*1 + time b*3 always < 99 
        // Loop through top 4 first leg runners
        for (int i = 0; i < 4; i++) {
            Runner[] runners = new Runner[4];
            runners[0] = first_leg_pool[i];
            int members_count = 0;
            double time = runners[0].getFirstLeg();
            // Match first leg runner with his top 3 rest leg runners
            for (int j = 0; j < 4; j++) {
                Runner temp_runner = rest_leg_pool[j];
                
                // If first leg runner exists in top 4 rest leg runners list, skip him
                if (runners[0].getName().equals(temp_runner.getName()))
                    continue;
                
                // If there is vacancy for rest leg runners, continue adding
                if (members_count < 3) {
                    time += temp_runner.getRestLeg();
                    members_count++;
                    runners[members_count] = temp_runner;
                }
                
                // If all 3 rest leg members were found, end loop
                if (members_count == 3)
                    break;
            }
            // Replace best time with current team's time, if necessary
            if (time < best_time) {
                best_time = time;
                best_team = runners;
            }
        }

        // Build and display output
        StringBuilder output = new StringBuilder(Double.toString(best_time));
        output.append('\n');
        for (int i = 0; i < best_team.length; i++) {
            output.append(best_team[i].getName());
            output.append('\n');
        }
        System.out.print(output);
    }
}