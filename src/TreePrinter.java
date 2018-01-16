import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Meir on 12/24/2015.
 */
public class TreePrinter {

    public static void printSubtreeWithRoot(Node n, int indentationLevel) {
        indent(indentationLevel);
        if (n == null) {
            //System.out.println();
            return;
        }
        System.out.print(n.data);
        if (n.parent != null && n.parent.left == null) {
            System.out.print("(R)");
        }
        if (n.parent != null && n.parent.right == null) {
            System.out.print("(L)");
        }
        System.out.println();
        if (n.left != null) {

            printSubtreeWithRoot(n.left, indentationLevel + 2);

        }
        //System.out.println();
        if (n.right != null) {

            printSubtreeWithRoot(n.right, indentationLevel + 2);

        }
        //System.out.println();
    }

    public static void printSubtreeWithRoot2(Node n, String indentationString) {
        System.out.print(indentationString + "-");

        if (n == null) {
            //System.out.println();
            return;
        }
        System.out.print(n.data);
        if (n.parent != null && n.parent.left == null) {
            System.out.print("(R)");
        }
        if (n.parent != null && n.parent.right == null) {
            System.out.print("(L)");
        }
        System.out.println();
        if (n.left != null) {

            printSubtreeWithRoot2(n.left, indentationString.substring(0,indentationString.length()-1) + "  `");

        }
        //System.out.println();
        if (n.right != null) {

            printSubtreeWithRoot2(n.right, indentationString.substring(0,indentationString.length()-1) + "  `");

        }
        //System.out.println();
    }

    public static void indent(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            System.out.print(" ");
        }
    }

    public static void printTree(BinaryTree T) {
        printSubtreeWithRoot(T.root, 0);
    }
}

