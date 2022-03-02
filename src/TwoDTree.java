/**
 * Name: Nikhil Kashyap (nikhilka)
 * Course: Data Structures and Algorithms 95-771, CMU
 * Assignment: Project 2 - 2d Trees
 * Date of Submission: 15th Feb 2022
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * a) This class builds a 2d tree using similar rules used to construct a binary search tree.
 * b) It implements common tree traversals like: Inorder, postorder, preorder, levelOrder, reverseLevelOrder.
 * c) It implements functionalities like: rangeSearch, nearestNeighbor
 * Ideas adopted from Robert Sedgewick: https://www.youtube.com/watch?v=1OoM0phlO_U
 */
public class TwoDTree {

    /**
     * Each node of the 2d tree is of the type TwoDTreeNode.
     * rootNode - Always keeps track of the root of the 2d tree
     * addTo - a variable used to indicate whether to add a new Node as Left or Right child.
     * crimes - variable used to store results of rangeSearch containing list of crimes within the given range.
     * NODES_TRAVERSED - keeps track of the count of nodes visited during the rangeSearch operation
     */
    private TwoDTreeNode rootNode;
    private String addTo;
    public ListOfCrimes crimes;
    public Neighbor nearestNeighbor;
    public int NODES_TRAVERSED;
    public int NODES_TRAVERSED_NEIGHBOR;
    public int records;
    public double min_distance;
    /**
     * Reads the text file and constructs a 2d tree. Each node contains a single line from the file.
     * It also initializes other instance variables.
     * @param crimeDataLocation
     *  A valid file location with at-least one line of text in it
     */
    public TwoDTree(String crimeDataLocation){
        readFile(crimeDataLocation);
        crimes = new ListOfCrimes();
        nearestNeighbor = new Neighbor();
        NODES_TRAVERSED = 0;
        NODES_TRAVERSED_NEIGHBOR = 0;
        records = 0;
        min_distance = Double.MAX_VALUE;
        addTo = null;
    }

    //getter method for rootNode
    public TwoDTreeNode getRootNode() {
        return rootNode;
    }

