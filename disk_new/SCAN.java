import java.util.*;

public class SCAN {
    public static void main(String[] args) {
        int currentPosition = 50;
        int[] requests = {176, 79, 34, 60, 92, 11, 41, 114};
        int maxCylinder = 199;  // Maximum cylinder number

        int totalSeekOperations = 0;
        int direction = -1;  // -1 indicates moving towards lower cylinder numbers

        Arrays.sort(requests);

        System.out.println("Seek Sequence is");

        // Find the position of the initial request in the sorted array
        int index = Arrays.binarySearch(requests, currentPosition);
        if (index < 0) {
            index = -index - 1;
        }

        for (int i = index - 1; i >= 0; i--) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }
        
        totalSeekOperations += 2*(currentPosition);
        System.out.println("0");

        for (int i = index; i < requests.length; i++) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }

        System.out.println("Total number of seek operations = " + totalSeekOperations);
    }
}
