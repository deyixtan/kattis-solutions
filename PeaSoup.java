import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PeaSoup {

    public static void main(String[] args) throws IOException {
        // Used BufferReader instead of Scanner (uses regex, slows down performance)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean found = false;

        // Get number of restaurants
        int numOfRestaurants = Integer.parseInt(br.readLine());
        // Go through each restaurant
        for (int i = 0; i < numOfRestaurants; i++) {
            // Get number of menu items per restaurant
            int numOfMenuItems = Integer.parseInt(br.readLine());

            // Get restaurant name
            String restaurantName = br.readLine();
            // Set up flags to check if restaurant have both the food we wanted
            boolean peasoupExist = false;
            boolean pancakesExist = false;
            // Go through all the menu items
            for (int j = 0; j < numOfMenuItems; j++) {
                String menuItem = br.readLine();
                if (menuItem.equals("pea soup"))
                    peasoupExist = true;
                else if (menuItem.equals("pancakes"))
                    pancakesExist = true;

                if (peasoupExist && pancakesExist) {
                    System.out.println(restaurantName);
                    found = true;
                    break;
                }
            }

            // Additional check to force exit the program
            // Since at least 1 restaurant has been found
            if (found) {
                break;
            }
        }
        
        // display alternative message, if no interested restaurant found
        if (!found)
            System.out.println("Anywhere is fine I guess");
    }
}