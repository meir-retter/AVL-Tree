import java.util.ArrayList;

/**
 * Created by Meir on 12/24/2015.
 */
public class Set<E extends Comparable> {
    BinarySearchTree<E> tree;

    public Set(E[] elements) {
        tree = new BinarySearchTree<E>();
        for (E element : elements) {
            SearchNode<E> n = new SearchNode<E>(element);
            tree.insertNode(n);
        }
    }

    public Set(BinarySearchTree<E> T) {
        tree = T;
    }

    public Set<E> union(Set<E> otherSet) {
        BinarySearchTree<E> preRet = new BinarySearchTree<E>();
        for (SearchNode<E> n : tree.getInorder()) {
            preRet.insertData(n.data);
        }
        for (SearchNode<E> n : otherSet.tree.getInorder()) {
            preRet.insertData(n.data);
        }
        return new Set<E>(preRet);
    }

    public Set<E> intersection(Set<E> otherSet) {
        BinarySearchTree<E> preRet = new BinarySearchTree<E>();
        for (SearchNode<E> n : tree.getInorder()) {
            if (otherSet.tree.search(n.data) != null) {
                preRet.insertData(n.data);
            }
        }
        return new Set<E>(preRet);
    }

    public Set<E> difference(Set<E> otherSet) {
        BinarySearchTree<E> preRet = new BinarySearchTree<E>();
        for (SearchNode<E> n : tree.getInorder()) {
            preRet.insertData(n.data);
        }
        for (SearchNode<E> n : otherSet.tree.getInorder()) {
            if (tree.search(n.data) != null) {
                preRet.delete(preRet.findNode(preRet.root, n.data));
            }
        }
        return new Set<E>(preRet);
    }

    public void printContents() {
        for (SearchNode<E> n : tree.getInorder()) {
            System.out.print(n.data);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Set<Integer> S = new Set<Integer>(new Integer[]{1,2,3,4,5});
        Set<Integer> R = new Set<Integer>(new Integer[]{3,4,5,6,7});
        System.out.print("set S: ");
        S.printContents();
        System.out.print("set R: ");
        R.printContents();
        System.out.print("union(S,R): ");
        S.union(R).printContents();
        System.out.print("intersection(S,R): ");
        S.intersection(R).printContents();
        System.out.print("difference(S,R): ");
        S.difference(R).printContents();

    }
}
