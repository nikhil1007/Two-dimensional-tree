/**
 * Name: Nikhil Kashyap (nikhilka)
 * Course: Data Structures and Algorithms 95-771, CMU
 * Assignment: Project 2 - 2d Trees
 * Date of Submission: 15th Feb 2022
 */

/**
 * This class represents a single Stack Node which would be part of Stack data structure.
 * It contains a TwoDTreeNode as its data and reference to the node inserted after it.
 */

public class StackNode {
    /**
     * data - TwoDTreeNode object
     * link - reference to the StackNode inserted into stack after this node
     */
    TwoDTreeNode data;
    StackNode link;

    public StackNode(TwoDTreeNode data){
        this.data = data;
        link = null;
    }

    //--------------------------- Getters and Setter method for instance variables ---------------------------------
    public TwoDTreeNode getData() {
        return data;
    }

    public void setData(TwoDTreeNode data) {
        this.data = data;
    }

    public StackNode getLink() {
        return link;
    }

    public void setLink(StackNode link) {
        this.link = link;
    }

}
