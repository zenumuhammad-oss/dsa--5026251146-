package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner zn = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

if (zn.hasNextLine()) {
    String[] tokens = zn.nextLine().split("\\s+");
    for (String token : tokens) {
        if (!token.isEmpty()) {
            bst.insert(Integer.parseInt(token));
        }
    }
}

        int target = 0;
        if (zn.hasNextInt()) {
            target = zn.nextInt();
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Nodes : " + bst.countNodes());
        System.out.println("Leaves : " + bst.countLeaves());

        zn.close();
    }
}