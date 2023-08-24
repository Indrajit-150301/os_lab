import java.util.Arrays;

public class SSTF {
    public static void main(String[] args) {
        int currentPosition = 50;
        int[] requests = {176, 79, 34, 60, 92, 11, 43, 114};
        int direction = -1;  // -1 indicates moving left

        Arrays.sort(requests);

        int totalSeekOperations = 0;
        int index = Arrays.binarySearch(requests, currentPosition);

        if (index < 0) {
            index = -index - 1;
        }
        
        System.out.println("Seek Sequence is");
        System.out.println(currentPosition);
        // Handle requests to the left of the initial position
        for (int i = index - 1; i >= 0; i--) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }

        // Handle requests to the right of the initial position
        for (int i = index; i < requests.length; i++) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }

        System.out.println("Total number of seek operations = " + totalSeekOperations);
    }
}