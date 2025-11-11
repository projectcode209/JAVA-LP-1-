import java.util.*;

public class OptimalSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter page reference string (space-separated): ");
        String[] input = sc.nextLine().split("\\s+");
        int[] pages = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();
        List<Integer> memory = new ArrayList<>();
        int faults = 0;
        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (memory.contains(page)) {
                System.out.println("Page: " + page + " -> Frames: " + memory);
                continue;
            }
            faults++;
            if (memory.size() < frames) {
                memory.add(page);
            } else {
                int farthestIndex = -1, pageToRemove = -1;

                for (int memPage : memory) {
                    int nextUse = Integer.MAX_VALUE; 
                    for (int j = i + 1; j < pages.length; j++) {
                        if (pages[j] == memPage) {
                            nextUse = j;
                            break;
                        }
                    }
                    if (nextUse > farthestIndex) {
                        farthestIndex = nextUse;
                        pageToRemove = memPage;
                    }
                }
                memory.remove(Integer.valueOf(pageToRemove));
                memory.add(page);
            }
            System.out.println("Page: " + page + " -> Frames: " + memory);
        }
        System.out.println("\nTotal Page Faults (Optimal): " + faults);
        sc.close();
    }
}