/**
 * @author Nikhil Kashyap
 */

import java.util.Scanner;

/**
 * This is a control plane class which executes all the methods defined in the TwoDTree class
 */
public class TwoDTreeDriver {
    public static void main(String[] args) {
        TwoDTree twoDTree = new TwoDTree("CrimeLatLonXY.csv");
        TwoDTreeNode treeNode = twoDTree.getRootNode();
        Scanner scanner = new Scanner(System.in);
        int user_input = 0;

        System.out.println();
        System.out.println("------------------------------ START OF DEMO -------------------------------------------");
        System.out.println("Crime file loaded into 2d tree with 27218 records");
        while (user_input != 8){
            System.out.println("What would you like to do?\n" +
                    "1: inorder\n" +
                    "2: preorder\n" +
                    "3: levelorder\n" +
                    "4: postorder\n" +
                    "5: reverseLevelOrder\n" +
                    "6: search for points within rectangle\n" +
                    "7: search for nearest neighbor\n" +
                    "8: quit\n");

            user_input = scanner.nextInt();

            switch (user_input){
                case 1:
                    twoDTree.inOrderPrint(treeNode);
                    System.out.println();
                    break;
                case 2:
                    twoDTree.preOrderPrint(treeNode);
                    System.out.println();
                    break;
                case 3:
                    twoDTree.levelOrderPrint(treeNode);
                    System.out.println();
                    break;
                case 4:
                    twoDTree.postOrderPrint(treeNode);
                    System.out.println();
                    break;
                case 5:
                    twoDTree.ReverselevelOrderPrint(treeNode);
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Enter a rectangle bottom left (X1,Y1) and top right (X2, Y2) as four doubles each separated by a space.");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    String[] fields = input.split("\\s+");
                    System.out.println();
                    twoDTree.findPointsInRange(Double.parseDouble(fields[0]),Double.parseDouble(fields[1]),Double.parseDouble(fields[2]),Double.parseDouble(fields[3]));
                    System.out.println();
                    break;
                case 7:
                    System.out.println();
                    System.out.println("Enter a point to find nearest crime. Separate with a space.");
                    scanner.nextLine();
                    String input2 = scanner.nextLine();
                    String[] fields2 = input2.split("\\s+");
                    System.out.println();
                    twoDTree.nearestNeighbor(Double.parseDouble(fields2[0]), Double.parseDouble(fields2[1]));
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Thank you for exploring Pittsburgh crimes in the 1990’s.");
                    break;
            }
        }
    }
}
