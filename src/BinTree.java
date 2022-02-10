public class BinTree {
    public static void main(String [] args) {
        System.out.println("Hallo zu binären Bäumen");

        BinaryTree binTree = new BinaryTree();
        // Konstruktion eines binären Suchbaumes
/*
        binTree.insert(binTree.getRoot(), 60);
        binTree.insert(binTree.getRoot(), 40);
        binTree.insert(binTree.getRoot(), 20);
        binTree.insert(binTree.getRoot(), 10);
        binTree.insert(binTree.getRoot(), 30);
        binTree.insert(binTree.getRoot(), 50);
        binTree.insert(binTree.getRoot(), 80);
        binTree.insert(binTree.getRoot(), 70);
        binTree.insert(binTree.getRoot(), 90);
*/
        binTree.insertIterativ(binTree.getRoot(), 60);
        binTree.insertIterativ(binTree.getRoot(), 40);
        binTree.insertIterativ(binTree.getRoot(), 20);
        binTree.insertIterativ(binTree.getRoot(), 10);
        binTree.insertIterativ(binTree.getRoot(), 30);
        binTree.insertIterativ(binTree.getRoot(), 50);
        binTree.insertIterativ(binTree.getRoot(), 80);
        binTree.insertIterativ(binTree.getRoot(), 70);
        binTree.insertIterativ(binTree.getRoot(), 75);
        binTree.insertIterativ(binTree.getRoot(), 90);
        //binTree.insertIterativ(binTree.getRoot(), 45);
        //binTree.insertIterativ(binTree.getRoot(), 85);

        System.out.println("In-order traversal rekursiv");
        binTree.inOrderRekursiv(binTree.getRoot());
        //System.out.println();
        //System.out.println("In-Order traversal iterativ");
        //binTree.inOrderIterativ(binTree.getRoot());
        System.out.println();
        System.out.println("Pre-order traversal");
        binTree.preOrderRekursiv(binTree.getRoot());
        System.out.println();
        System.out.println("Post-order traversal");
        binTree.postOrderRekursiv(binTree.getRoot());
        System.out.println();
        System.out.println("Mininum: " + binTree.searchMinValue(binTree.getRoot()).getData());
        System.out.println("Maximum: " + binTree.searchMaxValue(binTree.getRoot()).getData());

        findeWert(binTree,20);
        findeWert(binTree,70);
        findeWert(binTree, 100);

        findeParent(binTree, 60);
        findeParent(binTree, 40);
        findeParent(binTree, 20);
        findeParent(binTree, 10);
        findeParent(binTree, 30);
        findeParent(binTree, 50);
        findeParent(binTree, 80);
        findeParent(binTree, 70);
        findeParent(binTree, 90);
        findeParent(binTree, 45);
        findeParent(binTree, 85);

        //binTree.removeNode(60);
        //System.out.println("After removing 60 (root)");
        //binTree.removeNode(45);
        System.out.println("After removing 45");
        //binTree.removeNode(85);
        //System.out.println("After removing 85");
        binTree.removeNode(10);
        System.out.println("After removing 10");
        //binTree.removeNode(80);
        System.out.println("After removing 80");

        //System.out.println("In-order traversal rekursiv");
        binTree.inOrderRekursiv(binTree.getRoot());

        binTree.removeNode(20);
        System.out.println("After removing 20");
        binTree.inOrderRekursiv(binTree.getRoot());

        binTree.removeNode(40);
        System.out.println("After removing 40");
        binTree.inOrderRekursiv(binTree.getRoot());

    }

    private static void findeWert(BinaryTree binTree, int value) {
        Node knoten = binTree.findNode(binTree.getRoot(), value);
        if (knoten != null) {
            System.out.println("Knoten mit dem Wert " + value + " gefunden!; Knoten.getData()=" + knoten.getData());
        }
        else {
            System.out.println("Knoten mit dem Wert " + value + " nicht gefunden.");
        }
    }

    private static void findeParent(BinaryTree binTree, int value) {
        Node knoten = binTree.findNode(binTree.getRoot(), value);
        if (knoten != null) {
            Node parent = binTree.findParent(knoten);
            //Node parent = binTree.findParentRekursiv(knoten, binTree.getRoot());
            if (parent != null) {
                System.out.println("Parent von " + value + " ist :" + parent.getData());
            }
            else {
                System.out.println("Parent von " + value + " nicht gefunden!");
            }
        }
    }
}
