import java.util.Scanner;

public class FCFSAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        int[] arrivalTime = new int[numProcesses];
        int[] burstTime = new int[numProcesses];
        int[] completionTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        int[] turnaroundTime = new int[numProcesses];

        // Input arrival time and burst time for each process
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for Process " + (i + 1) + ": ");
            arrivalTime[i] = scanner.nextInt();
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            burstTime[i] = scanner.nextInt();
        }

        scanner.close();

        // Calculate completion time, waiting time, and turnaround time
        completionTime[0] = burstTime[0];
        turnaroundTime[0] = completionTime[0] - arrivalTime[0];
        waitingTime[0] = turnaroundTime[0] - burstTime[0];

        for (int i = 1; i < numProcesses; i++) {
            completionTime[i] = completionTime[i - 1] + burstTime[i];
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
        }

        // Calculate average waiting time, average turnaround time, and throughput
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double throughput = (double) numProcesses / completionTime[numProcesses - 1];

        for (int i = 0; i < numProcesses; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        double averageWaitingTime = totalWaitingTime / numProcesses;
        double averageTurnaroundTime = totalTurnaroundTime / numProcesses;

        // Display the results
        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println((i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i] +
                    "\t\t\t" + waitingTime[i] + "\t\t\t" + turnaroundTime[i]);
        }
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
        System.out.println("Throughput: " + throughput);
    }
}
