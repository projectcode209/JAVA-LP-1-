import java.util.*; // import utility classes

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // to take user input

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt(); // number of processes

        int[] at = new int[n]; // array for Arrival Time
        int[] bt = new int[n]; // array for Burst Time
        int[] ct = new int[n]; // array for Completion Time
        int[] tat = new int[n]; // array for Turnaround Time
        int[] wt = new int[n]; // array for Waiting Time

        // take arrival and burst time input
        for (int i = 0; i < n; i++) {
            System.out.println("Process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
        }

        // sort processes by arrival time (FCFS = first come first serve)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    // swap arrival time
                    int temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    // swap burst time also
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                }
            }
        }

        // calculate completion, turnaround and waiting times
        ct[0] = at[0] + bt[0]; // first process
        for (int i = 1; i < n; i++) {
            if (at[i] > ct[i - 1]) {
                // CPU is idle until next process arrives
                ct[i] = at[i] + bt[i];
            } else {
                // continue execution immediately
                ct[i] = ct[i - 1] + bt[i];
            }
        }

        // calculate TAT and WT
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i]; // Turnaround Time = CT - AT
            wt[i] = tat[i] - bt[i]; // Waiting Time = TAT - BT
        }

        // print output
        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close(); // close scanner
    }
}
