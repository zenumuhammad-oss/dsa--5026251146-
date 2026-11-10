package lw04.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Membaca file input parcels.txt menggunakan Scanner dari resource stream
        Scanner z = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        // 1. Membaca jumlah parcel (N) dan data bobot parcel
        int n = z.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = z.nextInt();
        }

        // 2. Membaca jumlah target (M) dan data target bobot
        int targetAmount = z.nextInt();
        int[] targets = new int[targetAmount];
        for (int i = 0; i < targetAmount; i++) {
            targets[i] = z.nextInt();
        }
        z.close();

        // Array untuk menyimpan kondisi rak setelah tepat 2 pass
        int[] after2Passes = new int[n];

        // 3. Proses Pengurutan: Bubble Sort Descending
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (weights[j] < weights[j + 1]) {
                    int temp = weights[j];
                    weights[j] = weights[j + 1];
                    weights[j + 1] = temp;
                }
            }
            // Simpan salinan kondisi array setelah pass ke-2 (i == 1)
            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    after2Passes[k] = weights[k];
                }
            }
        }

        // Penanganan kondisi jika N <= 2
        if (n <= 2) {
            for (int k = 0; k < n; k++) {
                after2Passes[k] = weights[k];
            }
        }

        // 4. Menampilkan kondisi setelah 2 pass
        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(after2Passes[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        // 5. Menampilkan kondisi terurut sempurna (Fully Sorted)
        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < n; i++) {
            System.out.print(weights[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        // 6. Proses Pencarian: Binary Search pada Array Terurut Descending
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
                    // Karena array descending, nilai yang lebih besar berada di sebelah kiri
                    high = mid - 1;
                } else {
                    // Nilai yang lebih kecil berada di sebelah kanan
                    low = mid + 1;
                }
            }

            if (!found) {
                System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}