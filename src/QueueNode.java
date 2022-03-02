/**
 * @author Nikhil Kashyap (nikhilka)
 */

/**
 * This class represents a single node in the Queue data structure. It contains a data variable and reference to the node
 * inserted after it to the queue.
 */
public class QueueNode {
    /**
     * data - a node of a 2d tree of type TwoDTreeNode
     * link - reference to the node inserted after it to the queue.
     */
    private TwoDTreeNode data;
    private QueueNode link;

    public QueueNode(TwoDTreeNode data){
        this.data = data;
        link = null;
    }

    // ----------------------------------- Getters and Setters for the instance variables ------------------------
    public TwoDTreeNode getData() {
        return data;
    }

    public void setData(TwoDTreeNode data) {
        this.data = data;
    }

    public QueueNode getLink() {
        return link;
    }

    public void setLink(QueueNode link) {
        this.link = link;
    }
}
