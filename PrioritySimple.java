import java.util.*;

public class PrioritySimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];  // Arrival time
        int[] bt = new int[n];  // Burst time
        int[] pr = new int[n];  // Priority
        int[] ct = new int[n];  // Completion time
        int[] tat = new int[n]; // Turnaround time
        int[] wt = new int[n];  // Waiting time

        // take input
        for (int i = 0; i < n; i++) {
            System.out.println("Process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            System.out.print("Priority: ");
            pr[i] = sc.nextInt();
        }

        // sort by arrival time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    int temp = at[i]; at[i] = at[j]; at[j] = temp;
                    temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                    temp = pr[i]; pr[i] = pr[j]; pr[j] = temp;
                }
            }
        }

        int time = 0, done = 0;
        boolean[] finished = new boolean[n];

        // main loop
        while (done < n) {
            int idx = -1, best = 9999;

            for (int i = 0; i < n; i++) {
                if (!finished[i] && at[i] <= time && pr[i] < best) {
                    best = pr[i];
                    idx = i;
                }
            }

            if (idx == -1) { time++; continue; }

            time += bt[idx];
            ct[idx] = time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            finished[idx] = true;
            done++;
        }

        System.out.println("\nP\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++)
            System.out.println((i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);

        sc.close();
    }
}
