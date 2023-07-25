import java.util.Scanner;

class FCFSAlgorithm {
    static void FCFS(int arr[], int head) {
        int seekCount = 0;
        int distance, curTrack;

        for (int i = 0; i < arr.length; i++) {
            curTrack = arr[i];

            // calculate absolute distance
            distance = Math.abs(curTrack - head);

            // increase the total count
            seekCount += distance;

            // accessed track is now new head
            head = curTrack;
        }

        System.out.println("Total number of seek operations = " + seekCount);

        // Seek sequence would be the same as request array sequence
        System.out.println("Seek Sequence is");

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        System.out.print("Enter the request sequence: ");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();

        FCFS(arr, head);

        scanner.close();
    }
}