    //setter method for rootNode
    public void setRootNode(TwoDTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Reads the text file and constructs a 2d tree. Each node contains a single line from the file.
     * Theta(n), Linear time complexity
     * @param file
     * A valid file location with at-least one line of text in it.
     * @precondition
     * The structure of the file is expected to have at least 5 columns with X,Y, Latitude, Longitude, additional data
     * @postcondition
     * A 2d tree will be constructed with the data from the file
     */
    private void readFile(String file){
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                AddTreeNode(new TwoDTreeNode(data));
                records++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Adds a new node to the 2d tree. During the process it checks following conditions:
     *      a) If tree is empty and if so adds root.
     *      b) If non-empty navigate to the "to-be" parent of our new node
     *      c) Determines if our node will be left or right child and adds it
     *
     * Evaluating time complexity for 1 insert operation:
     *      a) Worst case is: Big Oh(n)
     *      b) Best case is: Omega (1)
     *      c) Average case is : Theta(log n)            ( for n operation is would be Theta(n log n)
     * @param node
     *  A valid TwoDTreeNode with appropriate values for X,Y
     * @postcondition
     *  A new node of type TwoDTreeNode will be added to the 2d tree of type TwoDTree.
     */
    private void AddTreeNode(TwoDTreeNode node){
        TwoDTreeNode potentialParent = null;

        if(isEmpty(rootNode)){
            setRootNode(node);
        }
        else{
            potentialParent = getPotentialParent(node);
            node.setParent(potentialParent);

            if(addTo.equals("right")){
                potentialParent.setRightChild(node);
            }
            else {
                potentialParent.setLeftChild(node);
            }
        }
    }

    /**
     * @param root
     *  Root of a tree, of type TwoDTreeNode
     *  Theta(1), constant time complexity
     * @return
     *  A boolean value indicating if the 2d tree is empty or not
     */
    private boolean isEmpty(TwoDTreeNode root){
        return root == null;
    }

    /**
     * This is a helper method for AddTreeNode method. It traverses down the tree from given root
     * to a node to which we can add a new node as its child.
     *
     * --------- This method is the core of AddTreeNode method so analysis is similar --------
     *
     * If the height of the 2d tree is 'h' then the time complexity of the method is:
     *      a) Best case: Omega (1) , the tree has only one node
     *      b) Worst case: Big Oh(h)
     *      c) Average case: Theta(log h)
     * @param Node
     *   Root of the 2d Tree to which we are adding a new node. It is of type TwoDTreeNode
     * @precondition
     *   A non-empty 2d tree of type TwoDTree has been constructed
     * @return
     * A TwoDTreeNode is returned, which is a to-be parent for the new child node to be inserted
     */
    private TwoDTreeNode getPotentialParent(TwoDTreeNode Node){
        int level = 2;
        TwoDTreeNode iterator = rootNode;
        TwoDTreeNode temp = null;

        while (iterator != null){
            if(level % 2 == 0){
                if(Node.getX() >= iterator.getX()){
                    temp = iterator;
                    iterator = iterator.getRightChild();
                    addTo = "right";
                    level++;
                }
                else {
                    temp = iterator;
                    iterator = iterator.getLeftChild();
                    addTo = "left";
                    level++;
                }
            }
            else {
                if(Node.getY() >= iterator.getY()){
                    temp = iterator;
                    iterator = iterator.getRightChild();
                    addTo = "right";
                    level++;
                }
                else {
                    temp = iterator;
                    iterator = iterator.getLeftChild();
                    addTo = "left";
                    level++;
                }
            }
        }
    return temp;
    }

    /**
     * Traverses the 2d tree inOrder. i.e. Left-Child -> Root/Parent -> Right-Child
     *
     * Analysing Time complexity - If we assume there are n nodes and m sides then:
     *      a) Worst case: Big Oh(m + n) =~ Big Oh(n) because max value of m = n-1 in binary tree
     *      b) Best case: Omega(n)
     *      c) Average case: Big Theta(n)
     * @param root
     *  Root Node of 2d Tree of type TwoDTreeNode, which is to be traversed and printed
     * @precondition
     *  A non-empty 2d tree of type TwoDTree has been constructed
     * @postcondition
     *  The nodes of the 2d tree will be printed inOrder.
     */
    public void inOrderPrint(TwoDTreeNode root){

        if(root == null)
            return;

        inOrderPrint(root.getLeftChild());
        System.out.println(root);
        inOrderPrint(root.getRightChild());
    }

    /**
     * Traverses the 2d tree preOrder. i.e. Root/Parent -> Left-Child -> Right-Child
     *
     * Analysing Time complexity - If we assume there are n nodes and m sides then:
     *      a) Worst case: Big Oh(m + n) =~ Big Oh(n) because max value of m = n-1 in binary tree
     *      b) Best case: Omega(n)
     *      c) Average case: Big Theta(n)
     * @param root
     *  Root Node of 2d Tree of type TwoDTreeNode, which is to be traversed and printed
     * @precondition
     *  A non-empty 2d tree of type TwoDTree has been constructed
     * @postcondition
     *  The nodes of the 2d tree will be printed preOrder.
     */
    public void preOrderPrint(TwoDTreeNode root){
        if(root == null)
            return;

        System.out.println(root);
        preOrderPrint(root.getLeftChild());
        preOrderPrint(root.getRightChild());
    }

    /**
     * Traverses the 2d tree postOrder. i.e. Left-Child -> Right-Child -> Root/Parent
     *
     * Analysing Time complexity - If we assume there are n nodes and m sides then:
     *      a) Worst case: Big Oh(m + n) =~ Big Oh(n) because max value of m = n-1 in binary tree
     *      b) Best case: Omega(n)
     *      c) Average case: Big Theta(n)
     * @param root
     *  Root Node of 2d Tree of type TwoDTreeNode, which is to be traversed and printed
     * @precondition
     *  A non-empty 2d tree of type TwoDTree has been constructed
     * @postcondition
     *  The nodes of the 2d tree will be printed postOrder.
     */
    public void postOrderPrint(TwoDTreeNode root){
        if(root == null)
            return;

        postOrderPrint(root.getLeftChild());
        postOrderPrint(root.getRightChild());
        System.out.println(root);
    }

    /**
     * Traverses the 2d tree levelOrder. i.e. Parent, Nodes on level 1 then 2 so on...
     *
     * Analysing Time complexity - If we assume there are n nodes and m sides then:
     *      a) Worst case: Big Oh(n)
     *      b) Best case: Omega(n)
     *      c) Average case: Big Theta(n)
     * @param root
     *  Root Node of 2d Tree of type TwoDTreeNode, which is to be traversed and printed
     * @precondition
     *  A non-empty 2d tree of type TwoDTree has been constructed
     * @postcondition
     *  The nodes of the 2d tree will be printed levelOrder.
     */
    public void levelOrderPrint(TwoDTreeNode root){
        Queue queue = new Queue();
        queue.AddQueueNode(root);

        while (!queue.isEmpty()){
            TwoDTreeNode treeNode = queue.DeleteQueueNode();
            System.out.println(treeNode);

            if(treeNode.getLeftChild() != null){
                queue.AddQueueNode(treeNode.getLeftChild());
            }
            if(treeNode.getRightChild() != null){
                queue.AddQueueNode(treeNode.getRightChild());
            }
        }
    }

    /**
     * Traverses the 2d tree reverseLevelOrder. i.e. leaf nodes (level n), then node on level n-1 ... Root
     *
     * Analysing Time complexity - If we assume there are n nodes and m sides then:
     *      a) Worst case: Big Oh(n)
     *      b) Best case: Omega(n)
     *      c) Average case: Big Theta(n)
     * @param root
     *  Root Node of 2d Tree of type TwoDTreeNode, which is to be traversed and printed
     * @precondition
     *  A non-empty 2d tree of type TwoDTree has been constructed
     * @postcondition
     *  The nodes of the 2d tree will be printed reverseLevelOrder.
     */
    public void ReverselevelOrderPrint(TwoDTreeNode root){
        Queue queue = new Queue();
        Stack stack = new Stack();
        queue.AddQueueNode(root);

        while (!queue.isEmpty()){
            TwoDTreeNode treeNode = queue.DeleteQueueNode();
            stack.push(treeNode);

            if(treeNode.getLeftChild() != null){
                queue.AddQueueNode(treeNode.getLeftChild());
            }
            if(treeNode.getRightChild() != null){
                queue.AddQueueNode(treeNode.getRightChild());
            }
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    // A cover function which calls private method searchTree(), abstracting its logic
    public void findPointsInRange(double x1, double y1, double x2, double y2){
        System.out.println("Searching for points within (" + x1 +","+y1+") and ("+x2+","+y2+")");

        searchTree(x1,y1,x2,y2,rootNode,2);

        System.out.println("Examined "+ NODES_TRAVERSED + " during search");
        System.out.println("Found "+ crimes.count + " crime");
        System.out.println(crimes);
        crimes.createKMLDoc(crimes.toKML());
    }

    /**
     * Finds all points within the specified upper and lower bounds.To do this:
     *      a) Check if the current node is within the range (or rectangle)
     *      b) Recursively check if range is to the left/down or right/up from the current node
     *      c) If its to left/down then check the left subtree of the current node.
     *      d) If its to right/Up then check the right subtree of the current node
     *      c) If the range is on both left/down and right/up then check both subtrees of the current node.
     *
     * Analysis of time complexity of the range search method:
     *     - We have seen that to get to a certain node in the tree it takes about (log n) time in the AddNode method
     *     - Here we typically go through the path of the tree, but then there can be few additional nodes ~ (log n) overall
     *     - Lets assume we return R nodes back as result.
     *     - Therefore worst case : Big Oh(R + log n)
     * @param x1
     *  x co-ordinate of the bottom left point of the rectangle range
     * @param y1
     *  y co-ordinate of the bottom left point of the rectangle range
     * @param x2
     *  x co-ordinate of the top right point of the rectangle range
     * @param y2
     *  y co-ordinate of the top right point of the rectangle range
     * @param root
     *  Root of the 2d spatial Tree
     * @param level
     *  The current node's level which determines horizontal or vertical comparison.
     * @precondition
     *  The 2d tree has been constructed
     * @postcondition
     *  A list of 0 or more crimes is returned. These crimes occurred within the rectangular range specified by the
     *  four parameters. The pair (x1, y1) is the left bottom of the rectangle. The pair (x2, y2) is the top right of
     *  the rectangle.
     */
    private void searchTree(double x1,double y1,double x2,double y2, TwoDTreeNode root, int level){
        if(root == null)
            return;

        NODES_TRAVERSED ++;

        if(isInRange(x1,y1,x2,y2,root)){
            crimes.Add(root);
        }

        if(level % 2 == 0){
            if(isSplitx(x1,x2, root)){
                searchTree(x1, y1, x2, y2, root.getLeftChild(), level+1);
                searchTree(x1, y1, x2, y2, root.getRightChild(), level+1);
            }
            else if(isLeft(x2,root)){
                searchTree(x1, y1, x2, y2, root.getLeftChild(), level+1);
            }
            else if(isRight(x1, root)){
                searchTree(x1, y1, x2, y2, root.getRightChild(), level+1);
            }
        }
        else {
            if(isSplity(y1,y2, root)){
                searchTree(x1, y1, x2, y2, root.getLeftChild(), level+1);
                searchTree(x1, y1, x2, y2, root.getRightChild(), level+1);
            }
            else if(isDown(y2,root)){
                searchTree(x1, y1, x2, y2, root.getLeftChild(), level+1);
            }
            else if(isUp(y1, root)){
                searchTree(x1, y1, x2, y2, root.getRightChild(), level+1);
            }
        }
    }

    // A cover function which calls private method searchNeighbor(), abstracting its logic
    public Neighbor nearestNeighbor(double x1, double y1){;
        searchNeighbor(x1,y1,2,rootNode);
        System.out.println("Looked at " + NODES_TRAVERSED_NEIGHBOR + " nodes in the tree. Found the nearest crime at:");
        System.out.println(nearestNeighbor);
        return nearestNeighbor;
    }

    /**
     * Given a point finds its nearest neighbor using 2d tree of type TwoDTree. The logic being:
     *      a) We traverse down the tree and towards the point always. Search left of right subtree recursively.
     *      b) When we visit each node we calculate:
     *          1) Its distance to the target point
     *          2) Keep track of the min_dist to target till date
     *          3) Calculate the perpendicular height (i.e y distance) from target to the parent of the current node
     *      c) If the perpendicular height calculated is less than the min distance till date then we traverse the other
     *         subtree as well
     * @param x1
     *  x coordinate of the target point
     * @param y1
     *  y coordinate of the target point
     * @param level
     *  Current level of node(being inspected) in 2d tree
     * @param root
     *  Node being inspected in the 2d tree
     * @precondition
     *  the 2d tree has been constructed. The (x1,y1) pair represents a point in space near Pittsburgh and in the state
     *  plane coordinate system.
     * @postcondition
     *  the distance in feet to the nearest node is returned in Neighbor. In addition, the Neighbor object contains a
     *  reference to the nearest neighbor in the tree.
     */
    private void searchNeighbor(double x1, double y1, int level, TwoDTreeNode root){
        if(root == null)
            return;

        NODES_TRAVERSED_NEIGHBOR++;

        TwoDTreeNode otherDirection = null;

        double cur_distance = calculateDistance(root, x1,y1);
        double distance_to_parent = 0;

        if(min_distance > cur_distance){
            min_distance = cur_distance;
            nearestNeighbor.setDistanceFromTarget(min_distance);
            nearestNeighbor.setMyNearestNode(root);
        }

        if(level % 2 == 0){
           if(isLeft(x1,root)){
               searchNeighbor(x1,y1,level+1,root.getLeftChild());
               otherDirection = root.getRightChild();
            }
            else if(isRight(x1, root)){
               searchNeighbor(x1,y1,level+1,root.getRightChild());
               otherDirection = root.getLeftChild();
            }
        }
        else {
            if(isDown(y1,root)){
                searchNeighbor(x1,y1,level+1,root.getLeftChild());
                otherDirection = root.getRightChild();
            }
            else if(isUp(y1, root)){
                searchNeighbor(x1,y1,level+1,root.getRightChild());
                otherDirection = root.getLeftChild();
            }
        }

        if(min_distance > cur_distance){
            min_distance = cur_distance;
            nearestNeighbor.setDistanceFromTarget(min_distance);
            nearestNeighbor.setMyNearestNode(root);
        }

        if(root.getParent() != null)
            distance_to_parent = Math.abs(y1 - root.getParent().getY());

        if(distance_to_parent < min_distance ){
            searchNeighbor(x1,y1,level+1,otherDirection);
        }

    }

    public double calculateDistance(TwoDTreeNode node, double x1, double y1){
        double distance = 0;
        double x2 = node.getX();
        double y2 = node.getY();

        distance = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1), 2));

        return distance;
    }

    // ------------------------------- HELPER METHODS SEARCH TREE METHOD --------------------------------------------
    private boolean isSplitx(double x1, double x2, TwoDTreeNode root){
        double px = root.getX();
        return px < x2 && px > x1;
    }

    private boolean isSplity(double y1, double y2, TwoDTreeNode root){
        double py = root.getY();
        return py < y2 && py > y1;
    }

    private boolean isLeft(double x2,TwoDTreeNode root){
        double px = root.getX();
        return px > x2;
    }

    private boolean isRight(double x1, TwoDTreeNode root){
        double px = root.getX();
        return px < x1;
    }

    private boolean isDown(double y2, TwoDTreeNode root){
        double py = root.getY();
        return py > y2;
    }

    private boolean isUp(double y1, TwoDTreeNode root){
        double py = root.getY();
        return py < y1;
    }

    private boolean isInRange(double x1, double y1,double x2, double y2, TwoDTreeNode node){
        double px = node.getX();
        double qy = node.getY();

        return px >= x1 && px <= x2 && qy >= y1 && qy <= y2;
    }
// --------------------------------------- END OF HELPER METHODS -----------------------------------------------------
}
