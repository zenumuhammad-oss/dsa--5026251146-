package lw04.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner z = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = z.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = z.nextInt();
        }

        int targetAmount = z.nextInt();
        int[] targets = new int[targetAmount];
        for (int i = 0; i < targetAmount; i++) {
            targets[i] = z.nextInt();
        }
        z.close();

        int[] after2Passes = new int[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (weights[j] < weights[j + 1]) {
                    int temp = weights[j];
                    weights[j] = weights[j + 1];
                    weights[j + 1] = temp;
                }
            }
            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    after2Passes[k] = weights[k];
                }
            }
        }

        if (n <= 2) {
            for (int k = 0; k < n; k++) {
                after2Passes[k] = weights[k];
            }
        }

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(after2Passes[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < n; i++) {
            System.out.print(weights[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int target : targets) {
            int low = 0;
            int high = n - 1;
            int steps = 0;
            boolean found = false;

            while (low <= high) {
                steps++;
                int mid = (low + high) / 2; // Menentukan posisi tengah

                if (weights[mid] == target) {
                    System.out.println(target + ": FOUND at index " + mid + " (steps: " + steps + ")");
                    found = true;
                    break;
                } else if (weights[mid] < target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (!found) {
                System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}