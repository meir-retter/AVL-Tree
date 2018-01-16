import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Meir on 12/23/2015.
 */
public class BinarySearchTree<E extends Comparable>  {

    SearchNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(E data) {
        root = new SearchNode<E>(data);
    }

    public void insertAtNode(SearchNode<E> n, SearchNode<E> nodeToInsertAt) {
        // assumes nodeToInsertAt is not null
        if (n.data.compareTo(nodeToInsertAt.data) < 0) {
            if (nodeToInsertAt.left == null) {
                nodeToInsertAt.left = n;
                n.parent = nodeToInsertAt;
            } else {
                insertAtNode(n, nodeToInsertAt.left);
            }
        } else if (n.data.compareTo(nodeToInsertAt.data) > 0) {

            if (nodeToInsertAt.right == null) {

                nodeToInsertAt.right = n;
                n.parent = nodeToInsertAt;
            } else {
                insertAtNode(n, nodeToInsertAt.right);
            }
        }
        // notice that if the element to be inserted is already in the tree
        // then it won't be inserted
    }

    public void insertDataAtNode(E datum, SearchNode<E> nodeToInsertAt) {
        SearchNode<E> n = new SearchNode<E>(datum);
        // assumes nodeToInsertAt is not null
        if (n.data.compareTo(nodeToInsertAt.data) < 0) {
            if (nodeToInsertAt.left == null) {
                nodeToInsertAt.left = n;
                n.parent = nodeToInsertAt;

            } else {

                insertAtNode(n, nodeToInsertAt.left);
            }
        } else if (n.data.compareTo(nodeToInsertAt.data) > 0) {
            if (nodeToInsertAt.right == null) {
                nodeToInsertAt.right = n;
                n.parent = nodeToInsertAt;
            } else {
                insertAtNode(n, nodeToInsertAt.right);
            }
        }
        // notice that if the element to be inserted is already in the tree
        // then it won't be inserted
    }

    public void insertData(E datum) {
        if (root == null) {
            root = new SearchNode<E>(datum);
            return;
        } else {
            insertDataAtNode(datum, root);
        }
    }



    public SearchNode<E> search(E element) {
        return search(element, root);
    }

    public SearchNode<E> search(E datum, SearchNode<E> nodeToBeginSearch) {
        if (nodeToBeginSearch == null) {
            return null;
        }
        if (datum.equals(nodeToBeginSearch.data)) {
            return nodeToBeginSearch;
        }
        if (datum.compareTo(nodeToBeginSearch.data) < 0) {
            return search(datum, nodeToBeginSearch.left);
        }
        if (datum.compareTo(nodeToBeginSearch.data) > 0) {
            return search(datum, nodeToBeginSearch.right);
        }
        assert false;
        return null;
    }

    public void insertNode(SearchNode<E> n) {
        if (root == null) {
            root = n;
            return;
        } else {
            insertAtNode(n, root);
        }
    }

    public SearchNode<E> findNode(SearchNode<E> n, E datum) {
        if (n == null) {
            return null;
        }
        if (n.data.equals(datum)) {
            return n;
        }
        if (datum.compareTo(n.data) < 0) {
            return findNode(n.left, datum);
        }
        if (datum.compareTo(n.data) > 0) {
            return findNode(n.right, datum);
        }
        assert false;
        return null;
    }

    public void transplant(SearchNode<E> u, SearchNode<E> v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        } if (v != null) {
            v.parent = u.parent;
        }
    }

    public SearchNode<E> treeMinimum(SearchNode<E> x) {
        if (x.left == null) {
            return x;
        }
        return treeMinimum(x.left);
    }

    public void delete(SearchNode<E> z) {
        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            SearchNode<E> y = treeMinimum(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z,y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    public ArrayList<SearchNode<E>> getInorder(SearchNode<E> node) {
        ArrayList<SearchNode<E>> ret = new ArrayList<SearchNode<E>>();
        if (node == null) {
            return ret;
        } else {
            ret.addAll(getInorder(node.left));
            ret.add(node);
            ret.addAll(getInorder(node.right));
        }
        return ret;
    }

    public ArrayList<SearchNode<E>> getInorder() {
        return getInorder(root);
    }

    public static void main(String[] args) {
        Scanner sc = null;
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        } else {
            fileName = "searchtreefile.txt";
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

        BinarySearchTree T = new BinarySearchTree(null);
        for (SearchNode n : inserters) {
            T.insertNode(n);

        }

        SearchTreePrinter.printSubtreeWithRoot(T.root, 0);

        for (SearchNode n : deleters) {
            T.delete(T.findNode(T.root, n.data));
        }

        SearchTreePrinter.printSubtreeWithRoot(T.root, 0);


    }
}
