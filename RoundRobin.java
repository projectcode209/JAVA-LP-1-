import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // for user input

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt(); // number of processes

        int[] at = new int[n]; // Arrival Time
        int[] bt = new int[n]; // Burst Time
        int[] rt = new int[n]; // Remaining Time
        int[] ct = new int[n]; // Completion Time
        int[] tat = new int[n]; // Turnaround Time
        int[] wt = new int[n]; // Waiting Time

        // take input
        for (int i = 0; i < n; i++) {
            System.out.println("Process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i]; // initially remaining = burst
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt(); // time quantum value

        int time = 0; // keeps track of CPU time
        int done = 0; // number of completed processes
        Queue<Integer> q = new LinkedList<>(); // queue for ready processes
        boolean[] inQueue = new boolean[n]; // to check if process is in queue

        // start from first process that has arrived
        while (done < n) {
            // add newly arrived processes to queue
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && !inQueue[i] && rt[i] > 0) {
                    q.add(i);
                    inQueue[i] = true;
                }
            }

            if (q.isEmpty()) { // if no process is ready
                time++;
                continue;
            }

            int i = q.poll(); // pick first process from queue
            int exec = Math.min(tq, rt[i]); // execute for tq or remaining time
            rt[i] -= exec; // reduce remaining time
            time += exec; // move current time

            // add newly arrived processes during this time
            for (int j = 0; j < n; j++) {
                if (at[j] <= time && !inQueue[j] && rt[j] > 0) {
                    q.add(j);
                    inQueue[j] = true;
                }
            }

            if (rt[i] > 0) q.add(i); // if not finished, go to end of queue
            else {
                ct[i] = time; // completion time
                tat[i] = ct[i] - at[i]; // turnaround time
                wt[i] = tat[i] - bt[i]; // waiting time
                done++; // increase completed count
            }
        }

        // print output
        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++)
            System.out.println((i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);

        sc.close(); // close scanner
    }
}
