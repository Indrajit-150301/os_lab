import java.util.Scanner;

class Process {
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time

    public Process(int pid, int bt, int art) {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}

public class SRTF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        Process[] proc = new Process[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for process " + (i + 1) + ":");
            System.out.print("Burst Time: ");
            int bt = scanner.nextInt();
            System.out.print("Arrival Time: ");
            int art = scanner.nextInt();

            proc[i] = new Process(i + 1, bt, art);
        }

        scanner.close();

        findAvgTime(proc, n);
    }

    // Method to find the waiting time for all processes
    static void findWaitingTime(Process proc[], int n, int wt[]) {
        int[] rt = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;

        int complete = 0;
        int t = 0;
        int minm = Integer.MAX_VALUE;
        int shortest = -1;
        boolean check = false;

        // Process until all processes get completed
        while (complete != n) {
            // Find process with the shortest remaining time among the processes that have arrived till the current time
            for (int j = 0; j < n; j++) {
                if (proc[j].art <= t && rt[j] < minm && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (!check) {
                t++;
                continue;
            }

            // Reduce the remaining time of the selected process by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If the process is completed
            if (rt[shortest] == 0) {
                complete++;
                check = false;

                // Calculate waiting time
                wt[shortest] = t + 1 - proc[shortest].bt - proc[shortest].art;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }

            // Increment time
            t++;
        }
    }

    // Method to calculate the turnaround time
    static void findTurnaroundTime(Process proc[], int n, int wt[], int tat[], int ct[]) {
        // Calculate the turnaround time by adding the bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];

        // Calculate the completion time
        for (int i = 0; i < n; i++)
            ct[i] = tat[i] + proc[i].art;
    }

    // Method to calculate the average time
    static void findAvgTime(Process proc[], int n) {
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] ct = new int[n];
        int total_wt = 0;
        int total_tat = 0;

        // Find waiting time of all processes
        findWaitingTime(proc, n, wt);

        // Find turnaround time and completion time for all processes
        findTurnaroundTime(proc, n, wt, tat, ct);

        // Display processes along with all details
        System.out.println("Process\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time\tCompletion Time");

        for (int i = 0; i < n; i++) {
            total_wt += wt[i];
            total_tat += tat[i];
            System.out.println(proc[i].pid + "\t\t" + proc[i].bt + "\t\t" + proc[i].art + "\t\t\t" + wt[i] + "\t\t\t" + tat[i] + "\t\t\t" + ct[i]);
        }

        float averageWaitingTime = (float) total_wt / n;
        float averageTurnaroundTime = (float) total_tat / n;

        System.out.println("\nAverage waiting time = " + averageWaitingTime);
        System.out.println("Average turnaround time = " + averageTurnaroundTime);

        float totalBurstTime = 0;
        for (int i = 0; i < n; i++) {
            totalBurstTime += proc[i].bt;
        }

        float throughput = totalBurstTime / n;
        System.out.println("Throughput = " + throughput);
    }
}
