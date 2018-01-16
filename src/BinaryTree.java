import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Meir on 12/21/2015.
 */
public class BinaryTree {
    Node root;

    public BinaryTree() {
        // makes an empty tree
        root = null;

    }

    public Node headOfIAccordingToP(ArrayList<Node> I, ArrayList<Node> P) {
        for (Node x : P) {
            if (I.contains(x)){
                return x;
            }
        }
        assert false;
        return null;
    }

    public ArrayList<Node> leftPartOfIAccordingToPivot(ArrayList<Node> I,Node pivot) {
        ArrayList<Node> ret = new ArrayList<Node>();
        for (Node n : I) {
            if (I.indexOf(n) < I.indexOf(pivot)){
                ret.add(n);
            }
        }
        return ret;
    }

    public ArrayList<Node> rightPartOfIAccordingToPivot(ArrayList<Node> I, Node pivot) {
        ArrayList<Node> ret = new ArrayList<Node>();
        for (Node n : I) {
            if (I.indexOf(n) > I.indexOf(pivot)){
                ret.add(n);
            }
        }
        return ret;
    }

    public void buildSelf(ArrayList<Node> inorder, ArrayList<Node> preorder) {
        root = preorder.get(0);
        buildSelfLeft(root, leftPartOfIAccordingToPivot(inorder, root), preorder);
        buildSelfRight(root, rightPartOfIAccordingToPivot(inorder, root), preorder);
    }

    public void buildSelfLeft(Node node, ArrayList<Node> inorder, ArrayList<Node> preorder) {
        // have any BinaryTree call it, builds the tree from the given node
        if (inorder.size() == 0) {
            return;
        }
        Node head = headOfIAccordingToP(inorder, preorder);
        node.left = head;
        head.parent = node;
        ArrayList<Node> lefts = leftPartOfIAccordingToPivot(inorder, head);
        ArrayList<Node> rights = rightPartOfIAccordingToPivot(inorder, head);
        buildSelfLeft(head, lefts, preorder);
        buildSelfRight(head, rights, preorder);
    }

    public void buildSelfRight(Node node, ArrayList<Node> inorder, ArrayList<Node> preorder) {
        // have any BinaryTree call it, builds the tree from the given node
        if (inorder.size() == 0) {
            return;
        }
        Node head = headOfIAccordingToP(inorder, preorder);
        node.right = head;
        head.parent = node;
        ArrayList<Node> lefts = leftPartOfIAccordingToPivot(inorder, head);
        ArrayList<Node> rights = rightPartOfIAccordingToPivot(inorder, head);
        buildSelfLeft(head, lefts, preorder);
        buildSelfRight(head, rights, preorder);
    }

//    public ArrayList<Node> getPreorder(Node node) {
//        ArrayList<Node> ret = new ArrayList<Node>();
//        if (node == null) {
//            return ret;
//        } else {
//            ret.add(node);
//            ret.addAll(getPreorder(node.left));
//            ret.addAll(getPreorder(node.right));
//        }
//        return ret;
//    }

    public ArrayList<Node> getInorder(Node node) {
        ArrayList<Node> ret = new ArrayList<Node>();
        if (node == null) {
            return ret;
        } else {
            ret.addAll(getInorder(node.left));
            ret.add(node);
            ret.addAll(getInorder(node.right));
        }
        return ret;
    }

//    public ArrayList<Node> getPostorder(Node node) {
//        ArrayList<Node> ret = new ArrayList<Node>();
//        if (node == null) {
//            return ret;
//        } else {
//            ret.addAll(getPostorder(node.left));
//            ret.add(node);
//            ret.addAll(getPostorder(node.right));
//        }
//        return ret;
//    }





    public static void main(String[] args) {


        // first read the file
        Scanner sc = null;
        String fileName;
        if (args.length > 0) {
            fileName = args[0];
        } else {
            fileName = "treefile.txt";
        }
        try {
            FileReader fr = new FileReader(fileName);
            sc = new Scanner(fr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // dummy variable to get rid of unnecessary first line
        String type = sc.nextLine();
        ArrayList<Node> inorder;
        ArrayList<Node> preorder;
        String inorderString = sc.nextLine();
        String preorderString = sc.nextLine();
        if (type.equals("int")) {
            ArrayList<Node>[] lists = Parser.listsFromIntStrings(inorderString, preorderString);
            inorder = lists[0];
            preorder = lists[1];
        } else {
            assert type.equals("String");
            ArrayList<Node>[] lists = Parser.listsFromStringStrings(inorderString, preorderString);
            inorder = lists[0];
            preorder = lists[1];
        }

        BinaryTree T = new BinaryTree();
        T.buildSelf(inorder, preorder);

        TreePrinter.printSubtreeWithRoot(T.root, 0);





    }
}
