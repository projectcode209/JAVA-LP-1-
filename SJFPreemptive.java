import java.util.*;

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];  // Arrival Time
        int[] bt = new int[n];  // Burst Time
        int[] rt = new int[n];  // Remaining Time
        int[] ct = new int[n];  // Completion Time
        int[] tat = new int[n]; // Turnaround Time
        int[] wt = new int[n];  // Waiting Time

        // input for all processes
        for (int i = 0; i < n; i++) {
            System.out.print("AT for P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("BT for P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i]; // initially remaining = burst
        }

        int time = 0, done = 0; // total time and completed processes

        // run until all processes are done
        while (done < n) {
            int idx = -1, min = 9999;

            // find process with smallest remaining time that has arrived
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && rt[i] < min) {
                    min = rt[i];
                    idx = i;
                }
            }

            // if no process has arrived, increase time
            if (idx == -1) {
                time++;
                continue;
            }

            rt[idx]--; // run selected process for 1 unit
            time++; // increase time

            // if process is finished
            if (rt[idx] == 0) {
                ct[idx] = time; // completion time
                tat[idx] = ct[idx] - at[idx]; // turnaround time
                wt[idx] = tat[idx] - bt[idx]; // waiting time
                done++;
            }
        }

        // display result
        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++)
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);

        sc.close();
    }
}
