
/**
 * @nickThomas This class is meant to build the Tree and decode it as well
 */

public class MsgTree {

    public char payloadChar;
    public MsgTree left;
    public MsgTree right;
    /*Can use a static char idx to the tree string for recursive
    solution, but it is not strictly necessary*/
    private static int staticCharIdx = 0;

    //Constructor building the tree from a string
    public MsgTree(String encodingString) {
        //set the payload to the string count
        payloadChar = encodingString.charAt(staticCharIdx);
        //increment
        staticCharIdx++;
        //creates the left index at the tree
        left = new MsgTree(encodingString.charAt(staticCharIdx));
        if (left.payloadChar == '^') {
            //keep going left until you cant
            left = new MsgTree(encodingString);
        }
        //increment after
        staticCharIdx++;
        //the same thing, but doing it the right side
        right = new MsgTree(encodingString.charAt(staticCharIdx));
        if (right.payloadChar == '^') {
            //keep going left until you cant
            right = new MsgTree(encodingString);
        }
    }

    //Constructor for a single node with null children
    public MsgTree(char payloadChar) {
        this.payloadChar = payloadChar;
    }

    //method to print characters and their binary codes
    public static void printCodes(MsgTree root, String code) {
        //recursively add 1 and 0's when there is a left (0) and right(1)
        //add 0 to string
        if (root == null) {
            return;
        }
        //space it out when there is a ^ so it shows the branching
        if (root.payloadChar != '^') {
            //conditional to print \n
            if (root.payloadChar == '\n') {
                System.out.println("\\n" + "\s" + code);
            } else {
                //add 2 spaces to line up outputs
                System.out.println(root.payloadChar + "\s\s" + code);
            }
        }
        //recursively calls the method for left and right and adds onto the coded message
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }//end print codes
}
