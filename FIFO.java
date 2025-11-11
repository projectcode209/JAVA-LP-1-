import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter page reference string (space-separated): ");
        String[] pageStr = sc.nextLine().split("\\s+");
        int[] pages = Arrays.stream(pageStr).mapToInt(Integer::parseInt).toArray();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        Set<Integer> memory = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!memory.contains(page)) {
                pageFaults++;
                if (memory.size() == frames) {
                    int removed = queue.poll();
                    memory.remove(removed);
                }
                memory.add(page);
                queue.add(page);
            }
            System.out.println("Page: " + page + " -> Frames: " + memory);
        }

        System.out.println("\nTotal Page Faults (FIFO): " + pageFaults);
        sc.close();
    }
}
