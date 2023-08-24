import java.util.*;

public class CSCAN {
    public static void main(String[] args) {
        int currentPosition = 50;
        int[] requests = {176, 79, 34, 60, 92, 11, 41, 114};
        int max = 199;  // Maximum cylinder number

        int totalSeekOperations = 0;
        
        Arrays.sort(requests);

        System.out.println("Seek Sequence is");

        // Find the position of the initial request in the sorted array
        int index = Arrays.binarySearch(requests, currentPosition);
        if (index < 0) {
            index = -index - 1;
        }

        // Process requests in the forward direction (towards higher cylinder numbers)
        for (int i = index; i < requests.length; i++) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }

        // Add seek time to reach the maximum cylinder
        //totalSeekOperations += max;
        totalSeekOperations += 2*(max - currentPosition);
        // Reset initial position to the minimum cylinder
        
        currentPosition = 0;
        
        System.out.println(max);
        System.out.println(currentPosition);

        // Process requests from the minimum cylinder up to the starting position
        for (int i = 0; i < index; i++) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }
        System.out.println("Total number of seek operations = " + totalSeekOperations);
    }
}
