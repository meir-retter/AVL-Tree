import java.io.FileReader;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Scanner;

/**
 * Created by Meir on 1/30/2016.
 */
public class AVLTree<E extends Comparable> extends BinarySearchTree {

    public AVLTree(E data) {
        root = new SearchNode<E>(data);
    }

    public AVLTree() {
        root = null;
    }



    public static boolean isLeftChild(SearchNode node) {
        return (node == node.parent.left);
    }



    public void rightRotate(SearchNode<E> x) {


        SearchNode<E> y = x.left;
//        if (root.equals(x))
//            root = y;
        x.left = y.right;
        y.right = x;
        y.parent = x.parent;
        if (y.parent == null) {
            root = y;
        } else if (y.parent.left == x) {
            y.parent.left = y;
        } else {
            y.parent.right = y;
        }


        x.balance++;
        y.balance++;
//        if (x.right == null) {
//            x.balance++;
//            y.balance++;
//        }
        x.parent = y;




    }

    public void leftRotate(SearchNode<E> x) {

        SearchNode<E> y = x.right;
//        if (root.equals(x))
//            root = y;
        x.right = y.left;
        y.left = x;
        y.parent = x. parent;
        if (y.parent == null) {
            root = y;
        } else if (y.parent.left == x) {
            y.parent.left = y;
        } else {
            y.parent.right = y;
        }


        x.balance++;
        y.balance++;
//        if (x.right == null) {
//            x.balance++;
//            y.balance++;
//        }
        x.parent = y;

    }

    public void insert(E data) {
        SearchNode<E> node = new SearchNode<E>(data);
        AVLInsert(node);

    }

    public void insert(E data, SearchNode<E> whereToInsert) {
        SearchNode<E> node = new SearchNode<E>(data);
        insert(node, whereToInsert);
    }

    public void insert(SearchNode<E> node, SearchNode<E> whereToInsert) {
        super.insertAtNode(node, whereToInsert);
    }

    public void AVLInsert(SearchNode<E> node) {
        insertNode(node);

        SearchNode<E> current = node;
        SearchNode<E> next = node.parent;
        while (next != null) {
            // next.balance has not yet been updated!
            if (current == next.left) {
                if (next.balance == 1) {
                    if (current.balance == -1) leftRotate(current); // turn left-right case into left-left case
                    rightRotate(next); // left-left
                    break;
                }
                if (next.balance == -1) {
                    next.balance = 0;
                    break;
                }
                next.balance = 1;
            } else {
                if (next.balance == -1) {
                    if (current.balance == 1) rightRotate(current); // turn right left into right right
                    leftRotate(next); // Right Right case
                    break;
                }
                if (next.balance == 1) {
                    next.balance = 0;
                    break;
                }
                next.balance = -1;
            }
            current = next;
            next = current.parent;
        }
    }

    public void AVLDelete(SearchNode<E> node) {

        SearchNode<E> next = node.parent;
        delete(node);

        SearchNode<E> current;
        if (node.left == null || node.right == null)
            current = node;
        else current = treeMinimum(node.right);
        SearchNode<E> S;
        int B;
        while (next != null) {
            if (current == next.right) {
                if (next.balance == 1) {
                    S = next.left;
                    B = S.balance;
                    if (B == -1) { // Left Right Case
                        leftRotate(S); // Becomes the Left Left Case
                    }
                    // Left Left Case
                    rightRotate(next);
                    if (B == 0)
                        break;
                }
                if (next.balance == 0) {
                    next.balance = 1;
                    break;
                }
                next.balance = 0;
            } else {
                if (next.balance == -1) {
                    S = next.right;
                    B = S != null ? S.balance: 0;
                    if (B == 1) { // Right Left Case
                        rightRotate(S); // becomes Right Right Case
                    }
                    // Right Right Case
                    if (next != null && next.right != null)
                        leftRotate(next);
                    if (B == 0)
                        break;
                }
                if (next.balance == 0) {
                    next.balance = -1;
                    break;
                }
                next.balance = 0;
            }
            current = next;
            next = current.parent;
        }
    }

    public static void main(String[] args) {
        Scanner sc = null;
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        } else {
            fileName = "AVLtestfile2.txt";
        }
        try {
            FileReader fr = new FileReader(fileName);
            sc = new Scanner(fr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String type = sc.nextLine();
        ArrayList<SearchNode> inserters;
        ArrayList<SearchNode> deleters;
        String insertersString = sc.nextLine();
        String deletersString = sc.nextLine();
        if (type.equals("int")) {
            ArrayList<SearchNode>[] lists = SearchParser.listsFromIntStrings(insertersString, deletersString);
            inserters = lists[0];
            deleters = lists[1];
        } else {
            assert type.equals("String");
            ArrayList<SearchNode>[] lists = SearchParser.listsFromStringStrings(insertersString, deletersString);
            inserters = lists[0];
            deleters = lists[1];
        }

        AVLTree T = new AVLTree(inserters.get(0).data);
        for (SearchNode n : inserters.subList(1,inserters.size())) {
            T.AVLInsert(n);

        }

        SearchTreePrinter.printSubtreeWithRoot(T.root, 0);

        for (SearchNode n : deleters) {
            T.AVLDelete(n);
        }
        System.out.println();


        SearchTreePrinter.printSubtreeWithRoot(T.root, 0);



    }





}
