import java.util.Stack;

public class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    // Einfügen, rekursive Variante
    public void insert(Node node, int newData) {
        // Wenn es noch keinen Wurzelknoten gibt, dann erzeugen
        if (this.root == null) {
            this.root = new Node(newData, null, null);
            return;
        }
        // Der "compareValue" gibt an, ob links oder rechts einzufügen ist
        int compareValue = newData - node.getData();
        // Rekursiv linker Teilbaum, um den Einfügepunkt zu finden
        if (compareValue < 0) {
            if (node.left == null) {
                // Hier einfügen
                node.left = new Node(newData, null, null);
            } else {
                // Andernfalls weitermachen, nächster Aufruf
                insert(node.left, newData);
            }
        } else if (compareValue > 0) {
            // Rekursiv den rechten Teilbaum, um den Einfügepunkt zu finden
            if (node.right == null) {
                node.right = new Node(newData, null, null);
            } else {
                insert(node.right, newData);
            }
        }
    }

    // Einfügen, iterative Variante
    public void insertIterativ(Node knoten, int newData) {
        // Wenn es noch keinen Wurzelknoten gibt, dann erzeugen
        if (this.root == null) {
            this.root = new Node(newData, null, null);
            return;
        }
        // Iterative Variante
        Node child = knoten;
        while (child != null) {
            // Zumindest ein Element vorhanden
            // Der "compareValue" gibt an, ob links oder rechts einzufügen ist
            int compareValue = newData - child.getData();
            if (compareValue < 0) {
                if (child.left == null) {
                    // Hier einfügen
                    child.left = new Node(newData, null, null);
                    break;
                } else {
                    // Andernfalls weitermachen, nächster Schleifendurchlauf
                    child = child.left;
                }
            } else if (compareValue > 0) {
                if (child.right == null) {
                    child.right = new Node(newData, null, null);
                    break;
                } else {
                    child = child.right;
                }
            }
        }
    }

    // In-order traversal rekursiv
    public void inOrderRekursiv(Node knoten) {
        // Eingabe: Knoten k eines binären Baums mit Verweis auf linken (knoten.left) und rechten (knoten.right) Teilbaum sowie dem Element knoten.getData().
        // Inorder (knoten.left);       // besuche den linken Teilbaum
        // Verarbeite knoten.getData();
        // Inorder (knoten.right);      // besuche den rechten Teilbaum
        if (knoten != null) {
            inOrderRekursiv(knoten.left); // Durch den linken Teilbaum ...
            System.out.print(knoten.getData() + ",");
            inOrderRekursiv(knoten.right); // Durch den rechten Teilbaum ...
        }
    }

    // Pre-order traversal
    public void preOrderRekursiv(Node knoten) {
        // Eingabe: Knoten k eines binären Baums mit Verweis auf linken (knoten.left) und rechten (knoten.right) Teilbaum sowie dem Element knoten.getData().
        // Verarbeite knoten.getData();
        // PreOrder (knoten.left);       // besuche den linken Teilbaum
        // PreOrder (knoten.right);      // besuche den rechten Teilbaum
        if (knoten != null) {
            System.out.print(knoten.getData() + ",");
            preOrderRekursiv(knoten.left); // Durch den linken Teilbaum ...
            preOrderRekursiv(knoten.right); // Durch den rechten Teilbaum ...
        }
    }

    // Post-order traversal
    public void postOrderRekursiv(Node knoten) {
        // Eingabe: Knoten k eines binären Baums mit Verweis auf linken (knoten.left) und rechten (knoten.right) Teilbaum sowie dem Element knoten.getData().
        // PostOrder (knoten.left);       // besuche den linken Teilbaum
        // PostOrder (knoten.right);      // besuche den rechten Teilbaum
        // Verarbeite knoten.getData();
        if (knoten != null) {
            postOrderRekursiv(knoten.left); // Durch den linken Teilbaum ...
            postOrderRekursiv(knoten.right); // Durch den rechten Teilbaum ...
            System.out.print(knoten.getData() + ",");
        }
    }

    // Finden einen Knoten im Baum mit einem bestimmten Wert (und gib diesen zurück)
    public Node findNode(Node knoten, int value) {
        if (knoten == null) {
            // Baum ist leer
            return null;
        }
        if (value < knoten.getData()) {
            // Wenn der zu findende Wert kleiner ist ald der aktuelle, dann rekursiv links weiter
            return findNode(knoten.left, value);
        }
        else if (value > knoten.getData()) {
            // Wenn der zu findende Wert größer ist ald der aktuelle, dann rekursiv rechts weiter
            return findNode(knoten.right, value);
        }
        // Gib den gefundenen Knoten zurück
        return knoten;
    }

    // Finde den Vorgänger zu einem Knoten (z.B. den, den man vorher mit "findNode" gefunden hat)
    // Variante 1: Iterativ
    public Node findParent(Node knoten) {
        if (knoten != null) {
            // Erzeuge einen leeren Stack (weil wir uns die Nodes merken müsssen, wenn wir den Baum hinabsteigen)
            // (In der rekursiven Variante wird ja automatisch ein Stack aufgebaut, indem die Funktion von sich selbst aus immer wieder aufgerufen wird)
            Stack<Node> nodeStack = new Stack<Node>();
            // Beginne immer mit root (d.h. ganz oben)
            Node currentNode = root;
            while (!nodeStack.empty() || currentNode != null) {
                if (currentNode != null) {
                    // Wir sind noch nicht am Ende und merken uns den Node, indem wir ihn auf den Stack legen
                    nodeStack.push(currentNode);
                    if (currentNode.left == knoten || currentNode.right == knoten) {
                        // Gefunden! (der linke oder rechte Nachfolger ist der Knoten, den wir der Funktion übergeben haben)
                        return currentNode;
                    }
                    // Noch nicht gefunden, weiter links den Baum hinab
                    currentNode = currentNode.left;
                }
                else {
                    // Wir sind an einem Ende angelangt, rechte Seite bearbeiten
                    // Dazu müssen wir den letzten Node, den wir auf den Stack gelegt haben, wieder herunterholen (der aktuelle ist ja null)
                    // Erst dann können wir die rechte Seite bearbeiten
                    Node n = nodeStack.pop();
                    currentNode = n.right;
                }
            }
        }
        // Nichts gefunden, wir geben null zurück
        return null;
    }

    // Finde den Vorgänger zu einem Knoten (z.B. den, den man vorher mit "findNode" gefunden hat)
    // Variante 2: Rekursiv
    public Node findParentRekursiv(Node knoten, Node parent) {
        if (knoten != null && parent != null) {
            if (knoten == parent) {
                // Es gibt keinen Vorgänger der Wurzel
                return null;
            }
            if (parent.left == knoten || parent.right == knoten) {
                // Gefunden!
                return parent;
            }
            Node save = parent;
            if (parent.left != null) {
                parent = findParentRekursiv(knoten, parent.left); // Durch den linken Teilbaum ...
            }
        }
        return parent;
    }

    // Knoten löschen, Variante 1 (umgesetzt vom Pseudo-Code)
    // Fürs löschen von Knoten gibt es mehrere Algorithmen, hier sind zwei davon umgesetzt (und es gibt noch mehr)
    public void removeNode(int value) {
        Node knoten = findNode(root, value);
        if (knoten == null) {
            return;
        }
        Node parent = null;
        if (knoten == root) {
            // Wurzel soll gelöscht werden
            if (knoten.left == null) {
                root = knoten.right;
            } else if (knoten.right == null) {
                root = knoten.left;
            } else {
                // Suche den größten Wert im linken Teilbaum
                Node biggestLeft = searchMaxValue(knoten.left);
                // Entferne die Referenz auf diesen Node beim Vorgänger
                parent = findParent(biggestLeft);
                parent.right = null;
                // Setze diesen Node als neue Wurzel
                root = biggestLeft;
                root.left = knoten.left;
                root.right = knoten.right;
            }
        } else {
            // Normaler Knoten soll gelöscht werden
            // Finde Vorgänger
            parent = findParent(knoten);
            if (knoten.left == null) {
                // Wenn der zu löschende Knoten keinen linken Nachfolger hat
                if (knoten == parent.left) {
                    // Und wenn der zu löschende Knoten der linke Nachfolger vom Vorgänger ist
                    // Dann "hängen" wir die Referenzen um, sodass links am Vorgänger nun der rechte Teilbaum vom zu löschenden Knoten hängt
                    parent.left = knoten.right;
                }
                else {
                    // Andernfalls dasselbe als rechter Nachfolger
                    parent.right = knoten.right;
                }
            } else if (knoten.right == null) {
                // Wenn der zu löschende Knoten keinen rechten Nachfolger hat
                if (knoten == parent.left) {
                    // Und wenn der zu löschende Knoten der linke Nachfolger vom Vorgänger ist
                    // Dann "hängen" wir die Referenzen um, sodass links am Vorgänger nun der rechte Teilbaum vom zu löschenden Knoten hängt
                    parent.left = knoten.left;
                }
                else {
                    // Andernfalls dasselbe als rechter Nachfolger
                    parent.right = knoten.left;
                }
            }
            else {
                // Der zu löschende Knoten hat beide Nachfolger (links und rechts)
                // Suche zuerst den größten Wert im linken Teilbaum
                Node biggestLeft = searchMaxValue(knoten.left);
                // Finde dessen Vorgänger (damit wir die Referenzen richtig "umhängen" können)
                Node biggestLeftParent = findParent(biggestLeft);
                // Wenn der zu löschende Knoten als Nachfahre biggestLeft hat, tausche die Plätze
                if (knoten.left == biggestLeft) {
                    // Der linke Ast des Vorfahren bekommt jetzt biggestLeft als Nachfahre
                    parent.left = biggestLeft;
                    // Der rechte Ast von biggestLeft wird außerdem der rechte Ast des zu löschenden Knotens
                    biggestLeft.right = knoten.right;
                } else {
                    // Lösche den rechten Nachfolger vom Vorgänger von biggestLeft
                    biggestLeftParent.right = null;
                    // Ersetze knoten durch biggestLeft
                    parent.left = biggestLeft;
                    biggestLeft.left = knoten.left;
                    biggestLeft.right = knoten.right;
                }
            }
        }
    }

    // Knoten löschen, Variante 2 (etwas kompakter, aber anderer Algorithmus)
    // 1. If there is no child node, delete it directly
    // 2. If there is only one child node, the child node replaces the current node, and then deletes the current node.
    // 3. If there are two child nodes, replace the current node with the smallest node from the right subtree,
    // because the smallest node on the right is also larger than the value on the left.
    public Node removeNode2(Node knoten, int value) {
        if (knoten == null) {
            return knoten;
        }
        int compareValue = value - knoten.getData();
        if (compareValue > 0) {
            knoten.right = removeNode2(knoten.right, value);
        } else if (compareValue < 0) {
            knoten.left = removeNode2(knoten.left, value);
        } else if (knoten.left != null && knoten.right != null) {
            // Finde den kleinsten Wert vom rechten Teilbaum und ersetze den aktuellen Knoten
            Node min = searchMinValue(knoten.right);
            knoten.setData(min.getData());
            knoten.right = removeNode2(knoten.right, knoten.getData());
        } else {
            if (knoten.left != null) {
                knoten = knoten.left;
            } else {
                knoten = knoten.right;
            }
        }
        return knoten;
    }

    // Suche nach dem Minimum (den minimalen Wert eines Knotens im Baum)
    public Node searchMinValue(Node knoten) {
        if (knoten != null) {
            if (knoten.left == null) {
                // Wenn der linke Knoten leer ist, haben wir den "linkesten" (also kleinsten) Knoten gefunden
                return knoten;
            }
            // Andernfalls müssen wir weitersuchen (in dem Fall rekursiv)
            return searchMinValue(knoten.left);
        }
        return null;
    }

    // Suche nach dem Maximum
    public Node searchMaxValue(Node knoten) {
        if (knoten != null) {
            if (knoten.right == null) {
                return knoten;
            }
            return searchMaxValue(knoten.right);
        }
        return null;
    }

    // In-order traversal iterativ (angelehnt an das iterative Einfügen)
    public void inOrderIterativ(Node knoten) {
        if (knoten != null) {
            // Erzeuge einen leeren Stack (weil wir uns die Nodes merken müsssen, wenn wir den Baum hinabsteigen)
            Stack<Node> nodeStack = new Stack<Node>();
            Node currentNode = knoten;
            while (!nodeStack.empty() || currentNode != null) {
                if (currentNode != null) {
                    // Füge Knoten, die keine Enden sind, an den Stack an (oben drauf) und steige weiter "hinab"
                    nodeStack.push(currentNode);
                    currentNode = currentNode.left;
                }
                else {
                    // Wir sind an einem Ende angelangt, also ausgeben und rechte Seite bearbeiten
                    // Dafür müssen wir aber zuerst das letzte Element wieder vom Stack herunterholen
                    Node n = nodeStack.pop();
                    System.out.print(n.getData() + ",");
                    currentNode = n.right;
                }
            }
        }
    }
}