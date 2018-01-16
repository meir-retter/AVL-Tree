import java.util.ArrayList;

/**
 * Created by Meir on 12/24/2015.
 */
public class SearchParser {
    public static ArrayList<SearchNode>[] listsFromIntStrings(String inorderIntString, String preorderIntString) {
        ArrayList<SearchNode> inorderRet = new ArrayList<SearchNode>();
        ArrayList<SearchNode> preorderRet = new ArrayList<SearchNode>();
        String[] inorderStrArr = inorderIntString.trim().split("\\s+");
        String[] preorderStrArr = preorderIntString.trim().split("\\s+");
        for (String s : inorderStrArr) {
            inorderRet.add(new SearchNode(Integer.parseInt(s)));
        }
        for (String s : preorderStrArr) {
            preorderRet.add(locationOfPreorderElementInInorder(inorderRet, s));
        }
        ArrayList<SearchNode>[] ret = new ArrayList[2];
        ret[0] = inorderRet;
        ret[1] = preorderRet;
        return ret;
    }

    public static ArrayList<SearchNode>[] listsFromStringStrings(String inorderStringString, String preorderStringString) {
        ArrayList<SearchNode> inorderRet = new ArrayList<SearchNode>();
        ArrayList<SearchNode> preorderRet = new ArrayList<SearchNode>();
        String[] inorderStrArr = inorderStringString.trim().substring(1,inorderStringString.length()-1).split("\" \"");
        String[] preorderStrArr = preorderStringString.trim().substring(1,preorderStringString.length()-1).split("\" \"");
        for (String s : inorderStrArr) {
            inorderRet.add(new SearchNode(s));
        }
        for (String s : preorderStrArr) {
            preorderRet.add(locationOfPreorderElementInInorder(inorderRet, s));
        }
        ArrayList<SearchNode>[] ret = new ArrayList[2];
        ret[0] = inorderRet;
        ret[1] = preorderRet;
        return ret;
    }

    public static SearchNode locationOfPreorderElementInInorder(ArrayList<SearchNode> inorderRet, String preorderElement) {
        for (int i = 0; i < inorderRet.size(); i++) {
            if ((inorderRet.get(i).data + "").equals(preorderElement)) {
                return inorderRet.get(i);
            }
        }
        assert false;
        return null;
    }
}
