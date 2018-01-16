import java.util.ArrayList;

/**
 * Created by Meir on 12/24/2015.
 */
public class Parser {

    public static ArrayList<Node>[] listsFromIntStrings(String inorderIntString, String preorderIntString) {
        ArrayList<Node> inorderRet = new ArrayList<Node>();
        ArrayList<Node> preorderRet = new ArrayList<Node>();
        String[] inorderStrArr = inorderIntString.trim().split("\\s+");
        String[] preorderStrArr = preorderIntString.trim().split("\\s+");
        for (String s : inorderStrArr) {
            inorderRet.add(new Node(Integer.parseInt(s)));
        }
        for (String s : preorderStrArr) {
            preorderRet.add(locationOfPreorderElementInInorder(inorderRet, s));
        }
        ArrayList<Node>[] ret = new ArrayList[2];
        ret[0] = inorderRet;
        ret[1] = preorderRet;
        return ret;
    }

    public static ArrayList<Node>[] listsFromStringStrings(String inorderStringString, String preorderStringString) {
        ArrayList<Node> inorderRet = new ArrayList<Node>();
        ArrayList<Node> preorderRet = new ArrayList<Node>();
        String[] inorderStrArr = inorderStringString.trim().substring(1,inorderStringString.length()-1).split("\" \"");
        String[] preorderStrArr = preorderStringString.trim().substring(1,preorderStringString.length()-1).split("\" \"");
        for (String s : inorderStrArr) {
            inorderRet.add(new Node(s));
        }
        for (String s : preorderStrArr) {
            preorderRet.add(locationOfPreorderElementInInorder(inorderRet, s));
        }
        ArrayList<Node>[] ret = new ArrayList[2];
        ret[0] = inorderRet;
        ret[1] = preorderRet;
        return ret;
    }

    public static Node locationOfPreorderElementInInorder(ArrayList<Node> inorderRet, String preorderElement) {
        for (int i = 0; i < inorderRet.size(); i++) {
            if ((inorderRet.get(i).data + "").equals(preorderElement)) {
                return inorderRet.get(i);
            }
        }
        assert false;
        return null;
    }


}
