/**
 * Created by Meir on 12/23/2015.
 */
public class SearchNode<E extends Comparable> {
    // same as Node but Comparable

    E data;
    SearchNode<E> parent;
    SearchNode<E> left;
    SearchNode<E> right;
    int balance = 0;

    public SearchNode(E data0) {
        data = data0;
        parent = null;
        left = null;
        right = null;
    }

    public SearchNode() {
        data = null;
        parent = null;
        left = null;
        right = null;
    }




}
