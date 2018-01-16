/**
 * Created by Meir on 12/21/2015.
 */
public class Node<E> {
    E data;
    Node parent;
    Node left;
    Node right;

    public Node(E data0) {
        data = data0;
        parent = null;
        left = null;
        right = null;
    }


}