package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customerRecords = new LinkedList<>();
        Queue<String[]> qTransac = new LinkedList<>();
        Stack<String[]> withdrawFail = new Stack<>();

        Scanner zn = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (zn.hasNextLine()) {
            String line = zn.nextLine();
            String[] kata = line.split(" ");

            if(customerRecords.isEmpty()) {
                customerRecords.add(new String[]{kata[0], "0"});
            } else {
                boolean checkAda = false;
                for(String[] tes : customerRecords) {
                    if(tes[0].equals(kata[0])) {
                        checkAda = true;
                        break;
                    }
                }
                if(!checkAda) customerRecords.add(new String[]{kata[0], "0"});
            }
            transactions.add(kata);
        }

        for (String[] transaksi : transactions) {
            qTransac.add(transaksi);
        }

        while(!qTransac.isEmpty()) {
            String[] cust = qTransac.poll();
            String nama = cust[0];
            String jenis = cust[1];
            int jumlah = Integer.parseInt(cust[2]);

            for(String[] customer : customerRecords) {
                if(customer[0].equals(nama)) {
                    int balance = Integer.parseInt(customer[1]);

                    if(jenis.equals("DEPOSIT")) {
                        customer[1] = String.valueOf(jumlah + balance);
                    } else if(jenis.equals("WITHDRAW")) {
                        if(balance < jumlah) {
                            withdrawFail.push(cust);
                        } else {
                            customer[1] = String.valueOf(balance-jumlah);
                        }
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for(String[] array : customerRecords) {
            System.out.println(array[0] + " : " + array[1]);
        }

        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while(!withdrawFail.isEmpty()) {
            String[] array = withdrawFail.pop();
            System.out.println(array[0] + " " + array[1] + " " + array[2]);
        }

        zn.close();
    }
}