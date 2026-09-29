package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();
        Queue<String[]> qOrders = new LinkedList<>();
        Stack<String[]> failOrders = new Stack<>();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        Scanner zn = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        
        while (zn.hasNextLine()) {
            String line = zn.nextLine();
            if (line.trim().isEmpty()) continue; // Skip empty lines if any
            String[] kata = line.split(" ");
            orders.add(kata);
        }
        zn.close();

        for (String[] order : orders) {
            qOrders.add(order);
        }

        while (!qOrders.isEmpty()) {
            String[] currentOrder = qOrders.poll();
            String foodReq = currentOrder[1];
            String drinkReq = currentOrder[2];

            boolean isFoodAvailable = true;
            boolean isDrinkAvailable = true;

            if (!foodReq.equals("-")) {
                isFoodAvailable = false;
                for (String[] f : foods) {
                    if (f[0].equals(foodReq) && Integer.parseInt(f[1]) > 0) {
                        isFoodAvailable = true;
                        break;
                    }
                }
            }

            if (!drinkReq.equals("-")) {
                isDrinkAvailable = false;
                for (String[] d : drinks) {
                    if (d[0].equals(drinkReq) && Integer.parseInt(d[1]) > 0) {
                        isDrinkAvailable = true;
                        break;
                    }
                }
            }

            if (isFoodAvailable && isDrinkAvailable) {
                if (!foodReq.equals("-")) {
                    for (String[] f : foods) {
                        if (f[0].equals(foodReq)) {
                            f[1] = String.valueOf(Integer.parseInt(f[1]) - 1);
                            break;
                        }
                    }
                }
                
                if (!drinkReq.equals("-")) {
                    for (String[] d : drinks) {
                        if (d[0].equals(drinkReq)) {
                            d[1] = String.valueOf(Integer.parseInt(d[1]) - 1);
                            break;
                        }
                    }
                }
                successOrders.add(currentOrder);
            } else {
                failOrders.push(currentOrder);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] s : successOrders) {
            System.out.println(s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
        }

        System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foods) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinks) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println();

        System.out.println("=== Failed Orders ===");
        while (!failOrders.isEmpty()) {
            String[] f = failOrders.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2] + " " + f[3]);
        }
    }
}
