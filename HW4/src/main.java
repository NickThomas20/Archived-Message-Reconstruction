import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Nick Thomas
 * The  objective  of  this  exercise  is  to  reconstruct/unzip  a  message  archived  with  a  binary-tree-
 * based  algorithm.  The  program  should  ask  for  a  single  filename  at  the  start:  "Please
 * enter filename to decode:  ",  decode  the  message  in  the  file  and  print  it  out  to  the
 * console.  The  name  of  the  compressed  message  file  will  end  in  .arch,  e.g.  “monalisa.arch”.
 * The  file consists of two or three  lines:  the  first  one  or  two  lines  contain  the  encoding
 * scheme, and  the  second or third  line contains the archived message.
 **/

public class main {
    private static int indexNode = 0;
    private static final int payloadChar = 0;

    public static void main(String[] args) {
        //All set up/initializtion below
        String fileName = "";
        String encrytedMessage = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter filename to decode:  ");
        fileName = scan.next();
        //Create array list to capture line by line of encripted message
        ArrayList<String> line = new ArrayList<String>();
        //try catch for file
        try {
            File file = new File(fileName);
            Scanner fileScan = new Scanner(file);
            //loop through the file
            while (fileScan.hasNext()) {
                //add to the array list
                line.add(fileScan.nextLine());
            }
            //close scanner
            fileScan.close();

        } catch (Exception E) {
            System.out.println(E);
        }//end try catch
        //loop to start at the first line
        for (int i = 0; i < line.size() - 2; i++) {
            encrytedMessage += line.get(i) + '\n';
        }
        encrytedMessage += line.get(line.size() - 2);
        //create msg tree obj
        MsgTree tree = new MsgTree(encrytedMessage);
        //call print codes
        tree.printCodes(tree, "");
        //loop through till the last line of the file so the decoding works
        while (indexNode < line.get(line.size() - 1).length()) {
            decode(tree, line.get(line.size() - 1));
        }


    }

    /**
     * This method is made to print the deocded message to the console
     *
     * @param codes
     * @param msg
     */
    public static void decode(MsgTree codes, String msg) {
        //loop through until it reaches a null node
        while (codes.left != null && codes.right != null) {
            //check if 0 to assign to left
            if (msg.charAt(indexNode) == '0') {
                //assign to left
                codes = codes.left;
            }//end if
            //check if 1 to assign to right
            if (msg.charAt(indexNode) == '1') {
                //assign to right
                codes = codes.right;
            }//end if
            // iterate through tree
            indexNode++;
        }//end while
        //print out the code
        System.out.print(codes.payloadChar);


    }//end decode method
}
