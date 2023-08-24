import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        int currentPosition = 50;
        int[] requests = {176, 79, 34, 60, 92, 11, 41, 114};

        int totalSeekOperations = 0;

        System.out.println("Seek Sequence is");

        // Process requests in the order they arrive
        for (int i = 0; i < requests.length; i++) {
            System.out.println(requests[i]);
            totalSeekOperations += Math.abs(requests[i] - currentPosition);
            currentPosition = requests[i];
        }

        System.out.println("Total number of seek operations = " + totalSeekOperations);
    }
}
