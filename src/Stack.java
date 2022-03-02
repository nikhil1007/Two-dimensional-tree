/**
 * Name: Nikhil Kashyap (nikhilka)
 * Course: Data Structures and Algorithms 95-771, CMU
 * Assignment: Project 2 - 2d Trees
 * Date of Submission: 15th Feb 2022
 */

/**
 * Purpose: This class is implemented to achieve reversedLevelOrdered traversal of 2d Tree
 *
 * This class defined a data structure known as stack where:
 *      a) An element inserted last will be deleted first
 */

public class Stack {
    /**
     * frontPointer - keeps track of the latest element inserted into the stack
     */
    StackNode frontPointer;

    public Stack(){
        frontPointer = null;
    }

    /**
     * Theta(1), constant time complexity
     * @return
     *  Returns a boolean value indicating if the stack is empty or not
     */
    public boolean isEmpty(){
        return frontPointer == null;
    }

    /**
     * @param node
     *  A valid node of type TwoDTree node to be inserted into the stack
     * @precondition
     *  frontPointer is defined and initialized
     * @postcondition
     *  A new node of type StackNode will be inserted to the top of the stack
     */
    public void push(TwoDTreeNode node){
        StackNode stackNode = new StackNode(node);

        if (!this.isEmpty()) {
            stackNode.setLink(frontPointer);
        }
        frontPointer = stackNode;
    }

    /**
     * @precondition
     *  frontPointer is defined and initialized
     * @return
     *  returns a node obj of type TwoDTreeNode which has been deleted from the top of the stack.
     *  null - if the stack is empty
     */
    public TwoDTreeNode pop(){
        if(!this.isEmpty()){
            TwoDTreeNode temp = frontPointer.getData();
            frontPointer = frontPointer.getLink();
            return temp;
        }
    return null;
    }
}
