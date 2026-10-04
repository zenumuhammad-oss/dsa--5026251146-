package lw03.prelab;
import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("===== Problem 1 =====");
        Scanner zn1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (zn1.hasNext()) {
            String type = zn1.next();
            if (type.equals("ADD")) {
                String songName = zn1.nextLine();
                playlist.add(songName);
            } else if (type.equals("INSERT")) {
                int index = zn1.nextInt();
                String songName = zn1.nextLine();
                playlist.add(index, songName);
            } else if (type.equals("REMOVE")) {
                String songName = zn1.nextLine();
                playlist.remove(songName);
            }
        }
        zn1.close();

        System.out.println("Total songs: " + playlist.size());
        int i = 0;
        while (i < playlist.size()) {
            System.out.println((i + 1) + ": " + playlist.get(i));
            i++;
        }

        System.out.println(" ");
        System.out.println("===== Problem 2 =====");
        Scanner zn2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();
        int duplicate = 0;

        while (zn2.hasNext()) {
            String name = zn2.next();
            if(!participants.contains(name)){
                participants.add(name);
            } else {
                duplicate++;
            }
        }
        zn2.close();

        System.out.println("Unique participants: " + participants.size());
        int order = 1;
        for (String participant : participants) {
            System.out.println(order + ". " + participant);
            order++;
        }
        System.out.println("Duplicate registrations: " + duplicate);

        System.out.println(" ");
        System.out.println("===== Problem 3 =====");
        Scanner zn3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        
        while (zn3.hasNext()) {
            String type = zn3.next();
            String productType = zn3.next();
            int quantity = zn3.nextInt();

            if (type.equals("ADD")) {
                int stock = 0;
                if (!inventory.containsKey(productType)) {
                    stock = 0;
                } else {
                    stock = inventory.get(productType);
                }
                inventory.put(productType, stock + quantity);

            } else if (type.equals("SELL")) {
                if (inventory.containsKey(productType) && inventory.get(productType) >= quantity) {
                    int stock = inventory.get(productType);
                    inventory.put(productType, stock - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        zn3.close();

        for (String productType : inventory.keySet()) {
            int stock = inventory.get(productType);
            System.out.println(productType + ": " + stock);
        }
        System.out.println("Failed sales: " + failedSales);
    }
}