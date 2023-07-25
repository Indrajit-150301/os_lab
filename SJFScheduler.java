import java.util.*;

public class SJFScheduler {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // get number of processes
        System.out.print("Enter number of processes: ");
        int n = input.nextInt();

        // initialize arrays to store arrival time and burst time of each process
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];

        // get arrival time and burst time of each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process " + (i+1) + ": ");
            arrivalTime[i] = input.nextInt();
            System.out.print("Enter burst time of process " + (i+1) + ": ");
            burstTime[i] = input.nextInt();
        }

        // initialize arrays to store completion time, wait time, and turnaround time of each process
        int[] completionTime = new int[n];
        int[] waitTime = new int[n];
        int[] turnaroundTime = new int[n];
        boolean[] isCompleted = new boolean[n];
        int currentTime = 0;
        int completed = 0;

        while (completed != n) {
            // find the process with the shortest burst time that has arrived
            int shortest = -1;
            int shortestBurst = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && !isCompleted[i] && burstTime[i] < shortestBurst) {
                    shortest = i;
                    shortestBurst = burstTime[i];
                }
            }

            if (shortest == -1) {
                currentTime++;
            } else {
                // execute the shortest process
                completionTime[shortest] = currentTime + burstTime[shortest];
                waitTime[shortest] = currentTime - arrivalTime[shortest];
                turnaroundTime[shortest] = waitTime[shortest] + burstTime[shortest];
                isCompleted[shortest] = true;
                completed++;
                currentTime = completionTime[shortest];
            }
        }

        // print the completion time, wait time, and turnaround time of each process
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWait Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + "\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i] + "\t\t" + waitTime[i] + "\t\t" + turnaroundTime[i]);
        }

        // calculate and print the average wait time and average turnaround time
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            totalWaitTime += waitTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }
        double avgWaitTime = (double) totalWaitTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;
        System.out.println("Average Wait Time: " + avgWaitTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
    }
