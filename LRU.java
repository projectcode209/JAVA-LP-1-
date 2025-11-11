import java.util.*;

public class LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter page reference string (space-separated): ");
        String[] pageStr = sc.nextLine().split("\\s+");
        int[] pages = Arrays.stream(pageStr).mapToInt(Integer::parseInt).toArray();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        Set<Integer> memory = new HashSet<>();
        Map<Integer, Integer> recentUse = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (!memory.contains(page)) {
                pageFaults++;
                if (memory.size() == frames) {
                    int lruPage = -1, lruTime = Integer.MAX_VALUE;
                    for (int memPage : memory) {
                        int lastUsed = recentUse.getOrDefault(memPage, -1);
                        if (lastUsed < lruTime) {
                            lruTime = lastUsed;
                            lruPage = memPage;
                        }
                    }
                    memory.remove(lruPage);
                }
                memory.add(page);
            }
            recentUse.put(page, i);
            System.out.println("Page: " + page + " -> Frames: " + memory);
        }

        System.out.println("\nTotal Page Faults (LRU): " + pageFaults);
        sc.close();
    }
}
