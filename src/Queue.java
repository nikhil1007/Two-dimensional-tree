/**
 * @author Nikhil Kashyap
 */

/**
 * Purpose: This class has been mainly been defined to achieve level order and reverse level order traversal of 2d trees
 *
 * This class defines a data structure called queues where:
 *      a) An element added first will be deleted first.
 *      b) Each element keeps track of an element behind it i.e. keeps track of element inserted after it.
 */
public class Queue {
    /**
     * Root - Keeps track of the newest element in the queue
     * Tail - Keeps track of the oldest element in the queue
     */
    QueueNode root;
    QueueNode tail;

    public Queue(){
        root = null;
        tail = null;
    }

    /**
     * Overrides the default toString() method to print all the data of nodes in the queue
     * Theta(n), linear time complexity
     * @return
     *  Returns a string containing data of all QueueNodes
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        QueueNode iterator = tail;

        while (iterator != null){
            sb.append(iterator.getData());
            iterator = iterator.getLink();
        }
    return sb.toString();
    }

    /**
     * @return
     *  Returns a boolean value indicating if the queue is empty or not
     */
    public boolean isEmpty(){
        return tail == null;
    }

    /**
     * @param node
     *  A valid node of type TwoDTreeNode is passed as an argument to be added to queue
     * @precondition
     *  tail, root pointers are defined and initialized.A valid node of type TwoDTreeNode is passed as an argument
     * @postcondition
     *  A new node provided will be added to the queue with appropriate link.
     */
    public void AddQueueNode(TwoDTreeNode node){
        if(tail == null){
             root = tail = new QueueNode(node);
        }
        else{
            QueueNode temp = new QueueNode(node);
            root.setLink(temp);
            root = temp;
        }
    }

    /**
     * A node which was inserted the earliest i.e. oldest in the queue will be deleted
     * @return
     *  The node which was removed from the queue will be returned
     */
    public TwoDTreeNode DeleteQueueNode(){
        TwoDTreeNode TreeNode = tail.getData();
        tail = tail.getLink();

        return TreeNode;
    }
}
