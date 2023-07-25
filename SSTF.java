import java.util.*;

public class SSTF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int size = sc.nextInt();
        int[] requests = new int[size];

        System.out.println("Enter the request sequence:");
        for (int i = 0; i < size; i++) {
            requests[i] = sc.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = sc.nextInt();

        int totalSeekTime = 0;
        int currentHead = head;
        boolean[] visited = new boolean[size];

        System.out.println("\nSSTF Disk Scheduling Algorithm:");
        System.out.println("Head Movement\t\tSeek Time");

        for (int i = 0; i < size; i++) {
            int shortestSeekTime = Integer.MAX_VALUE;
            int shortestSeekIndex = -1;

            for (int j = 0; j < size; j++) {
                if (!visited[j]) {
                    int seekTime = Math.abs(requests[j] - currentHead);
                    if (seekTime < shortestSeekTime) {
                        shortestSeekTime = seekTime;
                        shortestSeekIndex = j;
                    }
                }
            }

            visited[shortestSeekIndex] = true;
            int seekDistance = Math.abs(requests[shortestSeekIndex] - currentHead);
            totalSeekTime += seekDistance;
            currentHead = requests[shortestSeekIndex];

            System.out.println(currentHead + "\t\t\t\t" + seekDistance);
        }

        System.out.println("\nTotal Seek Time: " + totalSeekTime);
        sc.close();
    }
}
