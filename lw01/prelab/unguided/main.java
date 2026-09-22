package unguided;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<washservice> jobs = new ArrayList<>();

        try (Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobs.add(new motorcycle(id, days, 1));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    jobs.add(new carwash(id, days, 1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: washes.txt file not found.");
            return;
        }

        for (washservice job : jobs) {
            System.out.println(job.summary());
        }
    }
}
