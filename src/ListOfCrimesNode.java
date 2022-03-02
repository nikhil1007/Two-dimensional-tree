/**
 * Name: Nikhil Kashyap (nikhilka)
 * Course: Data Structures and Algorithms 95-771, CMU
 * Assignment: Project 2 - 2d Trees
 * Date of Submission: 15th Feb 2022
 */

/**
 * This class represents a single node of ListOfCrime class. It contains TwoDTreeNode as its data and reference to
 * the node of type ListOfCrimeNode added to the linked list after this node.
 */
public class ListOfCrimesNode {
    /**
     * crime - data portion of the node which is a TwoDTreeNode
     * link - reference to the node of type ListOfCrimeNode added to the linked list after this node
     */
    private final TwoDTreeNode crime;
    private ListOfCrimesNode link;

    public ListOfCrimesNode(TwoDTreeNode crime){
        this.crime = crime;
        link = null;
    }

    // --------------------------------------- Getters and Setters for the instance variables --------------------------
    public TwoDTreeNode getCrime() {
        return crime;
    }

    public ListOfCrimesNode getLink() {
        return link;
    }

    public void setLink(ListOfCrimesNode link) {
        this.link = link;
    }
}
